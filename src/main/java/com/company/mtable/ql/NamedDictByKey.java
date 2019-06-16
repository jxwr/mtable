package com.company.mtable.ql;

import java.util.List;

/**
 * Created by jxwr on 2019/6/13.
 */
public class NamedDictByKey extends Node {

    private String name;

    private String key;

    private List<Node> nodes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public NamedDictByKey(String name, String key, List<Node> nodes) {
        this.name = name;
        this.key = key;
        this.nodes = nodes;
        this.type = NodeType.DICT_BY_KEY;
    }
}
