package com.company.mtable.core;

import com.company.mtable.schema.Schema;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

public class Bucket {

    private ConcurrentSkipListMap<IndexValue, Record> records;

    public Bucket() {
        records = new ConcurrentSkipListMap<>();
    }

    public void put(Schema schema, Record record) {
        IndexValue ck = record.uniqueIndexValue(schema);
        records.put(ck, record);
    }

    private ConcurrentNavigableMap<IndexValue, Record> sliceByFilters(IndexValue lower, IndexValue upper, List<Filter> filters) {
        ConcurrentNavigableMap<IndexValue, Record> slice;
        if (filters == null)
            slice = records;
        else
            slice = records.subMap(lower, true, upper, true);
        return slice;
    }

    public int update(Schema schema, List<ColValue> values, List<Filter> filters) {
        IndexValue lower = Filters.getLowestPrefix(schema, filters);
        IndexValue upper = Filters.getUpperPrefix(schema, filters);

        if (lower.compareTo(upper) == 0) {
            Record record = records.get(lower);
            if (record == null)
                return 0;

            for (ColValue cv : values) {
                record.setValue(cv.getCid(), cv.getValue());
            }
            return 1;
        }

        int affectedRows = 0;
        ConcurrentNavigableMap<IndexValue, Record> slice = sliceByFilters(lower, upper, filters);
        for (Record record : slice.values()) {
            if (Filters.filterAll(schema, record, filters)) {
                for (ColValue cv : values)
                    record.setValue(cv.getCid(), cv.getValue());
                affectedRows++;
            }
        }
        return affectedRows;
    }

    public ResultSet scan(Schema schema, List<Filter> filters, Scanner scanner) {
        IndexValue lower = Filters.getLowestPrefix(schema, filters);
        IndexValue upper = Filters.getUpperPrefix(schema, filters);

        ResultSet resultSet = scanner.getResultSet();

        scanner.init(schema);

        if (lower.compareTo(upper) == 0) {
            Record record = records.get(lower);
            if (record != null) {
                scanner.handle(schema, record);
                resultSet.setAffectedRows(1);
            }
            scanner.finish(schema);
            return scanner.getResultSet();
        }

        ConcurrentNavigableMap<IndexValue, Record> slice = sliceByFilters(lower, upper, filters);

        // printIndexValues(slice);
        int affectedRows = 0;
        for (Record record : slice.values()) {
            if (Filters.filterAll(schema, record, filters)) {
                scanner.handle(schema, record);
                affectedRows++;
            }
        }
        resultSet.setAffectedRows(affectedRows);

        scanner.finish(schema);
        return resultSet;
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
