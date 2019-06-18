package com.company.mtable.core;

import com.company.mtable.core.types.DataType;

/**
 * Created by jxwr on 2019/6/18.
 */
public interface Func {

    DataType inType();

    DataType outType();

    Object apply(Object obj);
}
