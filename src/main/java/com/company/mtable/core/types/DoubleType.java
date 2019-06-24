package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/25.
 */
public class DoubleType extends NumberType {

    @Override
    public String typeName() {
        return typeClass().getTypeName();
    }

    @Override
    public Class<Double> typeClass() {
        return Double.class;
    }

    @Override
    public Object value(Object value) {
        return ((Number) value).doubleValue();
    }
}
