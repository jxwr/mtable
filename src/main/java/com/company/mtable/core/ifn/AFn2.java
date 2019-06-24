package com.company.mtable.core.ifn;

public interface AFn2<T1, T2, R> {
    void handle(T1 t1, T2 t2) throws Exception;

    R finish() throws Exception;
}
