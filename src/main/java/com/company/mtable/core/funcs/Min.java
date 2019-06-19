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
public class Min extends AggragateFunc {
    private Object min;

    @Override
    public void init() {
        min = 0;
    }

    @Override
    public boolean handle(Object[] params) {
        if (params[0].getClass().isInstance(Comparable.class)) {
            Comparable c = (Comparable)params[0];
            if (c.compareTo(min) < 0) {
                min = params[0];
            }
        }
        return true;
    }

    @Override
    public Object finish() {
        return min;
    }

    @Override
    public AggragateFunc copy() {
        return new Min();
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
