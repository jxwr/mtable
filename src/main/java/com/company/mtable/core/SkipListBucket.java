package com.company.mtable.core;

import com.company.mtable.exception.InvalidPartitionFilterException;
import com.company.mtable.core.scanners.*;
import com.company.mtable.schema.Schema;

import java.util.*;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

public class SkipListBucket implements Bucket {

    private ConcurrentSkipListMap<IndexValue, Record> records;

    public SkipListBucket() {
        records = new ConcurrentSkipListMap<>();
    }

    @Override
    public void put(Schema schema, Record record) {
        IndexValue ck = record.uniqueIndexValue(schema);
        records.put(ck, record);
    }

    @Override
    public Record get(Schema schema, List<Filter> filters) throws InvalidPartitionFilterException {
        IndexValue indexValue = Filters.getFullIndexValue(schema, filters);
        return records.get(indexValue);
    }

    @Override
    public int delete(Schema schema, List<Filter> filters) {
        IndexValue lower = Filters.getLowestPrefix(schema, filters);
        IndexValue upper = Filters.getUpperPrefix(schema, filters);

        Set<IndexValue> deleteIndexValues = new HashSet<>();
        ConcurrentNavigableMap<IndexValue, Record> slice = slice(lower, upper, filters);

        for (Map.Entry<IndexValue, Record> entry : slice.entrySet()) {
            if (Filters.filterAll(schema, entry.getValue(), filters)) {
                deleteIndexValues.add(entry.getKey());
            }
        }

        for (IndexValue indexValue : deleteIndexValues) {
            records.remove(indexValue);
        }
        return deleteIndexValues.size();
    }

    private boolean isUpdateIndexValue(Schema schema, List<ColValue> values) {
        for (ColValue v : values) {
            int[] ids = schema.getUniqueIndexCids();
            for (int id : ids) {
                if (id == v.getCid()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int update(Schema schema, List<Filter> filters, List<ColValue> values) {
        boolean updatingIndexValue = isUpdateIndexValue(schema, values);

        if (updatingIndexValue) {
            return updateIndexValue(schema, filters, values);
        } else {
            return updateNonIndexValue(schema, filters, values);
        }
    }

    synchronized private int updateIndexValue(Schema schema, List<Filter> filters, List<ColValue> values) {
        IndexValue lower = Filters.getLowestPrefix(schema, filters);
        IndexValue upper = Filters.getUpperPrefix(schema, filters);

        if (lower.compareTo(upper) == 0) {
            Record record = records.get(lower);
            if (record == null)
                return 0;

            for (ColValue cv : values) {
                record.set(cv.getCid(), cv.getValue());
            }
            records.remove(lower);
            records.put(record.uniqueIndexValue(schema), record);
            return 1;
        }

        int affectedRows = 0;
        Map<IndexValue, Record> oldIndexToNewRecord = new HashMap<>();
        ConcurrentNavigableMap<IndexValue, Record> slice = slice(lower, upper, filters);

        for (Map.Entry<IndexValue, Record> entry : slice.entrySet()) {
            if (Filters.filterAll(schema, entry.getValue(), filters)) {
                for (ColValue cv : values) {
                    entry.getValue().set(cv.getCid(), cv.getValue());
                }
                oldIndexToNewRecord.put(entry.getKey(), entry.getValue());
                affectedRows++;
            }
        }

        // remove old index=>record and reinsert
        for (Map.Entry<IndexValue, Record> entry : oldIndexToNewRecord.entrySet()) {
            records.remove(entry.getKey());
            records.put(entry.getValue().uniqueIndexValue(schema), entry.getValue());
        }
        return affectedRows;
    }

    private int updateNonIndexValue(Schema schema, List<Filter> filters, List<ColValue> values) {
        IndexValue lower = Filters.getLowestPrefix(schema, filters);
        IndexValue upper = Filters.getUpperPrefix(schema, filters);

        if (lower.compareTo(upper) == 0) {
            Record record = records.get(lower);
            if (record == null)
                return 0;

            for (ColValue cv : values) {
                record.set(cv.getCid(), cv.getValue());
            }
            return 1;
        }

        int affectedRows = 0;
        ConcurrentNavigableMap<IndexValue, Record> slice = slice(lower, upper, filters);
        for (Record record : slice.values()) {
            if (Filters.filterAll(schema, record, filters)) {
                for (ColValue cv : values) {
                    record.set(cv.getCid(), cv.getValue());
                }
                affectedRows++;
            }
        }
        return affectedRows;
    }

    @Override
    public void scan(Schema schema, List<Filter> filters, Scanner scanner) {
        IndexValue lower = Filters.getLowestPrefix(schema, filters);
        IndexValue upper = Filters.getUpperPrefix(schema, filters);

        scanner.init(schema);

        if (lower.compareTo(upper) == 0) {
            Record record = records.get(lower);
            if (record != null) {
                scanner.handle(schema, record);
            }
            scanner.finish(schema);
            return;
        }

        ConcurrentNavigableMap<IndexValue, Record> slice = slice(lower, upper, filters);

        // printIndexValues(slice);
        for (Record record : slice.values()) {
            if (Filters.filterAll(schema, record, filters)) {
                if (!scanner.handle(schema, record)) {
                    scanner.finish(schema);
                    return;
                }
            }
        }

        scanner.finish(schema);
    }

    @Override
    public List<Record> scan(Schema schema, List<Filter> filters) {
        IndexValue lower = Filters.getLowestPrefix(schema, filters);
        IndexValue upper = Filters.getUpperPrefix(schema, filters);

        List<Record> results = new ArrayList<>();

        if (lower.compareTo(upper) == 0) {
            Record record = records.get(lower);
            if (record != null) {
                results.add(record);
            }
            return results;
        }

        ConcurrentNavigableMap<IndexValue, Record> slice = slice(lower, upper, filters);

        for (Record record : slice.values()) {
            if (Filters.filterAll(schema, record, filters)) {
                results.add(record);
            }
        }
        return results;
    }

    @Override
    public Collection<Record> values() {
        return records.values();
    }

    @Override
    public int size() {
        return records.size();
    }

    private ConcurrentNavigableMap<IndexValue, Record> slice(IndexValue lower, IndexValue upper, List<Filter> filters) {
        return filters == null ? records : records.subMap(lower, true, upper, true);
    }

    public void printTable(Schema schema, Filter... filters) {
        printTable(schema, Arrays.asList(filters));
    }

    public void printTable(Schema schema, List<Filter> filters) {
        scan(schema, filters, new TablePrinter());
    }

    public void printIndexValues(ConcurrentNavigableMap<IndexValue, Record> slice) {
        System.out.println(String.join(",\n",
                slice.keySet().stream().map(IndexValue::toString).collect(Collectors.toList())));
    }
}
