package com.company.mtable.core;

import com.company.mtable.schema.ColumnMeta;
import com.company.mtable.schema.Schema;

import java.util.List;

/**
 * Created by jxwr on 2019/6/18.
 */
public class AggregateScanner implements Scanner {

    public void addProjection(ColumnMeta cmeta) {

    }

    public void setGroupBy(List<ColumnMeta> groupBy) {

    }

    @Override
    public void init(Schema schema) {

    }

    @Override
    public boolean handle(Schema schema, Record record) {
        return false;
    }

    @Override
    public void finish(Schema schema) {

    }
}