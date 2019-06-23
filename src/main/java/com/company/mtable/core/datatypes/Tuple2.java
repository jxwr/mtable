package com.company.mtable.core.datatypes;

/**
 * Created by jxwr on 2019/6/23.
 */
public class Tuple2<T0, T1> {

    private T0 v0;

    private T1 v1;

    public Tuple2(T0 v0, T1 v1) {
        this.v0 = v0;
        this.v1 = v1;
    }

    public T0 v0() {
        return v0;
    }

    public T1 v1() {
        return v1;
    }
}
