package com.company.mtable.core.types;

import java.util.Date;

/**
 * Created by jxwr on 2019/6/19.
 */
public class DateType implements DataType {
    @Override
    public String typeName() {
        return "Date";
    }

    @Override
    public Class<?> type() {
        return Date.class;
    }
}
