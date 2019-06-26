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
        return Double.TYPE;
    }

    @Override
    public Object value(Object value) {
        return ((Number) value).doubleValue();
    }

    @Override
    public Double minValue() {
        return Double.MIN_VALUE;
    }

    @Override
    public Double maxValue() {
        return Double.MAX_VALUE;
    }

    @Override
    public boolean isConcreteType() {
        return true;
    }
}
