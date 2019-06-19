package com.company.mtable.core;

import com.company.mtable.schema.Column;
import com.company.mtable.schema.Schema;

import java.util.List;

/**
 * Created by jxwr on 2019/6/19.
 */
public class AggregateScanner implements Scanner {

    private List<Projection> projections;

    private List<Column> groupBy;

    public void addProjection(AggragateFunc func, List<Object> params) {

    }

    public void addProjection(AggragateFunc func, List<Object> params, String as) {

    }

    public void setGroupBy(List<Column> groupBy) {

    }

    @Override
    public void init(Schema schema) {
        for (Projection projection : projections) {
            projection.init(schema);
        }
    }

    @Override
    public boolean handle(Schema schema, Record record) {
        int skipCount = 0;
        for (Projection projection : projections) {
            if (projection.shouldStop()) {
                skipCount++;
                continue;
            }
            boolean cont = projection.handle(schema, record);
            if (!cont) {
                projection.setStop(true);
            }
        }

        return skipCount < projections.size();
    }

    @Override
    public void finish(Schema schema) {
    }
}
