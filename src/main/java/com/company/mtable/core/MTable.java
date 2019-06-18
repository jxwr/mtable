package com.company.mtable.core;

import com.company.mtable.exception.InvalidPartitionFilterException;
import com.company.mtable.exception.NoPartitionFilterFoundException;
import com.company.mtable.schema.Schema;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MTable {

    private Schema schema;

    private Map<Object, Bucket> buckets;

    public MTable(Schema schema) {
        this.schema = schema;
        this.buckets = new ConcurrentHashMap<>();
    }

    public Record get(List<Filter> filters) {
        Filter f = popPartitionFilter(filters);

        Bucket bucket = buckets.get(f.getValue());
        if (bucket == null) {
            return null;
        }

        return bucket.get(schema, filters);
    }

    public void put(Record record) {
        IndexValue indexValue = record.uniqueIndexValue(schema);

        Bucket bucket = buckets.computeIfAbsent(indexValue, k -> new SkipListBucket());

        bucket.put(schema, record);
    }

    public int update(List<Filter> filters, List<ColValue> values) {
        Filter f = popPartitionFilter(filters);

        Bucket bucket = buckets.get(f.getValue());
        if (bucket == null) {
            return 0;
        }

        return bucket.update(schema, filters, values);
    }

    public int delete(List<Filter> filters) {
        Filter f = popPartitionFilter(filters);

        Bucket bucket = buckets.get(f.getValue());
        if (bucket == null) {
            return 0;
        }

        return bucket.delete(schema, filters);
    }

    public List<Record> scan(List<Filter> filters) {
        Filter f = popPartitionFilter(filters);

        Bucket bucket = buckets.get(f.getValue());
        if (bucket == null) {
            return Collections.emptyList();
        }

        return bucket.scan(schema, filters);
    }

    public void scan(List<Filters> filter, Scanner scanner) {

    }

    private Filter popPartitionFilter(List<Filter> filters) throws InvalidPartitionFilterException, NoPartitionFilterFoundException {
        for (Filter f : filters) {
            if (f.getCid() == schema.getPartitionColumn().getCid()) {
                if (f.getOp() != OpType.EQ)
                    throw new InvalidPartitionFilterException();
                filters.remove(f);
                return f;
            }
        }

        throw new NoPartitionFilterFoundException();
    }
}
