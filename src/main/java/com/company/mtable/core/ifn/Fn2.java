package com.company.mtable.core.ifn;

public interface Fn2<T1, T2, R> {
    R call(T1 t1, T2 t2) throws Exception;
}
