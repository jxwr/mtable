package com.company.mtable.core;

import com.company.mtable.exception.InvalidUniqueIndexException;
import com.company.mtable.schema.Column;
import com.company.mtable.schema.Schema;

public class Record {

    private Object[] values;

    private Record(Object[] values) {
        this.values = values;
    }

    public static Record newRecord(Schema schema) {
        Object[] values = new Object[schema.getColumns().size()];
        return new Record(values);
    }

    public Object get(int cid) {
        return values[cid];
    }

    public void set(Column col, Object value) {
        int cid = col.getCid();
        this.values[cid] = col.getType().value(value);
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
