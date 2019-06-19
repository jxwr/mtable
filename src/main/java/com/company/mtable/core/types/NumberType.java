package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/19.
 */
public class NumberType extends NumericType {
    @Override
    public String typeName() {
        return "NumberType";
    }

    @Override
    public Class<?> type() {
        return Number.class;
    }

    public static Number value(Object obj) {
        return (Number) obj;
    }
}
