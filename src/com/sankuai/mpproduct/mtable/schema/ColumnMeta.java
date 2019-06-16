package com.sankuai.mpproduct.mtable.schema;

public class ColumnMeta {

    public ColumnMeta(int cid, String name, Class<?> type) {
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

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    private int cid;

    private String name;

    private Class<?> type;
}
