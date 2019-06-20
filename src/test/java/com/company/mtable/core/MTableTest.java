package com.company.mtable.core;

import com.company.mtable.schema.Schema;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

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

}