package com.company.mtable.core;

/**
 * Created by jxwr on 2019/6/14.
 */
public class Filter {

    private int cid;
    private OpType op;
    private Comparable value;

    public Filter(int cid, OpType op, Comparable value) {
        this.cid = cid;
        this.op = op;
        this.value = value;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public OpType getOp() {
        return op;
    }

    public void setOp(OpType op) {
        this.op = op;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Comparable value) {
        this.value = value;
    }

    @SuppressWarnings("unchecked")
    public boolean check(Object val) {
        Comparable cval = (Comparable) val;

        switch (op) {
            case EQ:
                return val.equals(value);
            case LT:
                return cval.compareTo(value) < 0;
            case GT:
                return cval.compareTo(value) > 0;
            case LTE:
                return cval.compareTo(value) <= 0;
            case GTE:
                return cval.compareTo(value) >= 0;
            default:
                return false;
        }
    }
}
