package com.company.mtable.core;

import com.company.mtable.schema.Column;
import com.company.mtable.schema.Schema;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.company.mtable.core.types.Types.IntegerType;
import static org.junit.Assert.assertEquals;

/**
 * Created by jxwr on 2019/6/18.
 */
public class BucketTest {
    private Schema schema;

    private static Schema newSchema() {
        Schema schema = new Schema("product_info");

        schema.addColumn("poi_id", IntegerType);
        schema.addColumn("product_id", IntegerType);
        schema.addColumn("customer_id", IntegerType);
        schema.addColumn("date", IntegerType);
        schema.addColumn("trade_type", IntegerType);
        schema.addColumn("selling_price", IntegerType);

        schema.setPartitionKey("poi_id");
        schema.setUniqueIndexKeys(Arrays.asList("date", "product_id"));
        return schema;
    }

    @Before
    public void setup() {
        schema = newSchema();
    }

    @Test
    public void getput() throws Exception {
        SkipListBucket bucket = new SkipListBucket();
        for (int j = 0; j < 2; j++) {
            for (int i = 1; i < 11; i++) {
                Record record = Record.newRecord(schema);

                record.set(schema.column("poi_id"), i);
                record.set(schema.column("product_id"), i);
                record.set(schema.column("customer_id"), i);
                record.set(schema.column("date"), i);
                record.set(schema.column("trade_type"), i);
                record.set(schema.column("selling_price"), i);

                bucket.put(schema, record);

                assertEquals(bucket.get(schema,
                        Arrays.asList(
                                new Filter(schema.getUniqueIndexCids()[0], OpType.EQ, i),
                                new Filter(schema.getUniqueIndexCids()[1], OpType.EQ, i)
                        )),
                        record);
            }
        }
        assertEquals(bucket.size(), 10);
        bucket.printTable(schema);
    }

    private SkipListBucket mkBucket() {
        SkipListBucket bucket = new SkipListBucket();
        for (int i = 1; i < 11; i++) {
            Record record = Record.newRecord(schema);

            record.set(schema.column("poi_id"), i);
            record.set(schema.column("product_id"), i);
            record.set(schema.column("customer_id"), i * 203);
            record.set(schema.column("date"), i % 2);
            record.set(schema.column("trade_type"), i);
            record.set(schema.column("selling_price"), i);

            bucket.put(schema, record);
        }
        return bucket;
    }

    @Test
    public void deleteIndexPrefix() throws Exception {
        SkipListBucket bucket = mkBucket();
        List<Filter> filters = Arrays.asList(
                new Filter(schema.getUniqueIndexCids()[0], OpType.EQ, 1)
        );
        int n = bucket.delete(schema, filters);
        assertEquals(n, 5);
        assertEquals(bucket.size(), 5);

        n = bucket.delete(schema, filters);
        assertEquals(n, 0);

        bucket.printTable(schema);
    }

    @Test
    public void deleteIndex() throws Exception {
        SkipListBucket bucket = mkBucket();
        List<Filter> filters = Arrays.asList(
                new Filter(schema.getUniqueIndexCids()[0], OpType.EQ, 2 % 2),
                new Filter(schema.getUniqueIndexCids()[1], OpType.EQ, 2)
        );
        int n = bucket.delete(schema, filters);
        assertEquals(n, 1);
        assertEquals(bucket.size(), 9);

        Record record = bucket.get(schema, filters);
        assertEquals(record, null);

        n = bucket.delete(schema, filters);
        assertEquals(n, 0);

        bucket.printTable(schema);
    }

    @Test
    public void update() throws Exception {
        SkipListBucket bucket = mkBucket();
        List<Filter> filters = Arrays.asList(
                new Filter(schema.getUniqueIndexCids()[0], OpType.EQ, 2 % 2),
                new Filter(schema.getUniqueIndexCids()[1], OpType.EQ, 2)
        );
        int n = bucket.update(schema, filters,
                Arrays.asList(
                        new ColValue(1, 999),
                        new ColValue(2, 888)
                ));
        assertEquals(n, 1);
        assertEquals(bucket.size(), 10);

        bucket.printTable(schema);

        filters = Arrays.asList(
                new Filter(schema.getUniqueIndexCids()[0], OpType.EQ, 2 % 2),
                new Filter(schema.getUniqueIndexCids()[1], OpType.EQ, 8)
        );
        n = bucket.update(schema, filters,
                Arrays.asList(
                        new ColValue(1, 9999),
                        new ColValue(2, 777)
                ));
        assertEquals(n, 1);
        assertEquals(bucket.size(), 10);

        bucket.printTable(schema);
    }

