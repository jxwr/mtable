package com.company.mtable.core;

import com.company.mtable.schema.Schema;

public class Record {

    public Record(Object[] values) {
        this.values = values;
    }

    private Object[] values;

    public IndexValue uniqueIndexValue(Schema schema) {
        int[] cids = schema.getUniqueIndexCids();
        IndexValue ival = schema.newIndexValue();

        int i = 0;
        for (Integer cid : cids) {
            ival.setValue(i++, values[cid]);
        }

        return ival;
    }

    public Object getValue(int cid) {
        return values[cid];
    }

    public void setValue(int cid, Object value) {
        this.values[cid] = value;
    }

    public static Record newRecord(Schema schema) {
        Object[] values = new Object[schema.getColumns().size()];
        return new Record(values);
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
}
