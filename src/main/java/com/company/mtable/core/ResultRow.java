package com.company.mtable.core;

/**
 * Created by jxwr on 2019/6/17.
 */
public class ResultRow {
    private Object[] values;

    public ResultRow(int numCols) {
        this.values = new Object[numCols];
    }

    public ResultRow(Object[] values) {
        this.values = values;
    }

    public Object get(int i) {
        return values[i];
    }

    public Integer getInt(int i) {
        return (Integer)get(i);
    }

    public Long getLong(int i) {
        return (Long)get(i);
    }
}
