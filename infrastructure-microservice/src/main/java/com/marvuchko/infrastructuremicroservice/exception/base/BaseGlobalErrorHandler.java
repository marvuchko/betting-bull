package com.marvuchko.infrastructuremicroservice.exception.base;

import com.marvuchko.infrastructuremicroservice.exception.dto.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public abstract class BaseGlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorDto> handleBaseException(BaseException exception, HttpServletRequest request) {
        var error = ErrorDto.builder()
                .errorCode(exception.getDefaultErrorCode())
                .errorMessages(Set.of(exception.getDefaultMessage()))
                .path(request.getRequestURI())
                .build();

        return ResponseEntity
                .status(error.getErrorCode())
                .body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        var httpServletRequest = ((ServletWebRequest) request).getRequest();

        var fieldErrors = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(toSet());

        var error = ErrorDto.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .path(httpServletRequest.getRequestURI())
                .errorMessages(fieldErrors.isEmpty() ? Set.of(exception.getMessage()) : fieldErrors)
                .build();

        return ResponseEntity
                .status(error.getErrorCode())
                .body(error);
    }

}
