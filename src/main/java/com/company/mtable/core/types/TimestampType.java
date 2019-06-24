package com.company.mtable.core.types;

import java.sql.Timestamp;

/**
 * Created by jxwr on 2019/6/25.
 */
public class TimestampType implements DataType {
    @Override
    public String typeName() {
        return "Timestamp";
    }

    @Override
    public Class<?> typeClass() {
        return Timestamp.class;
    }

    @Override
    public boolean acceptsType(DataType type) {
        return type == Types.TimestampType;
    }

    @Override
    public Object value(Object value) {
        return value;
    }

    @Override
    public boolean acceptsValue(Object value) {
        return value instanceof Timestamp;
    }
}
