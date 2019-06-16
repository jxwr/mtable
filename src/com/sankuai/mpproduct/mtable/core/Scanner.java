package com.sankuai.mpproduct.mtable.core;

import com.sankuai.mpproduct.mtable.schema.Schema;

public abstract class Scanner {

    private ResultSet resultSet = new ResultSet();

    public void init(Schema schema) {
    }

    protected abstract void handle(Schema schema, Record record);

    public ResultSet finish(Schema schema) {
        return resultSet;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }
}
