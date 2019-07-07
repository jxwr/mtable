package com.company.mql.ast;

import org.antlr.v4.runtime.Token;

/**
 * Created by jxwr on 2019/7/8.
 */
public class AstFactory {

    Document document;

    public AstFactory() {
        document = new Document();
    }

    public void addDefinition(Definition result) {
        this.document.addDefinition(result);
    }

    public Definition createOperationDefinition(SelectionSet result) {
        OperationDefinition operationDefinition = new OperationDefinition(result);
        return operationDefinition;
    }

    public SelectionSet createSelectionSet() {
        SelectionSet selectionSet = new SelectionSet();
        return selectionSet;
    }

    public Field createField(Token name) {
        Field field = new Field(name.getText());
        return field;
    }

    public Document getDocument() {
        return document;
    }
}
