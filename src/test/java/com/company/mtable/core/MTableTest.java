package com.company.mtable.core;

import com.company.mtable.schema.Column;
import com.company.mtable.schema.Schema;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.company.mtable.core.funcs.Funcs.*;
import static com.company.mtable.core.types.Types.IntegerType;
import static org.junit.Assert.*;

/**
 * Created by jxwr on 2019/6/21.
 */
public class MTableTest {
    private Schema schema;

    @Before
    public void setUp() throws Exception {
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
        schema.setUniqueIndexKeys(Arrays.asList("poi_id", "date", "product_id"));
        return schema;
    }

    @Test
    public void getput() throws Exception {
        MTable mtable = new MTable(schema);
        for (int j = 0; j < 2; j++) {
            for (int i = 1; i < 11; i++) {
                Record record = Record.newRecord(schema);

                record.set(schema.cid("poi_id"), i);
                record.set(schema.cid("product_id"), i);
                record.set(schema.cid("customer_id"), i);
                record.set(schema.cid("date"), i);
                record.set(schema.cid("trade_type"), i);
                record.set(schema.cid("selling_price"), i);

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
            record.set(schema.cid("poi_id"), 100100);
            record.set(schema.cid("product_id"), 300100);
            record.set(schema.cid("customer_id"), 33);
            record.set(schema.cid("date"), 20190523+i);
            record.set(schema.cid("trade_type"), 1);
            record.set(schema.cid("selling_price"), 228+i*10);
            table.put(record);
        }

        for (int i = 0; i < 10; i++) {
            record = Record.newRecord(schema);
            record.set(schema.cid("poi_id"), 100100);
            record.set(schema.cid("product_id"), 300200);
            record.set(schema.cid("customer_id"), 33);
            record.set(schema.cid("date"), 20190523+i);
            record.set(schema.cid("trade_type"), 1);
            record.set(schema.cid("selling_price"), 78+i*10);
            table.put(record);
        }

        for (int i = 0; i < 10; i++) {
            record = Record.newRecord(schema);
            record.set(schema.cid("poi_id"), 100100);
            record.set(schema.cid("product_id"), 300300);
            record.set(schema.cid("customer_id"), 33);
            record.set(schema.cid("date"), 20190523+i);
            record.set(schema.cid("trade_type"), 1);
            record.set(schema.cid("selling_price"), 58+i*10);
            table.put(record);
        }

        for (int i = 0; i < 5; i++) {
            record = Record.newRecord(schema);
            record.set(schema.cid("poi_id"), 100100);
            record.set(schema.cid("product_id"), 300400);
            record.set(schema.cid("customer_id"), 33);
            record.set(schema.cid("date"), 20190526+i);
            record.set(schema.cid("trade_type"), 2);
            record.set(schema.cid("selling_price"), 138+i*10);
            table.put(record);
        }
        return table;
    }

    @Test
    public void scanAggregateScannerByGroup() throws Exception {
        AggregateScanner scanner = new AggregateScanner();

        MTable table = mkTableRealData();
        table.printTable();

        Column groupCol = schema.getColumn(1);
        Column price_col = schema.getColumn(5);

        List<Column> groupBy = Arrays.asList(groupCol);
        scanner.setGroupBy(groupBy);

        scanner.addProjection(groupCol);
        scanner.addProjection(Count, Collections.singletonList(price_col), "count_price");
        scanner.addProjection(Avg, Collections.singletonList(price_col), "avg_price");
        scanner.addProjection(Sum, Collections.singletonList(price_col), "sum_price");
        scanner.addProjection(Max, Collections.singletonList(price_col), "max_price");
        scanner.addProjection(Min, Collections.singletonList(price_col), "min_price");

        int dateCid = schema.cid("date");
        table.scan(Arrays.asList(
                new Filter(schema.getPartitionColumn().getCid(), OpType.EQ, 100100),
                new Filter(dateCid, OpType.GT, 20190525),
                new Filter(dateCid, OpType.LT, 20190530)
        ), scanner);

        List<ResultRow> resultRows = scanner.getResultSet().resultRows();

        ResultRow resultRow = resultRows.get(0);
        assertEquals(resultRow.get(0), 300100);
        assertEquals(resultRow.get(1), 4);
        assertEquals(resultRow.get(2), 273L);
        assertEquals(resultRow.get(3), 1092L);
        assertEquals(resultRow.get(4), 288);
        assertEquals(resultRow.get(5), 258);

        scanner.getResultSet().printTable();
    }
}