package com.company.mtable.core;

import com.company.mtable.schema.Schema;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
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

        schema.addColumn("poi_id", Integer.class);
        schema.addColumn("product_id", Integer.class);
        schema.addColumn("customer_id", Integer.class);
        schema.addColumn("date", Integer.class);
        schema.addColumn("trade_type", Integer.class);
        schema.addColumn("selling_price", Integer.class);

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
            record.set(schema.cid("customer_id"), i);
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
    public void scanScanner() throws Exception {
    }

}