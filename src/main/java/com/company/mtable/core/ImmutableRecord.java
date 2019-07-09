package com.company.mtable.core;

import com.company.mtable.schema.Column;

public interface ImmutableRecord {
    Object get(int cid);

    Boolean getBoolean(int i);

    Byte getByte(int i);

    Short getShort(int i);

    Integer getInt(int i);

    Long getLong(int i);

    String getString(int i);

    int numColumns();
}
