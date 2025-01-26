package org.umm.httpserver.config;

public class HttpConfigException extends RuntimeException {

    public HttpConfigException() {
    }

    public HttpConfigException(Throwable cause) {
        super(cause);
    }

    public HttpConfigException(String message) {
        super(message);
    }

    public HttpConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HttpConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
