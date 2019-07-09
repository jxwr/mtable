package com.company.mtable.core.types;

import com.company.mtable.core.Record;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Companion Objects
 */
public interface Types {

    DataType DoubleType = new DoubleType();

    DataType LongType = new LongType();

    DataType IntegerType = new IntegerType();

    DataType ShortType = new ShortType();

    DataType ByteType = new ByteType();

    DataType NumberType = new NumberType();

    DataType BooleanType = new BooleanType();

    DataType StringType = new StringType();

    DataType DateType = new DateType();

    DataType AnyType = new AnyType();

    DataType TimestampType = new TimestampType();

    DataType RecordType = new RecordType();

    static DataType fromJavaType(Class<?> klass) {
        if (klass == Integer.class || klass == Integer.TYPE) {
            return IntegerType;
        } else if (klass == Long.class || klass == Long.TYPE) {
            return LongType;
        } else if (klass == Short.class || klass == Short.TYPE) {
            return ShortType;
        } else if (klass == Double.class || klass == Double.TYPE) {
            return DoubleType;
        } else if (klass == Byte.class || klass == Byte.TYPE) {
            return ByteType;
        } else if (klass == Number.class) {
            return NumberType;
        } else if (klass == String.class) {
            return StringType;
        } else if (klass == Record.class) {
            return RecordType;
        } else if (klass == Date.class) {
            return DateType;
        } else if (klass == Timestamp.class) {
            return TimestampType;
        }
        return AnyType;
    }

    static char typeChar(DataType dataType) {
        if (dataType == Types.IntegerType) {
            return 'I';
        } else if (dataType == Types.LongType) {
            return 'L';
        } else if (dataType == Types.ByteType) {
            return 'b';
        } else if (dataType == Types.ShortType) {
            return 's';
        } else if (dataType == Types.DoubleType) {
            return 'd';
        } else if (dataType == Types.NumberType) {
            return 'N';
        } else if (dataType == Types.BooleanType) {
            return 'B';
        } else if (dataType == Types.StringType) {
            return 'S';
        } else if (dataType == Types.RecordType) {
            return 'R';
        } else if (dataType == Types.DateType) {
            return 'D';
        } else if (dataType == Types.AnyType) {
            return 'A';
        }
        return 'E';
    }
}
