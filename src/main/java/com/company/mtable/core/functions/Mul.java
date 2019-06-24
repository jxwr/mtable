package com.company.mtable.core.functions;

import com.company.mtable.core.ifn.Fn2;

/**
 * Created by jxwr on 2019/6/19.
 */
public class Mul implements Fn2<Number, Number, Long> {

    @Override
    public Long call(Number left, Number right) throws Exception {
        return left.longValue() * right.longValue();
    }

}
