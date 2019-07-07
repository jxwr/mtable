package com.company.mql.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jxwr on 2019/7/8.
 */
public class SelectionSet implements Node {
    private List<Selection> selections = new ArrayList<>();

    public void addSelection(Selection result) {
        selections.add(result);
    }

    public List<Selection> getSelections() {
        return selections;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Selection selection : selections) {
            sb.append(selection.toString()).append(" ");
        }
        return sb.toString();
    }
}
