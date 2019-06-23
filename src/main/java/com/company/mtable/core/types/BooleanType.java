package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/19.
 */
public class BooleanType implements DataType {
    @Override
    public String typeName() {
        return typeClass().getTypeName();
    }

    @Override
    public Class<Boolean> typeClass() {
        return Boolean.TYPE;
    }

    @Override
    public boolean acceptsType(DataType type) {
        return type == Types.BooleanType;
    }

    @Override
    public Object value(Object value) {
        return value;
    }

    @Override
    public boolean acceptsValue(Object value) {
        return (value instanceof Boolean);
    }
}
