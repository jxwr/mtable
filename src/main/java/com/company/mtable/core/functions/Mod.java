package com.company.mtable.core.functions;

import com.company.mtable.core.ifn.Fn2;

/**
 * Created by jxwr on 2019/6/19.
 */
public class Mod implements Fn2<Number, Integer, Integer> {
    @Override
    public Integer call(Number left, Integer right) throws Exception {
        return (int) left % right;
    }
}
