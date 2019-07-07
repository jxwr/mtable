package com.company.mql.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jxwr on 2019/7/8.
 */
public class Document implements Node {

    private List<Definition> definitions = new ArrayList<>();

    public Document() {
    }

    public void addDefinition(Definition definition) {
        definitions.add(definition);
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }
}
