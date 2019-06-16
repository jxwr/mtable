package com.sankuai.mpproduct.mtable.ql;

public class NamedField extends Node {
    private String name;

    public NamedField(String columnName) {
        this.name = columnName;
        this.type = NodeType.FIELD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
