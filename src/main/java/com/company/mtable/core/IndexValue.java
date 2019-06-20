package com.company.mtable.core;

/**
 * Created by jxwr on 2019/6/16.
 */
public class IndexValue implements Comparable<IndexValue> {

    private Object[] values;

    public IndexValue(Object[] values) {
        this.values = values;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

    public void setValue(int i, Object value) {
        values[i] = value;
    }

    public Object getValue(int i) {
        return values[i];
    }

    @Override
    @SuppressWarnings("unchecked")
    public int compareTo(IndexValue that) {
        for (int i = 0; i < values.length; i++) {
            Comparable left = (Comparable) this.getValue(i);
            Comparable right = (Comparable) that.getValue(i);

            int cmp = left.compareTo(right);
            if (cmp != 0) return cmp;
        }
        return 0;
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

    @Override
    public boolean equals(Object that) {
        if (!that.getClass().equals(this.getClass())) {
            return false;
        }

        for (int i = 0; i < values.length; i++) {
            if (!this.getValue(i).equals(((IndexValue)that).getValue(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        if (values.length == 1)
            return values[0].hashCode();

        if (values.length == 2) {
            int result = (values[0].hashCode() ^ (values[0].hashCode() >>> 16));
            result = 31 * result + values[1].hashCode();
            return result;
        }

        if (values.length == 3) {
            int result = (values[0].hashCode() ^ (values[0].hashCode() >>> 16));
            result = 31 * result + values[1].hashCode();
            result = 31 * result + values[2].hashCode();
            return result;
        }

        if (values.length >= 4) {
            int result = (values[0].hashCode() ^ (values[0].hashCode() >>> 16));
            result = 31 * result + values[1].hashCode();
            result = 31 * result + values[2].hashCode();
            result = 31 * result + values[3].hashCode();
            return result;
        }

        return 0x777;
    }
}
