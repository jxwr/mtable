package com.company.mtable.ql;

import java.util.List;

/**
 * Created by jxwr on 2019/6/13.
 */
public class NamedDictArray extends Node {

    private String name;

    private List<Node> nodes;

    public NamedDictArray(String name, List<Node> nodes) {
        this.name = name;
        this.nodes = nodes;
        this.type = NodeType.DICT_ARRAY;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = this.nodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
