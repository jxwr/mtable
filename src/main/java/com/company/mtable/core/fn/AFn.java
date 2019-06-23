package com.company.mtable.core.fn;

public interface AFn<R> {
  void handle(Object[] t) throws Exception;
  R finish() throws Exception;
}
