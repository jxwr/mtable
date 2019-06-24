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
        return (Integer) get(i);
    }

    public Long getLong(int i) {
        return (Long) get(i);
    }

    public void set(int i, Object val) {
        this.values[i] = val;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (int i = 0; i < values.length; i++) {
            sb.append(values[i]);
            if (i < values.length - 1) {
                sb.append(',');
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
