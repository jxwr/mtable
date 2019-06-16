package com.company.mtable.core;

import com.company.mtable.schema.Schema;

/**
 * Created by jxwr on 2019/6/16.
 */
public class Maker {

    private Schema schema;

    public Maker(Schema schema) {
        this.schema = schema;
    }

    public ColValue colValue(String colName, Object value) {
        return new ColValue(schema.cid(colName), value);
    }

    public Filter filter(String colName, OpType op, Comparable obj) {
        return new Filter(schema.cid(colName), op, obj);
    }
}
