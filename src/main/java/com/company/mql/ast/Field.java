package com.company.mql.ast;

import org.antlr.v4.runtime.Token;

/**
 * Created by jxwr on 2019/7/8.
 */
public class Field extends Selection {
    private String name;
    private SelectionSet selectionSet;
    public Field(String name) {
        this.name = name;
    }

    public void setSelectionSeet(SelectionSet selectionSeet) {
        this.selectionSet = selectionSeet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SelectionSet getSelectionSet() {
        return selectionSet;
    }

    @Override
    public String toString() {
        if (selectionSet == null) {
            return name;
        } else {
            return name + " ( " + selectionSet.toString() + ")";
        }
    }
}
