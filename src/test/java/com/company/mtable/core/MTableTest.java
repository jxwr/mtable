package com.company.mtable.core;

import com.company.mtable.schema.Column;
import com.company.mtable.schema.Schema;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.company.mtable.core.types.Types.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by jxwr on 2019/6/21.
 */
public class MTableTest {
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
        MTable mtable = new MTable(schema);
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

                mtable.put(record);

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

    private MTable mkTableRealData() {
        MTable table = new MTable(schema);

        Record record;

        for (int i = 0; i < 10; i++) {
            record = Record.newRecord(schema);
            record.set(schema.column("poi_id"), 100100);
            record.set(schema.column("product_id"), 300100);
            record.set(schema.column("customer_id"), 33);
            record.set(schema.column("date"), 20190523 + i);
            record.set(schema.column("trade_type"), 1);
            record.set(schema.column("selling_price"), 228 + i * 10);
            table.put(record);
        }

        for (int i = 0; i < 10; i++) {
            record = Record.newRecord(schema);
            record.set(schema.column("poi_id"), 100100);
            record.set(schema.column("product_id"), 300200);
            record.set(schema.column("customer_id"), 33);
            record.set(schema.column("date"), 20190523 + i);
            record.set(schema.column("trade_type"), 1);
            record.set(schema.column("selling_price"), 78 + i * 10);
            table.put(record);
        }

        for (int i = 0; i < 10; i++) {
            record = Record.newRecord(schema);
            record.set(schema.column("poi_id"), 100100);
            record.set(schema.column("product_id"), 300300);
            record.set(schema.column("customer_id"), 33);
            record.set(schema.column("date"), 20190523 + i);
            record.set(schema.column("trade_type"), 1);
            record.set(schema.column("selling_price"), 58 + i * 10);
            table.put(record);
        }

        for (int i = 0; i < 5; i++) {
            record = Record.newRecord(schema);
            record.set(schema.column("poi_id"), 100100);
            record.set(schema.column("product_id"), 300400L);
            record.set(schema.column("customer_id"), 33);
            record.set(schema.column("date"), 20190526 + i);
            record.set(schema.column("trade_type"), 2);
            record.set(schema.column("selling_price"), 138 + i * 10);
            table.put(record);
        }
        return table;
    }

    @Test
    public void scanAggregateScannerByGroup() throws Exception {
        Querier querier = new Querier(schema);

        MTable table = mkTableRealData();
        table.printTable();

        Column groupCol = schema.column(1);
        Column price_col = schema.column(5);

        List<Column> groupBy = Arrays.asList(groupCol);
        querier.setGroupBy(groupBy);

        querier.addSelection(new Projection(groupCol, null));

        querier.addSelection(FunctionCall.checkAndCreate(FunctionRegistry.get("count"), Collections.singletonList(price_col), null));
        querier.addSelection(FunctionCall.checkAndCreate(FunctionRegistry.get("avg"), Collections.singletonList(price_col), null));
        querier.addSelection(FunctionCall.checkAndCreate(FunctionRegistry.get("sum"), Collections.singletonList(price_col), "sum_price"));
        querier.addSelection(FunctionCall.checkAndCreate(FunctionRegistry.get("max"), Collections.singletonList(price_col), null));
        querier.addSelection(FunctionCall.checkAndCreate(FunctionRegistry.get("min"), Collections.singletonList(price_col), null));

        int dateCid = schema.cid("date");
        table.scan(Arrays.asList(
                new Filter(schema.getPartitionColumn().getCid(), OpType.EQ, 100100),
                new Filter(dateCid, OpType.GT, 20190525),
                new Filter(dateCid, OpType.LT, 20190530)
        ), querier);

        querier.getResultSet().columns().forEach(c -> System.out.println(c.getType()));

        List<ResultRow> resultRows = querier.getResultSet().resultRows();

        ResultRow resultRow = resultRows.get(0);
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
        Querier querier = new Querier(schema);

        MTable table = mkTableRealData();
        table.printTable();

        Column tradeTypeCol = schema.column(4);
        Column dateCol = schema.column(3);

        querier.addSelection(tradeTypeCol, null);
        querier.addSelection(dateCol, null);

        int dateCid = schema.cid("date");
        table.scan(Arrays.asList(
                new Filter(schema.getPartitionColumn().getCid(), OpType.EQ, 100100),
                new Filter(dateCid, OpType.GT, 20190525),
                new Filter(dateCid, OpType.LT, 20190530)
        ), querier);

        List<ResultRow> resultRows = querier.getResultSet().resultRows();

        ResultRow resultRow = resultRows.get(0);

        querier.getResultSet().printTable();
    }

    @Test
    public void getUDF() throws Exception {
        FunctionRegistry.registerUDF("udf_add", "Fn2",
                "public Object call(Object t1, Object t2) throws Exception { return (Integer)t1 + (Integer)t2;}"
        );

        Querier querier = new Querier(schema);

        MTable table = mkTableRealData();
        table.printTable();

        Column tradeTypeCol = schema.column(4);
        Column dateCol = schema.column(3);

        // select trade_type, udf_add(date, 1000000000) where date > xx and date < xxx
        querier.addSelection(tradeTypeCol, null);
        querier.addSelection("udf_add", Arrays.asList(dateCol, 1000000000), null);

        int dateCid = schema.cid("date");
        table.scan(Arrays.asList(
                new Filter(schema.getPartitionColumn().getCid(), OpType.EQ, 100100),
                new Filter(dateCid, OpType.GT, 20190525),
                new Filter(dateCid, OpType.LT, 20190530)
        ), querier);

        querier.getResultSet().printTable();
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

        Querier querier = new Querier(schema);

        MTable table = mkTableRealData();
        table.printTable();

        Column tradeTypeCol = schema.column(4);
        Column dateCol = schema.column(3);
        Column cumstomerCol = schema.column(2);

        // select trade_type, udf_add(date, 1000000000) where date > xx and date < xxx
        querier.addSelection(new Projection(tradeTypeCol, null));
        querier.addSelection(FunctionCall.checkAndCreate(FunctionRegistry.get("udf_min_sum"),
                Arrays.asList(dateCol, cumstomerCol), null));

        int dateCid = schema.cid("date");
        table.scan(Arrays.asList(
                new Filter(schema.getPartitionColumn().getCid(), OpType.EQ, 100100),
                new Filter(dateCid, OpType.GT, 20190525),
                new Filter(dateCid, OpType.LT, 20190530)
        ), querier);

        querier.getResultSet().printTable();
    }
}