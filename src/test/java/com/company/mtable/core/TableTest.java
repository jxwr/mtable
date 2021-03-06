package com.company.mtable.core;

import com.company.mtable.*;
import com.company.mtable.schema.Column;
import com.company.mtable.schema.Schema;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.company.mtable.core.types.Types.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by jxwr on 2019/6/21.
 */
public class TableTest {
    private Schema schema;

    private static Schema newSchema() {
        Schema schema = new Schema("product_info");

        schema.addColumn("poi_id", IntegerType);
        schema.addColumn("product_id", IntegerType);
        schema.addColumn("customer_id", IntegerType);
        schema.addColumn("date", IntegerType);
        schema.addColumn("trade_type", ByteType);
        schema.addColumn("selling_price", IntegerType);
        schema.addColumn("product_name", StringType);

        schema.setPartitionKey("poi_id");
        schema.setUniqueIndexKeys(Arrays.asList("poi_id", "date", "product_id"));
        return schema;
    }

    @Before
    public void setUp() throws Exception {
        schema = newSchema();
    }

    @Test
    public void getput() throws Exception {
        Table mtable = new Table(schema);
        for (int j = 0; j < 2; j++) {
            for (int i = 1; i < 11; i++) {
                Record record = Record.newRecord(schema);

                record.set(schema.column("poi_id"), i);
                record.set(schema.column("product_id"), i);
                record.set(schema.column("customer_id"), i);
                record.set(schema.column("date"), i);
                record.set(schema.column("trade_type"), i);
                record.set(schema.column("selling_price"), i);
                record.set(schema.column("product_name"), "pname" + i);

                mtable.putRecord(record);

                assertEquals(mtable.get(
                        Arrays.asList(
                                new Filter(schema.getUniqueIndexCids()[0], OpType.EQ, i),
                                new Filter(schema.getUniqueIndexCids()[1], OpType.EQ, i),
                                new Filter(schema.getUniqueIndexCids()[2], OpType.EQ, i)
                        )),
                        record);
            }
        }
        assertEquals(mtable.size(), 10);
        mtable.printTable();
    }

    @Test
    public void put() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void scan() throws Exception {
    }

    private Table mkTableRealData() {
        Table table = new Table(schema);

        Record record;

        for (int i = 0; i < 10; i++) {
            record = Record.newRecord(schema);
            record.set(schema.column("poi_id"), 100100);
            record.set(schema.column("product_id"), 300100);
            record.set(schema.column("customer_id"), 33);
            record.set(schema.column("date"), 20190523 + i);
            record.set(schema.column("trade_type"), 1);
            record.set(schema.column("selling_price"), 228 + i * 10);
            table.putRecord(record);
        }

        for (int i = 0; i < 10; i++) {
            record = Record.newRecord(schema);
            record.set(schema.column("poi_id"), 100100);
            record.set(schema.column("product_id"), 300200);
            record.set(schema.column("customer_id"), 33);
            record.set(schema.column("date"), 20190523 + i);
            record.set(schema.column("trade_type"), 1);
            record.set(schema.column("selling_price"), 78 + i * 10);
            table.putRecord(record);
        }

        for (int i = 0; i < 10; i++) {
            record = Record.newRecord(schema);
            record.set(schema.column("poi_id"), 100100);
            record.set(schema.column("product_id"), 300300);
            record.set(schema.column("customer_id"), 33);
            record.set(schema.column("date"), 20190523 + i);
            record.set(schema.column("trade_type"), 1);
            record.set(schema.column("selling_price"), 58 + i * 10);
            table.putRecord(record);
        }

        for (int i = 0; i < 5; i++) {
            record = Record.newRecord(schema);
            record.set(schema.column("poi_id"), 100100);
            record.set(schema.column("product_id"), 300400L);
            record.set(schema.column("customer_id"), 33);
            record.set(schema.column("date"), 20190526 + i);
            record.set(schema.column("trade_type"), 2);
            record.set(schema.column("selling_price"), 138 + i * 10);
            table.putRecord(record);
        }
        return table;
    }

