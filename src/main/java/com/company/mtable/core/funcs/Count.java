package com.company.mtable.core.funcs;

import com.company.mtable.core.AggragateFunc;
import com.company.mtable.core.types.DataType;
import com.company.mtable.core.types.Types;

import java.util.Collections;
import java.util.List;

/**
 * Created by jxwr on 2019/6/20.
 */
public class Count extends AggragateFunc {
    private int count;

    @Override
    public void init() {
        count = 0;
    }

    @Override
    public boolean handle(Object[] params) {
        count++;
        return true;
    }

    @Override
    public Object finish() {
        return count;
    }

    @Override
    public AggragateFunc copy() {
        return new Count();
    }

    @Override
    public List<DataType> inType() {
        return Collections.singletonList(Types.AnyType);
    }

    @Override
    public DataType outType() {
        return Types.IntegerType;
    }
}
