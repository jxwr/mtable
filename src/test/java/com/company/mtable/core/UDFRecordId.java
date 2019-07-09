package com.company.mtable.core;

import com.company.mtable.core.ifn.Fn2;

public class UDFRecordId implements Fn2<Record, Integer, Integer> {
    @Override
    public Integer call(Record record, Integer integer) throws Exception {
        return record.getInt(integer);
    }
}
