package com.sankuai.mpproduct.mtable.ql;

import com.sankuai.mpproduct.mtable.ql.NamedDictArray;
import com.sankuai.mpproduct.mtable.ql.NamedDictByKey;
import com.sankuai.mpproduct.mtable.ql.NamedField;
import com.sankuai.mpproduct.mtable.ql.Node;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jxwr on 2019/6/13.
 */
public class AST {
    public static NamedDictArray makeMapList(String name, List<Node> nodes) {
        return new NamedDictArray(name, nodes);
    }

    public static NamedDictArray makeMapList(String name, Node... nodes) {
        return new NamedDictArray(name, Arrays.asList(nodes));
    }

    public static NamedDictByKey makeKMap(String name, String key, List<Node> nodes) {
        return new NamedDictByKey(name, key, nodes);
    }

    public static NamedDictByKey makeKMap(String name, String key, Node... nodes) {
        return new NamedDictByKey(name, key, Arrays.asList(nodes));
    }

    public static NamedField makeColumn(String columnName) {
        return new NamedField(columnName);
    }
}
