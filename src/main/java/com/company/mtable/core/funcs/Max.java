package com.company.mtable.core.funcs;

import com.company.mtable.core.AggragateFunc;
import com.company.mtable.core.types.DataType;
import com.company.mtable.core.types.NumberType;
import com.company.mtable.core.types.Types;

import java.util.Collections;
import java.util.List;

/**
 * Created by jxwr on 2019/6/20.
 */
public class Max extends AggragateFunc {
    private Object max;

    @Override
    public void init() {
        max = null;
    }

    @Override
    public boolean handle(Object[] params) {
        if (max == null) {
            max = params[0];
            return true;
        }

        Comparable c = (Comparable)params[0];
        if (c.compareTo(max) > 0) {
            max = params[0];
        }
        return true;
    }

    @Override
    public Object finish() {
        return max;
    }

    @Override
    public AggragateFunc copy() {
        return new Max();
    }

    @Override
    public List<DataType> inType() {
        return Collections.singletonList(Types.NumberType);
    }

    @Override
    public DataType outType() {
        return Types.IntegerType;
    }
}
