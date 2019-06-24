package com.company.mtable.core.ifn;

public interface Fn<R> {
    R call(Object[] t) throws Exception;
}
