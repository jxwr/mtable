package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/19.
 */
public class StringType extends AbstractDataType {
    @Override
    public String typeName() {
        return "String";
    }

    @Override
    public Class<?> typeClass() {
        return String.class;
    }

    @Override
    public boolean acceptsType(DataType type) {
        return true;
    }

    @Override
    public Object value(Object value) {
        return String.valueOf(value);
    }

    @Override
    public boolean acceptsValue(Object value) {
        return true;
    }
}
