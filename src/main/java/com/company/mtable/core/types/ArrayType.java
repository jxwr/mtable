package com.company.mtable.core.types;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jxwr on 2019/6/20.
 */
public class ArrayType implements DataType {

    private DataType elementType;

    public ArrayType(DataType elementType) {
        this.elementType = elementType;
    }

    public DataType elementType() {
        return elementType;
    }

    @Override
    public String typeName() {
        return "Array";
    }

    @Override
    public Class<?> type() {
        return List.class;
    }
}
