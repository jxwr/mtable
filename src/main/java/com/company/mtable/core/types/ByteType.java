package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/19.
 */
public class ByteType extends NumberType {
    @Override
    public String typeName() {
        return type().getTypeName();
    }

    @Override
    public Class<Byte> type() {
        return Byte.TYPE;
    }
}
