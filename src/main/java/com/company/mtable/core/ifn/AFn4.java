package com.company.mtable.core.ifn;

public interface AFn4<T1, T2, T3, T4, R> {
    void handle(T1 t1, T2 t2, T3 t3, T4 t4) throws Exception;

    R finish() throws Exception;
}
