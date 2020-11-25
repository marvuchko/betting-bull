package com.marvuchko.infrastructuremicroservice.exception.core;

import com.marvuchko.infrastructuremicroservice.exception.base.BaseException;

public class UnauthorizedException extends BaseException {

    public static final String DEFAULT_ERROR_MESSAGE = "Unauthorized access!";

    public UnauthorizedException() {
        super(DEFAULT_ERROR_MESSAGE, 401);
    }

    public UnauthorizedException(String defaultMessage, int defaultErrorCode) {
        super(defaultMessage, defaultErrorCode);
    }
}
