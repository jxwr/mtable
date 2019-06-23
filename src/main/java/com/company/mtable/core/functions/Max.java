package com.company.mtable.core.functions;

import com.company.mtable.core.fn.AFn1;

/**
 * Created by jxwr on 2019/6/20.
 */
public class Max implements AFn1<Comparable, Comparable> {
    private Comparable max;

    public Max() {
        max = null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void handle(Comparable param) throws Exception {
        if (max == null) {
            max = param;
            return;
        }

        if (param.compareTo(max) > 0) {
            max = param;
        }
    }

    @Override
    public Comparable finish() {
        return max;
    }
}
