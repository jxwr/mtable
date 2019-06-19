package com.company.mtable.core;

import com.company.mtable.core.types.DataType;
import com.company.mtable.schema.Column;
import com.company.mtable.schema.Schema;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jxwr on 2019/6/19.
 */
public class ProjectionScanner implements Scanner {

    private ResultSet resultSet = new ResultSet();

    public ProjectionScanner() {
        projections = new ArrayList<>();
    }

    private List<Projection> projections;

    public void addProjection(Column col) {
        projections.add(new ColumnProjection(col, null));
    }

    public void addProjection(Column col, String as) {
        projections.add(new ColumnProjection(col, as));
    }

    public void addProjection(SimpleFunc func, List<Object> params, String as) {
        projections.add(new SimpleFuncProjection(func, params, as));
    }

    @Override
    public void init(Schema schema) {
        for (int i = 0; i < projections.size(); i++) {
            Projection projection = projections.get(i);
            projection.init(schema);

            resultSet.addColumns(new Column(i, projection.name(), projection.outType()));
        }
    }

    @Override
    public boolean handle(Schema schema, Record record) {
        ResultRow row = new ResultRow(projections.size());

        for (int i = 0; i < projections.size(); i++) {
            Projection projection = projections.get(i);
            Object val = projection.handle(schema, record);
            row.set(i, val);
        }

        resultSet.addResultRow(row);

        return true;
    }

    @Override
    public void finish(Schema schema) {
        for (ResultRow row : resultSet.resultRows()) {
            System.out.println(row);
        }
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    private interface Projection {

        void init(Schema schema);

        Object handle(Schema schema, Record record);

        String name();

        DataType outType();
    }

    private static class ColumnProjection implements Projection {

        private String as;

        private Column col;

        public ColumnProjection(Column col, String as) {
            this.as = as;
            this.col = col;
        }

        @Override
        public void init(Schema schema) {

        }

        @Override
        public Object handle(Schema schema, Record record) {
            return record.get(col.getCid());
        }

        @Override
        public String name() {
            return as == null ? col.getName() : as;
        }

        @Override
        public DataType outType() {
            return col.getType();
        }
    }

    private static class SimpleFuncProjection implements Projection {

        private List<Object> params;

        private List<Integer> idxs;
        private List<Integer> cids;

        private Object[] paramValues;

        private SimpleFunc func;

        private String as;

        public SimpleFuncProjection(SimpleFunc func, List<Object> params, String as) {
            this.as = as;
            this.params = params;
            this.func = func;
            this.paramValues = new Object[params.size()];
            this.idxs = new ArrayList<>();
            this.cids = new ArrayList<>();
        }

        @Override
        public void init(Schema schema) {
            for (int i = 0; i < params.size(); i++) {
                Object obj = params.get(i);
                if (obj instanceof Column) {
                    idxs.add(i);
                    cids.add(((Column) obj).getCid());
                } else {
                    paramValues[i] = obj;
                }
            }
        }

        @Override
        public Object handle(Schema schema, Record record) {
            for (Integer i : idxs) {
                paramValues[i] = record.get(cids.get(i));
            }
            return func.apply(paramValues);
        }

        @Override
        public String name() {
            return as;
        }

        @Override
        public DataType outType() {
            return func.outType();
        }
    }
}
