package com.match.dev.exception;

public abstract class BaseException extends Exception {

    protected BaseException(String message) { super(message); }

    protected BaseException(Throwable cause) { super(cause); }
}
