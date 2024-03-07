package com.minibanking.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends Throwable {
    private static final long serialVersionUID = 1L;

    private final String errorCode;
    private final int httpStatus;
    private final String[] arguments;

    public ServiceException(String errorCode, int httpStatus) {
        super("RestException:" + errorCode + "," + httpStatus);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.arguments = null;
    }

    public ServiceException(String errorCode, HttpStatus httpStatus) {
        this(errorCode, httpStatus.value());
    }

    public ServiceException(String errorCode) {
        this(errorCode, HttpStatus.BAD_REQUEST);
    }

    public ServiceException(String errorCode, Throwable cause, String... arguments) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.httpStatus = 404;
        this.arguments = arguments;
    }

    public ServiceException(String errorCode, String... arguments) {
        this(errorCode, null, arguments);
    }
}
