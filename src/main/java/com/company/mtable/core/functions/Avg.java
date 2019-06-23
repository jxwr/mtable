package com.company.mtable.core.functions;

import com.company.mtable.core.fn.AFn1;

/**
 * Created by jxwr on 2019/6/20.
 */
public class Avg implements AFn1<Number, Number> {
    private long sum;
    private long count;

    public Avg() {
        sum = 0;
        count = 0;
    }

    @Override
    public void handle(Number param) {
        long val = param.longValue();
        sum += val;
        count++;
    }

    @Override
    public Number finish() {
        if (count != 0) {
            return sum / count;
        } else {
            return 0;
        }
    }
}
