package com.company.mtable.core.ifn;

public interface AFn<R> {
    void handle(Object[] t) throws Exception;

    R finish() throws Exception;
}