    @Test
    public void scan() throws Exception {
        SkipListBucket bucket = mkBucket();

        List<Filter> filters = Arrays.asList(
                new Filter(schema.getUniqueIndexCids()[0], OpType.EQ, 0),
                new Filter(schema.getUniqueIndexCids()[1], OpType.EQ, 2)
        );
        List<Record> records = bucket.scan(schema, filters);
        assertEquals(records.size(), 1);

        filters = Arrays.asList(
                new Filter(schema.getUniqueIndexCids()[0], OpType.EQ, 0)
        );
        records = bucket.scan(schema, filters);
        assertEquals(records.size(), 5);

        filters = Arrays.asList(
                new Filter(5, OpType.EQ, 10)
        );
        records = bucket.scan(schema, filters);
        assertEquals(records.size(), 1);
        assertEquals(records.get(0).get(5), 10);

        bucket.printTable(schema);
    }

    @Test
    public void scanProjectionScanner1() throws Exception {
        Querier querier = new Querier(schema);

        SkipListBucket bucket = mkBucket();
        bucket.printTable(schema);

        Column col = new Column(1, "product_id", IntegerType);

        querier.addSelection(new Projection(col, "gid"));
        querier.addSelection(FunctionCall.checkAndCreate(FunctionRegistry.get("mul"), Arrays.asList(
                col,
                1L
        ), "gid_mod"));

        bucket.scan(schema, Collections.emptyList(), querier);
        querier.getResultSet().printTable();
    }

    @Test
    public void scanProjectionScanner2() throws Exception {
        Querier querier = new Querier(schema);

        SkipListBucket bucket = mkBucket();
        bucket.printTable(schema);

        Column col = schema.column("product_id");

        querier.addSelection(new Projection(col, null));
        querier.addSelection(new Projection(schema.column("customer_id"), "kid"));
        querier.addSelection(FunctionCall.checkAndCreate(FunctionRegistry.get("mul"), Arrays.asList(col, 2L), "gid_mul"));
        querier.addSelection(FunctionCall.checkAndCreate(FunctionRegistry.get("mod"), Arrays.asList(col, 3L), "gid_mod_3"));

        bucket.scan(schema, Collections.emptyList(), querier);

        querier.getResultSet().printTable();
    }

    @Test
    public void testInputTypeCheck() throws Exception {
        Querier querier = new Querier(schema);

        SkipListBucket bucket = mkBucket();
        bucket.printTable(schema);

        Column col = schema.column("product_id");

        querier.addSelection(new Projection(col, null));
        querier.addSelection(new Projection(schema.column("customer_id"), "kid"));
        querier.addSelection(FunctionCall.checkAndCreate(FunctionRegistry.get("mul"), Arrays.asList(col, Byte.valueOf("10")), "gid_mul"));
        querier.addSelection(FunctionCall.checkAndCreate(FunctionRegistry.get("mod"), Arrays.asList(col, Byte.valueOf("2")), "gid_mod"));

        bucket.scan(schema, Collections.emptyList(), querier);

        querier.getResultSet().printTable();
    }

