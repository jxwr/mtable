package com.company.mql.ast;

/**
 * Created by jxwr on 2019/7/8.
 */
public class OperationDefinition extends Definition {
    private SelectionSet selectionSet;

    public OperationDefinition(SelectionSet result) {
        this.selectionSet = result;
    }

    public SelectionSet getSelectionSet() {
        return selectionSet;
    }
}
