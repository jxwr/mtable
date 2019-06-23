package com.company.mtable.core;

import com.company.mtable.core.fn.*;
import com.company.mtable.core.types.DataType;
import com.company.mtable.core.types.Types;
import com.company.mtable.exception.ParameterTypeError;
import com.company.mtable.schema.Column;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jxwr on 2019/6/23.
 */
public class FunctionCall implements Selection {

    private FunctionInfo funcInfo;

    private List<Object> params;

    private String name;

    public static FunctionCall checkAndCreate(FunctionInfo funcInfo, List<Object> params, String as) throws ParameterTypeError {
        String errorMsg = funcInfo.checkInputTypes(params);
        if (errorMsg != null) {
            throw new ParameterTypeError(errorMsg);
        }
        return new FunctionCall(funcInfo, params, as);
    }

    private FunctionCall(FunctionInfo funcInfo, List<Object> params, String as) {
        this.funcInfo = funcInfo;
        this.params = params;

        if (as == null) {
            StringBuilder sb = new StringBuilder();
            this.name = sb.append(funcInfo.name().toLowerCase())
                    .append('(')
                    .append(params.stream()
                            .map(Object::toString)
                            .collect(Collectors.joining(",")))
                    .append(')')
                    .toString();
        } else {
            this.name = as;
        }
    }

    public boolean isAggregateFunction() {
        return this.funcInfo.isAggregateFunction();
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public DataType dataType() {
        return this.funcInfo.dataType();
    }

    public List<DataType> inputTypes(List<Object> params) {
        List<DataType> types = new ArrayList<>(params.size());
        for (Object param : params) {
            DataType type;
            if (param instanceof Column) {
                type = ((Column) param).getType();
            } else {
                type = Types.fromClass(param.getClass());
            }
            types.add(type);
        }
        return types;
    }

    @Override
    public SelectionHandler getHandler(boolean aggregateQuery) {
        List<Integer> idxs = new ArrayList<>();
        List<Column> cols = new ArrayList<>();
        Object[] paramValues = new Object[params.size()];

        for (int i = 0; i < params.size(); i++) {
            Object obj = params.get(i);
            if (obj instanceof Column) {
                idxs.add(i);
                cols.add((Column) obj);
            } else {
                paramValues[i] = funcInfo.inputTypes().get(i).value(obj);
            }
        }

        if (aggregateQuery) {
            return new AggregateFunctionHandler(funcInfo.func(), paramValues, idxs, cols);
        } else {
            return new SimpleFunctionHandler(funcInfo.func(), paramValues, idxs, cols);
        }
    }

    private class SimpleFunctionHandler implements SelectionHandler {

        private Object[] params;

        private List<Integer> idxs;

        private List<Column> cols;

        private Object func;

        public SimpleFunctionHandler(Object func, Object[] params, List<Integer> idxs, List<Column> cols) {
            this.func = func;
            this.params = params;
            this.cols = cols;
            this.idxs = idxs;
        }

        @Override
        @SuppressWarnings("unchecked")
        public Object handle(Record record) throws Exception {
            for (Integer i : idxs) {
                Column col = cols.get(i);
                params[i] = record.get(col);
            }

            switch (params.length) {
                case 0: return ((Fn0)func).call();
                case 1: return ((Fn1)func).call(params[0]);
                case 2: return ((Fn2)func).call(params[0], params[1]);
                case 3: return ((Fn3)func).call(params[0], params[1], params[2]);
                case 4: return ((Fn4)func).call(params[0], params[1], params[2], params[3]);
                case 5: return ((Fn5)func).call(params[0], params[1], params[2], params[3], params[4]);
                default: return ((Fn)func).call(params);
            }
        }

        @Override
        public Object finish() {
            return null;
        }
    }

    private class AggregateFunctionHandler implements SelectionHandler {

        private Object[] params;

        private List<Integer> idxs;

        private List<Column> cols;

        private Object func;

        public AggregateFunctionHandler(Object func, Object[] params, List<Integer> idxs, List<Column> cols) {
            this.func = func;
            this.params = params;
            this.cols = cols;
            this.idxs = idxs;
        }

        @Override
        @SuppressWarnings("unchecked")
        public Object handle(Record record) throws Exception {
            for (Integer i : idxs) {
                Column col = cols.get(i);
                params[i] = record.get(col);
            }
            switch (params.length) {
                case 0: ((AFn0)func).handle(); break;
                case 1: ((AFn1)func).handle(params[0]); break;
                case 2: ((AFn2)func).handle(params[0], params[1]); break;
                case 3: ((AFn3)func).handle(params[0], params[1], params[2]); break;
                case 4: ((AFn4)func).handle(params[0], params[1], params[2], params[3]); break;
                case 5: ((AFn5)func).handle(params[0], params[1], params[2], params[3], params[4]); break;
                default: ((AFn)func).handle(params);
            }
            return null;
        }

        @Override
        public Object finish() throws Exception {
            switch (params.length) {
                case 0: return ((AFn0)func).finish();
                case 1: return ((AFn1)func).finish();
                case 2: return ((AFn2)func).finish();
                case 3: return ((AFn3)func).finish();
                case 4: return ((AFn4)func).finish();
                case 5: return ((AFn5)func).finish();
                default: return ((AFn)func).finish();
            }
        }
    }
}
