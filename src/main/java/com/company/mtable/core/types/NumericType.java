package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/18.
 */
public class NumericType implements DataType {
    @Override
    public String typeName() {
        return "NumericType";
    }

    @Override
    public Class<?> type() {
        return Number.class;
    }
}
