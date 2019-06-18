package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/18.
 */
public class IntegerType extends NumericType {
    @Override
    public String typeName() {
        return "Integer";
    }

    @Override
    public Class<?> type() {
        return Integer.class;
    }
}
