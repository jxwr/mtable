package com.company.mtable.core;

import com.company.mtable.schema.Schema;

public abstract class Scanner {

    private ResultSet resultSet = new ResultSet();

    public void init(Schema schema) {
    }

    public abstract void handle(Schema schema, Record record);

    public ResultSet finish(Schema schema) {
        return resultSet;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }
}
