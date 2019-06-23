package com.company.mtable.core;

/**
 * Created by jxwr on 2019/6/23.
 */
public interface SelectionHandler {

    Object handle(Record record) throws Exception;

    Object finish() throws Exception;
}
