package com.company.mtable.core.ifn;

public interface AFn1<T1, R> {

    void handle(T1 t1) throws Exception;

    R finish() throws Exception;
}
