package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/19.
 */
public class StringType implements DataType {
    @Override
    public String typeName() {
        return "String";
    }

    @Override
    public Class<?> type() {
        return String.class;
    }
}
