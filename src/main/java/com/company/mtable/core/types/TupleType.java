package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/21.
 */
public class TupleType implements DataType {
    @Override
    public String typeName() {
        return "Tuple";
    }

    @Override
    public Class<?> type() {
        return Object.class;
    }
}
