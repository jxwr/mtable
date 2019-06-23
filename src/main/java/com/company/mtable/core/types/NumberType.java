package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/19.
 */
public class NumberType extends AbstractDataType {

    @Override
    public String typeName() {
        return "Number";
    }

    @Override
    public Class<?> typeClass() {
        return Number.class;
    }

    @Override
    public boolean acceptsType(DataType type) {
        return type instanceof NumberType;
    }

    @Override
    public Object value(Object value) {
        return value;
    }

    @Override
    public boolean acceptsValue(Object value) {
        return value instanceof Number;
    }
}
