package org.halo.thallo.mmr.core.service;

/**
 * Created by lihong on 17-10-11.
 */
public class MMRException extends Exception {
    public MMRException() {
    }

    public MMRException(String message) {
        super(message);
    }

    public MMRException(String message, Throwable cause) {
        super(message, cause);
    }

    public MMRException(Throwable cause) {
        super(cause);
    }

    public MMRException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
