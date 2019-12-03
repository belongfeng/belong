package com.belong.common.exception.base;

public class PageException extends Exception {
    private static final long serialVersionUID = 3887472968823615091L;

    public PageException() {
    }

    public PageException(String msg) {
        super(msg);
    }
}