package com.company.mtable.core;


import com.company.mtable.schema.Column;
import com.company.mtable.schema.Schema;
import static com.company.mtable.core.types.Types.*;
import static com.company.mtable.core.funcs.Funcs.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jxwr on 2019/6/18.
 */
public class BucketTest {
    private Schema schema;

    @Before
    public void setup() {
        schema = newSchema();
    }

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

    @Test
    public void getput() throws Exception {
        SkipListBucket bucket = new SkipListBucket();
        for (int j = 0; j < 2; j++) {
            for (int i = 1; i < 11; i++) {
                Record record = Record.newRecord(schema);

                record.set(schema.cid("poi_id"), i);
                record.set(schema.cid("product_id"), i);
                record.set(schema.cid("customer_id"), i);
                record.set(schema.cid("date"), i);
                record.set(schema.cid("trade_type"), i);
                record.set(schema.cid("selling_price"), i);

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

            record.set(schema.cid("poi_id"), i);
            record.set(schema.cid("product_id"), i);
            record.set(schema.cid("customer_id"), i * 203);
            record.set(schema.cid("date"), i%2);
            record.set(schema.cid("trade_type"), i);
            record.set(schema.cid("selling_price"), i);

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
                new Filter(schema.getUniqueIndexCids()[0], OpType.EQ, 2%2),
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
                new Filter(schema.getUniqueIndexCids()[0], OpType.EQ, 2%2),
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
                new Filter(schema.getUniqueIndexCids()[0], OpType.EQ, 2%2),
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
        ProjectionScanner scanner = new ProjectionScanner();

        SkipListBucket bucket = mkBucket();
        bucket.printTable(schema);

        Column col = new Column(1, "product_id", IntegerType);

        scanner.addProjection(col, "gid");
        scanner.addProjection(Mul, Arrays.asList(
                col,
                2
        ), "gid_mod");

        bucket.scan(schema, Collections.emptyList(), scanner);
    }

    @Test
    public void scanProjectionScanner2() throws Exception {
        ProjectionScanner scanner = new ProjectionScanner();

        SkipListBucket bucket = mkBucket();
        bucket.printTable(schema);

        Column col = new Column(1, "product_id", IntegerType);
        Column col2 = new Column(2, "customer_id", IntegerType);

        scanner.addProjection(col);
        scanner.addProjection(col2, "kid");
        scanner.addProjection(Mul, Arrays.asList(
                col,
                2L
        ), "gid_mul");

        scanner.addProjection(Mod, Arrays.asList(
                col,
                3
        ), "gid_mod_3");

        bucket.scan(schema, Collections.emptyList(), scanner);

        scanner.getResultSet().printTable();
    }

    @Test
    public void scanAggregateScanner() throws Exception {
        AggregateScanner scanner = new AggregateScanner();

        SkipListBucket bucket = mkBucket();
        bucket.printTable(schema);

        Column col = new Column(1, "product_id", IntegerType);
        Column col2 = new Column(2, "customer_id", IntegerType);

        //scanner.addProjection(col);
        //scanner.addProjection(col2, "kid");
        scanner.addProjection(Sum, Arrays.asList(
                col,
                2L
        ), "sum_pid");

        scanner.addProjection(Count, Arrays.asList(
                col2,
                3
        ), "count_cid");

        scanner.addProjection(Avg, Arrays.asList(
                col2,
                3
        ), "avg_cid");

        scanner.addProjection(Sum, Arrays.asList(
                col2,
                3
        ), "sum_cid");

        bucket.scan(schema, Collections.emptyList(), scanner);

        scanner.getResultSet().printTable();
    }
}