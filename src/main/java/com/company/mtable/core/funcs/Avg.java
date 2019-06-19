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
public class Avg extends AggragateFunc {
    private long sum;
    private long count;

    @Override
    public void init() {
        sum = 0;
        count = 0;
    }

    @Override
    public boolean handle(Object[] params) {
        long val = NumberType.value(params[0]).longValue();
        sum += val;
        count++;
        return true;
    }

    @Override
    public Object finish() {
        if (count != 0) {
            return sum / count;
        } else {
            return 0;
        }
    }

    @Override
    public AggragateFunc copy() {
        return new Avg();
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
