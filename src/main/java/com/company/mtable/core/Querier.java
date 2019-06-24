package com.company.mtable.core;

import com.company.mtable.core.types.Types;
import com.company.mtable.schema.Column;
import com.company.mtable.schema.Schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jxwr on 2019/6/22.
 */
public class Querier implements Scanner {

    private static final IndexValue singleGroupIndexValue = new IndexValue(new Object[]{Integer.MAX_VALUE});
    private ResultSet resultSet = new ResultSet();
    private List<Column> groupBy;
    private List<Selection> selections = new ArrayList<>();
    private Map<IndexValue, List<SelectionHandler>> handlersGroup;
    private boolean isAggregateQuery;

    public void addSelection(Projection projection) {
        selections.add(projection);
    }

    public void addSelection(FunctionCall functionInvoke) {
        selections.add(functionInvoke);
    }

    public void setGroupBy(List<Column> groupBy) {
        this.groupBy = groupBy;
    }

    @Override
    public void init(Schema schema) {
        for (int i = 0; i < selections.size(); i++) {
            Selection selection = selections.get(i);

            resultSet.addColumns(new Column(i, selection.name(), selection.dataType()));
        }
        handlersGroup = new HashMap<>();

        // TODO: more check
        if (groupBy != null) {
            isAggregateQuery = true;
        } else {
            for (Selection selection : selections) {
                if (selection instanceof FunctionCall) {
                    if (((FunctionCall) selection).isAggregateFunction()) {
                        isAggregateQuery = true;
                        break;
                    }
                }
            }
        }
    }

    @Override
    public boolean handle(Schema schema, Record record) throws Exception {
        if (isAggregateQuery()) {
            IndexValue indexValue = groupIndexValue(record, groupBy);
            List<SelectionHandler> handlers = handlersGroup.computeIfAbsent(indexValue, k -> initializeHandlers());

            for (SelectionHandler handler : handlers) {
                handler.handle(record);
            }
        } else {
            ResultRow row = new ResultRow(selections.size());
            List<SelectionHandler> handlers = initializeHandlers();

            for (int i = 0; i < handlers.size(); i++) {
                SelectionHandler handler = handlers.get(i);
                Object val = handler.handle(record);
                row.set(i, val);
            }

            resultSet.addResultRow(row);
        }
        return true;
    }

    @Override
    public void finish(Schema schema) throws Exception {
        for (List<SelectionHandler> handlers : handlersGroup.values()) {
            ResultRow resultRow = new ResultRow(handlers.size());
            for (int i = 0; i < handlers.size(); i++) {
                resultRow.set(i, handlers.get(i).finish());
            }
            resultSet.addResultRow(resultRow);
        }

        // fix column types, anything but object
        if (resultSet.resultRows().size() > 0) {
            for (int i = 0; i < resultSet.columns().size(); i++) {
                Column col = resultSet.columns().get(i);
                if (col.getType() == Types.AnyType) {
                    col.setType(Types.fromClass(resultSet.resultRows().get(0).get(i).getClass()));
                }
            }
        }
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    private IndexValue groupIndexValue(Record record, List<Column> groupBy) {
        if (groupBy == null || groupBy.isEmpty()) {
            return singleGroupIndexValue;
        } else {
            Object[] values = new Object[groupBy.size()];
            for (int i = 0; i < groupBy.size(); i++) {
                Column col = groupBy.get(i);
                values[i] = record.get(col.getCid());
            }
            return new IndexValue(values);
        }
    }


    private boolean isAggregateQuery() {
        return isAggregateQuery;
    }

    private List<SelectionHandler> initializeHandlers() {
        List<SelectionHandler> handlers = new ArrayList<>();
        for (Selection projection : selections) {
            SelectionHandler handler = projection.getHandler(isAggregateQuery());
            handlers.add(handler);
        }
        return handlers;
    }
}
