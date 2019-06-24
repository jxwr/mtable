package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/19.
 */
public class ShortType extends NumberType {
    @Override
    public String typeName() {
        return typeClass().getTypeName();
    }

    @Override
    public Class<?> typeClass() {
        return Short.TYPE;
    }

    @Override
    public Object value(Object value) {
        return ((Number) value).shortValue();
    }
}
