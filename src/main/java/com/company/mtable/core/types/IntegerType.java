package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/18.
 */
public class IntegerType extends NumberType {
    @Override
    public String typeName() {
        return typeClass().getTypeName();
    }

    @Override
    public Class<Integer> typeClass() {
        return Integer.TYPE;
    }

    @Override
    public Object value(Object value) {
        return ((Number) value).intValue();
    }
}
