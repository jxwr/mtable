package com.company.mtable.core.fn;

public interface Fn<R> {
  R call(Object[] t) throws Exception;
}
