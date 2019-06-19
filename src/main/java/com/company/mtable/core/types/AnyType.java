package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/20.
 */
public class AnyType implements DataType {
    @Override
    public String typeName() {
        return "Any";
    }

    @Override
    public Class<?> type() {
        return Object.class;
    }
}
