package com.company.mtable.core.types;


public interface DataType {

    String typeName();

    Class<?> typeClass();

    boolean acceptsType(DataType type);

    Object value(Object value);

    boolean acceptsValue(Object value);
}