    @Test
    public void scanAggregateScanner() throws Exception {
        Querier querier = new Querier(schema);

        SkipListBucket bucket = mkBucket();
        bucket.printTable(schema);

        Column col = new Column(1, "product_id", IntegerType);
        Column col2 = new Column(2, "customer_id", IntegerType);

        querier.addSelection("sum", Collections.singletonList(col), "sum_pid");
        querier.addSelection("count", Collections.singletonList(col2), "count_cid");
        querier.addSelection("avg", Collections.singletonList(col2), "avg_cid");
        querier.addSelection("sum", Collections.singletonList(col2), "sum_cid");

        bucket.scan(schema, Collections.emptyList(), querier);

        List<ImmutableRecord> resultRows = querier.getResultSet().resultRecords();
        ImmutableRecord resultRow = resultRows.get(0);
        assertEquals(resultRow.get(0), 55L);
        assertEquals(resultRow.get(1), 10);
        assertEquals(resultRow.get(2), 1116L);
        assertEquals(resultRow.get(3), 11165L);

        querier.getResultSet().printTable();
    }

    private SkipListBucket mkBucketRealData() {
        SkipListBucket bucket = new SkipListBucket();

        Record record;

        for (int i = 0; i < 10; i++) {
            record = Record.newRecord(schema);
            record.set(schema.column("poi_id"), 100100);
            record.set(schema.column("product_id"), 300100);
            record.set(schema.column("customer_id"), 33);
            record.set(schema.column("date"), 20190523 + i);
            record.set(schema.column("trade_type"), 1);
            record.set(schema.column("selling_price"), 228 + i * 10);
            bucket.put(schema, record);
        }

        for (int i = 0; i < 10; i++) {
            record = Record.newRecord(schema);
            record.set(schema.column("poi_id"), 100100);
            record.set(schema.column("product_id"), 300200);
            record.set(schema.column("customer_id"), 33);
            record.set(schema.column("date"), 20190523 + i);
            record.set(schema.column("trade_type"), 1);
            record.set(schema.column("selling_price"), 78 + i * 10);
            bucket.put(schema, record);
        }

        for (int i = 0; i < 10; i++) {
            record = Record.newRecord(schema);
            record.set(schema.column("poi_id"), 100100);
            record.set(schema.column("product_id"), 300300);
            record.set(schema.column("customer_id"), 33);
            record.set(schema.column("date"), 20190523 + i);
            record.set(schema.column("trade_type"), 1);
            record.set(schema.column("selling_price"), 58 + i * 10);
            bucket.put(schema, record);
        }

        for (int i = 0; i < 5; i++) {
            record = Record.newRecord(schema);
            record.set(schema.column("poi_id"), 100100);
            record.set(schema.column("product_id"), 300400);
            record.set(schema.column("customer_id"), 33);
            record.set(schema.column("date"), 20190526 + i);
            record.set(schema.column("trade_type"), 2);
            record.set(schema.column("selling_price"), 138 + i * 10);
            bucket.put(schema, record);
        }
        return bucket;
    }

    @Test
    public void scanAggregateScannerByGroup() throws Exception {
        Querier querier = new Querier(schema);

        SkipListBucket bucket = mkBucketRealData();
        bucket.printTable(schema);

        Column groupCol = schema.column(1);
        Column price_col = schema.column(5);

        List<Column> groupBy = Arrays.asList(groupCol);
        querier.setGroupBy(groupBy);

        querier.addSelection(new Projection(groupCol, null));
        querier.addSelection("count", Collections.singletonList(price_col), "count_price");
        querier.addSelection("avg", Collections.singletonList(price_col), "avg_price");
        querier.addSelection("sum", Collections.singletonList(price_col), "sum_price");
        querier.addSelection("max", Collections.singletonList(price_col), "max_price");
        querier.addSelection("min", Collections.singletonList(price_col), null);

        int dateCid = schema.cid("date");
        bucket.scan(schema, Arrays.asList(
                new Filter(dateCid, OpType.GT, 20190525),
                new Filter(dateCid, OpType.LT, 20190530)
        ), querier);

        List<ImmutableRecord> resultRows = querier.getResultSet().resultRecords();

        ImmutableRecord resultRow = resultRows.get(0);
        assertEquals(resultRow.get(0), 300100);
        assertEquals(resultRow.get(1), 4);
        assertEquals(resultRow.get(2), 273L);
        assertEquals(resultRow.get(3), 1092L);
        assertEquals(resultRow.get(4), 288);
        assertEquals(resultRow.get(5), 258);

        querier.getResultSet().printTable();
    }
}