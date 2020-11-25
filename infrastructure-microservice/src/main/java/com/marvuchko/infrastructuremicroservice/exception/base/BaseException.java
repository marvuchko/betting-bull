package com.marvuchko.infrastructuremicroservice.exception.base;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final String defaultMessage;

    private final int defaultErrorCode;

    public BaseException(String defaultMessage, int defaultErrorCode) {
        this.defaultMessage = defaultMessage;
        this.defaultErrorCode = defaultErrorCode;
    }
}
