package com.company.mtable.core.types;

import java.util.Date;

/**
 * Created by jxwr on 2019/6/19.
 */
public class DateType implements DataType {
    @Override
    public String typeName() {
        return "Date";
    }

    @Override
    public Class<?> typeClass() {
        return Date.class;
    }

    @Override
    public boolean acceptsType(DataType type) {
        return type == Types.DateType;
    }

    @Override
    public Object value(Object value) {
        return value;
    }

    @Override
    public boolean acceptsValue(Object value) {
        return value instanceof Date;
    }
}
