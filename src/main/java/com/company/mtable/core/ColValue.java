package com.company.mtable.core;

/**
 * Created by jxwr on 2019/6/14.
 */
public class ColValue {

    private int cid;
    private Object value;

    public ColValue(int cid, Object value) {
        this.cid = cid;
        this.value = value;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
