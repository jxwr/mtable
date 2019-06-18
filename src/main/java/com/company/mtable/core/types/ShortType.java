package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/19.
 */
public class ShortType implements DataType {
    @Override
    public String typeName() {
        return type().getTypeName();
    }

    @Override
    public Class<?> type() {
        return Short.TYPE;
    }
}
