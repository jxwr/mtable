package com.company.mtable.core.fn;

public interface AFn3<T1, T2, T3, R> {
  void handle(T1 t1, T2 t2, T3 t3) throws Exception;
  R finish() throws Exception;
}
