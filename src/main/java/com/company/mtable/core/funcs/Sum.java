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
public class Sum extends AggragateFunc {
    private long sum;

    @Override
    public void init() {
        sum = 0;
    }

    @Override
    public boolean handle(Object[] params) {
        long val = NumberType.value(params[0]).longValue();
        sum += val;
        return true;
    }

    @Override
    public Object finish() {
        return sum;
    }

    @Override
    public AggragateFunc copy() {
        Sum sum = new Sum();
        sum.init();
        return sum;
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
