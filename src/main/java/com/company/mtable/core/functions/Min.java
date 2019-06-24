package com.company.mtable.core.functions;

import com.company.mtable.core.ifn.AFn1;

/**
 * Created by jxwr on 2019/6/20.
 */
public class Min implements AFn1<Comparable, Comparable> {
    private Comparable min;

    public Min() {
        min = null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void handle(Comparable param) throws Exception {
        if (min == null) {
            min = param;
            return;
        }

        if (param.compareTo(min) < 0) {
            min = param;
        }
    }

    @Override
    public Comparable finish() {
        return min;
    }
}
