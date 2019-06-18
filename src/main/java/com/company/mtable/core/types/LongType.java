package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/18.
 */
public class LongType extends NumericType {

    @Override
    public String typeName() {
        return "Long";
    }

    @Override
    public Class<?> type() {
        return Long.class;
    }
}
