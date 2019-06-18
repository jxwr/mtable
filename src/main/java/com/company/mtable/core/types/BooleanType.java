package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/19.
 */
public class BooleanType implements DataType {
    @Override
    public String typeName() {
        return type().getTypeName();
    }

    @Override
    public Class<Boolean> type() {
        return Boolean.TYPE;
    }
}
