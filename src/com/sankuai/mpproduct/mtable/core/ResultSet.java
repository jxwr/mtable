package com.sankuai.mpproduct.mtable.core;

import com.sankuai.mpproduct.mtable.schema.ColumnMeta;

import java.util.List;

/**
 * Created by jxwr on 2019/6/16.
 */
public class ResultSet {
    public int getScanedRows() {
        return scanedRows;
    }

    public void setScanedRows(int scanedRows) {
        this.scanedRows = scanedRows;
    }

    public int getHandledRows() {
        return handledRows;
    }

    public void setHandledRows(int handledRows) {
        this.handledRows = handledRows;
    }

    public int getAffectedRows() {
        return affectedRows;
    }

    public void setAffectedRows(int affectedRows) {
        this.affectedRows = affectedRows;
    }

    public List<Object> getResults() {
        return results;
    }

    public void setResults(List<Object> results) {
        this.results = results;
    }

    private int scanedRows;

    private int handledRows;

    private int affectedRows;

    private List<ColumnMeta> columns;

    private List<Object> results;
}
