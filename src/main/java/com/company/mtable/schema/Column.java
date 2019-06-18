package com.company.mtable.schema;

import com.company.mtable.core.types.DataType;

public class Column {

    public Column(int cid, String name, DataType type) {
        this.cid = cid;
        this.name = name;
        this.type = type;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    private int cid;

    private String name;

    private DataType type;
}
