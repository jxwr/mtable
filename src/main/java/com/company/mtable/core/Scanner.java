package com.company.mtable.core;

import com.company.mtable.schema.Schema;

import java.util.List;

public interface Scanner {

    void init(Schema schema);

    boolean handle(Schema schema, Record record) throws Exception;

    void finish(Schema schema) throws Exception;

    List<Filter> getFilters();
}
