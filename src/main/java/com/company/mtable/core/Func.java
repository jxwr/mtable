package com.company.mtable.core;

import com.company.mtable.core.types.DataType;

import java.util.List;

/**
 * Created by jxwr on 2019/6/18.
 */
public interface Func {

    boolean isAggr();

    List<DataType> inType();

    DataType outType();
}
