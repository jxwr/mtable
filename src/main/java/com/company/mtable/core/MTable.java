package com.company.mtable.core;

import com.company.mtable.exception.InvalidPartitionFilterException;
import com.company.mtable.exception.NoPartitionFilterFoundException;
import com.company.mtable.schema.Schema;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MTable {

    private static int NUM_BUCKETS = 4;

    private Schema schema;

    private Map<Short, Bucket> buckets;

    public MTable(Schema schema) {
        this.schema = schema;
        this.buckets = new ConcurrentHashMap<>();
    }

    public Querier newQuerier() {
        return new Querier(schema);
    }

    public Record get(List<Filter> filters) {
        short bid = getBucketId(filters);

        Bucket bucket = buckets.get(bid);
        if (bucket == null) {
            return null;
        }

        return bucket.get(schema, filters);
    }

    public void put(Record record) {
        long pval = ((Number) record.get(schema.getPartitionColumn().getCid())).longValue();

        Bucket bucket = buckets.computeIfAbsent((short) (pval % NUM_BUCKETS), k -> new SkipListBucket());

        bucket.put(schema, record);
    }

    public int update(List<Filter> filters, List<ColValue> values) {
        short bid = getBucketId(filters);

        Bucket bucket = buckets.get(bid);
        if (bucket == null) {
            return 0;
        }

        return bucket.update(schema, filters, values);
    }

    public int delete(List<Filter> filters) {
        short bid = getBucketId(filters);

        Bucket bucket = buckets.get(bid);
        if (bucket == null) {
            return 0;
        }

        return bucket.delete(schema, filters);
    }

    public List<Record> scan(List<Filter> filters) {
        short bid = getBucketId(filters);

        Bucket bucket = buckets.get(bid);
        if (bucket == null) {
            return Collections.emptyList();
        }

        return bucket.scan(schema, filters);
    }

    public void scan(Scanner scanner) throws Exception {
        List<Filter> filters = scanner.getFilters();
        short bid = getBucketId(filters);

        Bucket bucket = buckets.get(bid);
        if (bucket == null) {
            return;
        }

        bucket.scan(schema, filters, scanner);
    }

    private short getBucketId(List<Filter> filters) {
        for (Filter f : filters) {
            if (f.getCid() == schema.getPartitionColumn().getCid()) {
                if (f.getOp() != OpType.EQ)
                    throw new InvalidPartitionFilterException();
                return (short) (((Number) f.getValue()).shortValue() % NUM_BUCKETS);
            }
        }

        throw new NoPartitionFilterFoundException();
    }

    public int size() {
        int count = 0;
        for (Bucket bucket : buckets.values()) {
            count += bucket.size();
        }
        return count;
    }

    public void printTable() {
        boolean first = true;
        for (Bucket bucket : buckets.values()) {
            ((SkipListBucket) bucket).printTable(schema, !first);
            first = false;
        }
    }
}
