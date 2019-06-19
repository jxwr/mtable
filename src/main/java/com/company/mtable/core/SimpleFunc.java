package com.company.mtable.core;

import com.company.mtable.core.types.DataType;

import java.util.List;

/**
 * Created by jxwr on 2019/6/19.
 */
public abstract class SimpleFunc implements Func {
    @Override
    public boolean isAggr() {
        return false;
    }
}