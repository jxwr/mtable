package com.company.mtable.functions;

import com.company.mtable.core.ifn.AFn1;

/**
 * Created by jxwr on 2019/6/20.
 */
public class Count implements AFn1<Object, Integer> {
    private int count;

    public Count() {
        this.count = 0;
    }

    @Override
    public void handle(Object param) {
        count++;
    }

    @Override
    public Integer finish() {
        return count;
    }
}
