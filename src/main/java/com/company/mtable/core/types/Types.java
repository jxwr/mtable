package com.company.mtable.core.types;

import com.company.mtable.core.datatypes.Tuple2;

import java.util.Date;

/**
 * Companion Objects
 */
public interface Types {

    DataType LongType = new LongType();

    DataType IntegerType = new IntegerType();

    DataType ShortType = new ShortType();

    DataType ByteType = new ByteType();

    DataType BooleanType = new BooleanType();

    DataType StringType = new StringType();

    DataType DateType = new DateType();

    DataType NumberType = new NumberType();

    DataType AnyType = new AnyType();

    static DataType fromClass(Class klass) {
        if (klass.equals(Integer.class)) {
            return IntegerType;
        } else if (klass.equals(Long.class)) {
            return LongType;
        } else if (klass.equals(Short.class)) {
            return ShortType;
        } else if (klass.equals(Byte.class)) {
            return ByteType;
        } else if (klass.equals(Number.class)) {
            return NumberType;
        } else if (klass.equals(String.class)) {
            return StringType;
        } else if (klass.equals(Date.class)) {
            return DateType;
        }
        return AnyType;
    }

    static char typeChar(DataType dataType) {
        if (dataType == Types.IntegerType) {
            return 'I';
        } else if (dataType == Types.BooleanType) {
            return 'B';
        } else if (dataType == Types.LongType) {
            return 'L';
        } else if (dataType == Types.ByteType) {
            return 'b';
        } else if (dataType == Types.NumberType) {
            return 'N';
        } else if (dataType == Types.StringType) {
            return 'S';
        } else if (dataType == Types.DateType) {
            return 'D';
        } else if (dataType == Types.AnyType) {
            return 'A';
        }
        return 'E';
    }
}
