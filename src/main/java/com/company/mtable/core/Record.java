package com.company.mtable.core;

import com.company.mtable.exception.InvalidUniqueIndexException;
import com.company.mtable.schema.Column;
import com.company.mtable.schema.Schema;

public class Record implements ImmutableRecord {

    private Object[] values;

    private Record(Object[] values) {
        this.values = values;
    }

    public Record(int numCols) {
        this.values = new Object[numCols];
    }

    public static Record newRecord(Schema schema) {
        Object[] values = new Object[schema.getColumns().size()];
        return new Record(values);
    }

    @Override
    public Object get(int cid) {
        return values[cid];
    }

    @Override
    public Integer getInt(int i) {
        return (Integer) get(i);
    }

    @Override
    public Boolean getBoolean(int i) {
        return (Boolean) get(i);
    }

    @Override
    public Byte getByte(int i) {
        return (Byte) get(i);
    }

    @Override
    public Short getShort(int i) {
        return (Short) get(i);
    }

    @Override
    public Long getLong(int i) {
        return (Long) get(i);
    }

    @Override
    public String getString(int i) {
        return (String) get(i);
    }

    @Override
    public int numColumns() {
        return values.length;
    }

    public void set(Column col, Object value) {
        int cid = col.getCid();
        this.values[cid] = col.getType().value(value);
    }

    public void set(int cid, Object value) {
        this.values[cid] = value;
    }

    public IndexValue uniqueIndexValue(Schema schema) {
        int[] cids = schema.getUniqueIndexCids();
        IndexValue ival = schema.newIndexValue();

        int i = 0;
        for (Integer cid : cids) {
            if (values[cid] == null) {
                throw new InvalidUniqueIndexException();
            }
            ival.setValue(i++, values[cid]);
        }

        return ival;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (int i = 0; i < values.length; i++) {
            sb.append(values[i]);
            if (i < values.length - 1)
                sb.append(',');
        }
        sb.append(')');
        return sb.toString();
    }

    public Object get(Column col) {
        return col.getType().value(values[col.getCid()]);
    }
}
