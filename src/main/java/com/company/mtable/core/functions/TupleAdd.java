package com.company.mtable.core.functions;

import com.company.mtable.core.datatypes.Tuple2;
import com.company.mtable.core.fn.Fn1;

/**
 * Created by jxwr on 2019/6/23.
 */
public class TupleAdd implements Fn1<Tuple2<Integer, Short>, Long> {

    @Override
    public Long call(Tuple2<Integer, Short> t) throws Exception {
        return t.v0().longValue() + t.v1().longValue();
    }
}
