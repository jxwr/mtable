package com.sankuai.mpproduct.mtable.ql;

/**
 * Created by jxwr on 2019/6/13.
 */
public class Node {
    protected NodeType type;

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public NamedDictByKey asNamedDictByKey() {
        return (NamedDictByKey)this;
    }

    public NamedDictArray asNamedDictArray() {
        return (NamedDictArray)this;
    }

    public NamedField asNamedField() {
        return (NamedField)this;
    }
}
