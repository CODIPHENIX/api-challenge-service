package com.apichallengeservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public enum ErrorCode {

    RESOURCE_NOT_FOUND("Resource not found", HttpStatus.NOT_FOUND),
    INVALIDE_INPUT_DATA("Invalide input data", HttpStatus.UNPROCESSABLE_ENTITY);

    private final String message;
    private final HttpStatus status;

    ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
