package com.company.mtable.core.funcs;

import com.company.mtable.core.SimpleFunc;
import com.company.mtable.core.types.DataType;
import com.company.mtable.core.types.NumberType;
import com.company.mtable.core.types.Types;

import java.util.Collections;
import java.util.List;

/**
 * Created by jxwr on 2019/6/19.
 */
public class Mod extends SimpleFunc {
    @Override
    public List<DataType> inType() {
        return Collections.singletonList(Types.NumericType);
    }

    @Override
    public DataType outType() {
        return Types.NumericType;
    }

    @Override
    public Object apply(Object[] params) {
        Long left = NumberType.value(params[0]).longValue();
        Long right = NumberType.value(params[1]).longValue();

        return left % right;
    }
}
