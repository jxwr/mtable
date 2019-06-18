package com.company.mtable.core;

import com.company.mtable.schema.Column;

import java.util.Collection;
import java.util.List;

/**
 * Created by jxwr on 2019/6/16.
 */
public class ResultSet {
    private List<Column> columns;

    private List<ResultRow> resultRows;

    public Collection<ResultRow> resultRows() {
        return resultRows;
    }
}
