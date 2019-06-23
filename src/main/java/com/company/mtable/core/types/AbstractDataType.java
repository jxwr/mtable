package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/23.
 */
public abstract class AbstractDataType implements DataType {
    @Override
    public String toString() {
        return typeName();
    }
}