    @Test
    public void scanAggregateScannerByGroup() throws Exception {
        Table table = mkTableRealData();
        table.printTable();

        Querier querier = table.newQuerier();

        Column groupCol = schema.column(1);
        Column price_col = schema.column(5);

        List<Column> groupBy = Arrays.asList(groupCol);
        querier.setGroupBy(groupBy);

        querier.addSelection(new Projection(groupCol, null));

        querier.addSelection("count", Collections.singletonList(price_col), null);
        querier.addSelection("avg", Collections.singletonList(price_col), null);
        querier.addSelection("sum", Collections.singletonList(price_col), "sum_price");
        querier.addSelection("max", Collections.singletonList(price_col), null);
        querier.addSelection("min", Collections.singletonList(price_col), null);

        int dateCid = schema.cid("date");
        querier.addFilter(schema.getPartitionColumn().getCid(), OpType.EQ, 100100);
        querier.addFilter(dateCid, OpType.GT, 20190525);
        querier.addFilter(dateCid, OpType.LT, 20190530);

        table.scan(querier);

        querier.getResultSet().columns().forEach(c -> System.out.println(c.getType()));

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

    @Test
    public void scanAggregateScannerType0() throws Exception {
        Table table = mkTableRealData();
        table.printTable();

        Querier querier = table.newQuerier();

        Column tradeTypeCol = schema.column(4);
        Column dateCol = schema.column(3);

        querier.addSelection(tradeTypeCol, null);
        querier.addSelection(dateCol, null);

        int dateCid = schema.cid("date");
        querier.addFilter(schema.getPartitionColumn().getCid(), OpType.EQ, 100100);
        querier.addFilter(dateCid, OpType.GT, 20190525);
        querier.addFilter(dateCid, OpType.LT, 20190530);

        table.scan(querier);

        List<ImmutableRecord> resultRows = querier.getResultSet().resultRecords();

        ImmutableRecord resultRow = resultRows.get(0);

        querier.getResultSet().printTable();
    }

    @Test
    public void testFastPath() throws Exception {
        Table table = mkTableRealData();
        table.printTable();

        Querier querier = table.newQuerier();

        querier.addFilter("poi_id", OpType.EQ, 100100);
        querier.addFilter("date", OpType.GT, 20190525);
        querier.addFilter("date", OpType.LT, 20190530);

        table.scan(querier);
        ResultSet resultSet = querier.getResultSet();
        resultSet.printTable();

        assertEquals(resultSet.resultSize(), 16);
        for (int i = 0; i < resultSet.resultSize(); i++) {
            ImmutableRecord record = resultSet.resultRecords().get(i);
            assertTrue(record.getInt(3) < 20190530);
            assertTrue(record.getInt(3) > 20190525);
            assertTrue(record.getInt(0) == 100100);
        }
    }

    @Test
    public void getUDF() throws Exception {
        FunctionRegistry.registerUDF("udf_add", "Fn2",
                "public Object call(Object t1, Object t2) throws Exception { return (Integer)t1 + (Integer)t2;}"
        );

        Table table = mkTableRealData();
        table.printTable();

        Querier querier = table.newQuerier();

        Column tradeTypeCol = schema.column(4);
        Column dateCol = schema.column(3);

        // select trade_type, udf_add(date, 1000000000) where date > xx and date < xxx
        querier.addSelection(tradeTypeCol, null);
        querier.addSelection("udf_add", Arrays.asList(dateCol, 1000000000), null);

        int dateCid = schema.cid("date");
        querier.addFilter(schema.getPartitionColumn().getCid(), OpType.EQ, 100100);
        querier.addFilter(dateCid, OpType.GT, 20190525);
        querier.addFilter(dateCid, OpType.LT, 20190530);

        table.scan(querier);
        querier.getResultSet().printTable();

        assertEquals(querier.getResultSet().resultSize(), 16);
    }

    @Test
    public void testFuncRecordParam() throws Exception {
        Table table = mkTableRealData();
        table.printTable();

        FunctionRegistry.register("get_id", UDFRecordId.class);

        Querier querier = table.newQuerier();

        querier.addSelection("get_id", Arrays.asList(FunctionCall.PARAM_RECORD, 0), "poi_id");

        int dateCid = schema.cid("date");
        querier.addFilter(schema.getPartitionColumn().getCid(), OpType.EQ, 100100);
        querier.addFilter(dateCid, OpType.GT, 20190525);
        querier.addFilter(dateCid, OpType.LT, 20190530);

        table.scan(querier);

        ResultSet resultSet = querier.getResultSet();
        resultSet.printTable();

        assertEquals(resultSet.resultSize(), 16);
        for (int i = 0; i < resultSet.resultSize(); i++) {
            ImmutableRecord record = resultSet.resultRecords().get(i);
            assertTrue(record.getInt(0) == 100100);
        }
    }

    @Test
    public void getUDAF() throws Exception {
        FunctionRegistry.registerUDF("udf_min_sum", "AFn2",
                "" +
                        "int min1 = Integer.MAX_VALUE;\n" +
                        "int min2 = Integer.MAX_VALUE;\n" +
                        "public void handle(Object t1, Object t2) throws Exception {\n" +
                        "    if (min1 > (Integer)t1) {min1 = (Integer)t1;}\n" +
                        "    if (min2 > (Integer)t2) {min2 = (Integer)t2;}\n" +
                        "}\n" +
                        "public Object finish() throws Exception { \n" +
                        "    return min1+min2;\n" +
                        "}\n"
        );

        Table table = mkTableRealData();
        table.printTable();

        Querier querier = table.newQuerier();

        Column tradeTypeCol = schema.column(4);
        Column dateCol = schema.column(3);
        Column cumstomerCol = schema.column(2);

        // select trade_type, udf_add(date, 1000000000) where date > xx and date < xxx
        querier.addSelection(tradeTypeCol, null);
        querier.addSelection("udf_min_sum", Arrays.asList(dateCol, cumstomerCol), null);

        int dateCid = schema.cid("date");
        querier.addFilter(schema.getPartitionColumn().getCid(), OpType.EQ, 100100);
        querier.addFilter(dateCid, OpType.GT, 20190525);
        querier.addFilter(dateCid, OpType.LT, 20190530);

        table.scan(querier);

        querier.getResultSet().printTable();
    }

    @Test
    public void testSchemaFromType() {
        Schema schema = Schema.fromClass("productInfo", ProductInfo.class);

        Table table = new Table(schema);

        for (int i = 0; i < 10; i++) {
            ProductInfo info = new ProductInfo();
            info.poiId = 100100;
            info.productId = 300100 + i;
            info.productName = "pname" + i;
            info.sellingPrice = 228 + i * 10;
            info.date = 20190523 + i;
            info.customerId = 33;
            table.put(info);
        }

        table.printTable();
    }
}