package com.company.mtable.core.types;

import com.company.mtable.core.Record;

public class RecordType extends AbstractDataType {
    @Override
    public String typeName() {
        return "Record";
    }

    @Override
    public Class<?> typeClass() {
        return Record.class;
    }

    @Override
    public boolean acceptsType(DataType type) {
        return type == Types.RecordType;
    }

    @Override
    public Object value(Object value) {
        return value;
    }

    @Override
    public boolean acceptsValue(Object value) {
        return value instanceof Record;
    }

    @Override
    public Object minValue() {
        return null;
    }

    @Override
    public Object maxValue() {
        return null;
    }

    @Override
    public boolean isConcreteType() {
        return true;
    }
}
