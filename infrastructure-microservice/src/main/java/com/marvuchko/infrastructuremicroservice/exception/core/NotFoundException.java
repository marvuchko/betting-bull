package com.marvuchko.infrastructuremicroservice.exception.core;

import com.marvuchko.infrastructuremicroservice.exception.base.BaseException;

public class NotFoundException extends BaseException {

    public static final String DEFAULT_ERROR_MESSAGE = "Not found!";

    public NotFoundException() {
        super(DEFAULT_ERROR_MESSAGE, 404);
    }

    public NotFoundException(String message, int errorCode) {
        super(message, errorCode);
    }
}
