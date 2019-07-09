package com.company.mtable.schema;

import com.company.mtable.core.types.DataType;

public class Column {

    private final int cid;
    private final String name;
    private DataType type;

    public Column(int cid, String name, DataType type) {
        this.cid = cid;
        this.name = name;
        this.type = type;
    }

    public int getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
}
