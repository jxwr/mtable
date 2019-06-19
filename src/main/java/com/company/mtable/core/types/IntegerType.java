package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/18.
 */
public class IntegerType extends NumberType {
    @Override
    public String typeName() {
        return type().getTypeName();
    }

    @Override
    public Class<Integer> type() {
        return Integer.TYPE;
    }
}
