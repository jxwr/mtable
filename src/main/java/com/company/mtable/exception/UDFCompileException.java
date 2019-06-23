package com.company.mtable.exception;

/**
 * Created by jxwr on 2019/6/23.
 */
public class UDFCompileException extends Exception {
    public UDFCompileException(Exception e) {
        super(e);
    }

    public UDFCompileException(String message) {
        super(message);
    }
}
