package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/20.
 */
public class AnyType extends AbstractDataType {
    @Override
    public String typeName() {
        return "Any";
    }

    @Override
    public Class<?> typeClass() {
        return Object.class;
    }

    @Override
    public boolean acceptsType(DataType type) {
        return true;
    }

    @Override
    public Object value(Object value) {
        return value;
    }

    @Override
    public boolean acceptsValue(Object value) {
        return true;
    }

    @Override
    public Object minValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object maxValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isConcreteType() {
        return false;
    }
}
