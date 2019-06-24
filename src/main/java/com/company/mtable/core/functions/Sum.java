package com.company.mtable.core.functions;

import com.company.mtable.core.ifn.AFn1;

/**
 * Created by jxwr on 2019/6/20.
 */
public class Sum implements AFn1<Number, Long> {
    private long sum;

    public Sum() {
        sum = 0;
    }

    @Override
    public void handle(Number val) throws Exception {
        sum += val.longValue();
    }

    @Override
    public Long finish() throws Exception {
        return sum;
    }
}
