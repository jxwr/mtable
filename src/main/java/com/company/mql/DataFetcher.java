package com.company.mql;

/**
 * Created by jxwr on 2019/7/7.
 */
public interface DataFetcher<T> {
    T get() throws Exception;
}
