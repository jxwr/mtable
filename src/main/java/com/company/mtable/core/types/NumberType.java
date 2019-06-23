package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/19.
 */
public abstract class NumberType implements DataType {

    @Override
    public boolean acceptsType(DataType type) {
        return type instanceof NumberType;
    }

    @Override
    public boolean acceptsValue(Object value) {
        return value instanceof Number;
    }
}
