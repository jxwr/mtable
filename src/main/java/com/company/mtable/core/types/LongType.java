package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/18.
 */
public class LongType extends NumberType {

    @Override
    public String typeName() {
        return typeClass().getTypeName();
    }

    @Override
    public Class<Long> typeClass() {
        return Long.TYPE;
    }

    @Override
    public Object value(Object value) {
        return ((Number) value).longValue();
    }

    @Override
    public Long minValue() {
        return Long.MIN_VALUE;
    }

    @Override
    public Long maxValue() {
        return Long.MAX_VALUE;
    }

    @Override
    public boolean isConcreteType() {
        return true;
    }
}
