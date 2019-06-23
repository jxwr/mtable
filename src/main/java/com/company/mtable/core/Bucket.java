package com.company.mtable.core;

import com.company.mtable.exception.InvalidPartitionFilterException;
import com.company.mtable.schema.Schema;

import java.util.Collection;
import java.util.List;

/**
 * Created by jxwr on 2019/6/18.
 */
public interface Bucket {

    void put(Schema schema, Record record);

    Record get(Schema schema, List<Filter> filters) throws InvalidPartitionFilterException;

    int delete(Schema schema, List<Filter> filters);

    int update(Schema schema, List<Filter> filters, List<ColValue> values);

    void scan(Schema schema, List<Filter> filters, Scanner scanner) throws Exception;

    List<Record> scan(Schema schema, List<Filter> filters);

    Collection<Record> values();

    int size();
}
