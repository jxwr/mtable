package com.company.mtable.core;

import com.company.mtable.core.types.DataType;
import com.company.mtable.schema.Column;
import com.company.mtable.schema.Schema;

import java.util.*;

/**
 * Created by jxwr on 2019/6/19.
 */
public class AggregateScanner implements Scanner {

    private ResultSet resultSet = new ResultSet();

    private List<Column> groupBy;

    private List<Projection> definitionProjections;

    private Map<IndexValue, List<Projection>> projectionsGroup;

    public AggregateScanner() {
        this.definitionProjections = new ArrayList<>();
    }

    public void addProjection(Column col) {
        definitionProjections.add(new ColumnProjection(col, null));
    }

    public void addProjection(Column col, String as) {
        definitionProjections.add(new ColumnProjection(col, as));
    }

    public void addProjection(AggragateFunc func, List<Object> params, String as) {
        AggragateFunc copy = func.copy();
        copy.init();
        definitionProjections.add(new AggragateFuncProjection(copy, params, as));
    }

    public void setGroupBy(List<Column> groupBy) {
        this.groupBy = groupBy;
    }

    @Override
    public void init(Schema schema) {
        for (int i = 0; i < definitionProjections.size(); i++) {
            Projection projection = definitionProjections.get(i);
            projection.init();

            resultSet.addColumns(new Column(i, projection.name(), projection.outType()));
        }
        if (groupBy != null) {
            projectionsGroup = new HashMap<>();
        }
    }

    @Override
    public boolean handle(Schema schema, Record record) {
        if (groupBy != null) {
            IndexValue indexValue = getGroupIndexValue(record, groupBy);
            List<Projection> projections = projectionsGroup.computeIfAbsent(indexValue, k -> cloneProjections());

            for (Projection projection : projections) {
                projection.handle(schema, record);
            }
        } else {
            for (Projection projection : definitionProjections) {
                projection.handle(schema, record);
            }
        }
        return true;
    }

    @Override
    public void finish(Schema schema) {
        if (groupBy != null) {
            for (List<Projection> projs : projectionsGroup.values()) {
                ResultRow resultRow = new ResultRow(projs.size());
                for (int i = 0; i < projs.size(); i++) {
                    resultRow.set(i, projs.get(i).finish(schema));
                }
                resultSet.addResultRow(resultRow);
            }
        } else {
            ResultRow resultRow = new ResultRow(definitionProjections.size());
            for (int i = 0; i < definitionProjections.size(); i++) {
                resultRow.set(i, definitionProjections.get(i).finish(schema));
            }
            resultSet.addResultRow(resultRow);
        }
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    private List<Projection> cloneProjections() {
        ArrayList<Projection> projs = new ArrayList<>(definitionProjections.size());
        for (Projection proj : definitionProjections) {
            Projection copy = proj.copy();
            copy.init();
            projs.add(copy);
        }
        return projs;
    }

    private IndexValue getGroupIndexValue(Record record, List<Column> groupBy) {
        return null;
    }

    private interface Projection {

        void init();

        boolean handle(Schema schema, Record record);

        Object finish(Schema schema);

        String name();

        DataType outType();

        Projection copy();
    }

    private class ColumnProjection implements Projection {

        private String as;

        private Column col;

        private Record currentRecord;

        public ColumnProjection(Column col, String as) {
            this.as = as;
            this.col = col;
        }

        @Override
        public void init() {

        }

        @Override
        public boolean handle(Schema schema, Record record) {
            this.currentRecord = record;
            return true;
        }

        @Override
        public Object finish(Schema schema) {
            this.currentRecord.get(col.getCid());
            return null;
        }

        @Override
        public String name() {
            return as == null ? col.getName() : as;
        }

        @Override
        public DataType outType() {
            return col.getType();
        }

        @Override
        public Projection copy() {
            ColumnProjection copy = new ColumnProjection(this.col, this.as);
            copy.init();
            return copy;
        }
    }

    private class AggragateFuncProjection implements Projection {
        private List<Object> params;

        private List<Integer> idxs;
        private List<Integer> cids;

        private Object[] paramValues;

        private AggragateFunc func;

        private String as;

        private boolean stop = false;

        public AggragateFuncProjection(AggragateFunc func, List<Object> params, String as) {
            this.as = as;
            this.params = params;
            this.func = func;
            this.paramValues = new Object[params.size()];
            this.idxs = new ArrayList<>();
            this.cids = new ArrayList<>();
        }

        @Override
        public void init() {
            for (int i = 0; i < params.size(); i++) {
                Object obj = params.get(i);
                if (obj instanceof Column) {
                    idxs.add(i);
                    cids.add(((Column) obj).getCid());
                } else {
                    paramValues[i] = obj;
                }
            }
            func.init();
        }

        @Override
        public boolean handle(Schema schema, Record record) {
            for (Integer i : idxs) {
                paramValues[i] = record.get(cids.get(i));
            }
            return func.handle(paramValues);
        }

        @Override
        public Object finish(Schema schema) {
            return func.finish();
        }

        @Override
        public String name() {
            return as;
        }

        @Override
        public DataType outType() {
            return func.outType();
        }

        @Override
        public Projection copy() {
            AggragateFuncProjection copy = new AggragateFuncProjection(this.func.copy(), this.params, this.as);
            copy.init();
            return copy;
        }
    }
}
