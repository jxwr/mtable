package com.company.mtable.core.types;

import com.company.mtable.core.datatypes.Tuple2;

import java.util.Date;

/**
 * Companion Objects
 */
public interface Types {

    DataType NumericType = new NumericType();

    DataType LongType = new LongType();

    DataType IntegerType = new IntegerType();

    DataType ShortType = new ShortType();

    DataType ByteType = new ByteType();

    DataType BooleanType = new BooleanType();

    DataType StringType = new StringType();

    DataType DateType = new DateType();

    DataType NumberType = new NumberType();

    DataType AnyType = new AnyType();

    DataType TupleType = new TupleType();

    static DataType fromClass(Class klass) {
        if (klass.equals(Integer.class)) {
            return IntegerType;
        } else if (klass.equals(Long.class)) {
            return LongType;
        } else if (klass.equals(Short.class)) {
            return ShortType;
        } else if (klass.equals(Byte.class)) {
            return ByteType;
        } else if (klass.equals(String.class)) {
            return StringType;
        } else if (klass.equals(Date.class)) {
            return DateType;
        } else if (klass.equals(Number.class)) {
            return NumberType;
        } else if (klass.equals(Tuple2.class)) {
            return TupleType;
        }
        return AnyType;
    }
}
