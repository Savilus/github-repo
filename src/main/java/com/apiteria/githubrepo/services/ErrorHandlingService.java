package com.apiteria.githubrepo.services;

import com.apiteria.githubrepo.errors.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ErrorHandlingService {

    public ResponseEntity<ErrorResponse> createUserNotFoundError(HttpStatus status, String message) {
        return ResponseEntity.ofNullable(new ErrorResponse(status.value(), message));
    }

    public ResponseEntity<ErrorResponse> createUnexpectedError(HttpStatus httpStatus, String message) {
        return ResponseEntity.ofNullable(new ErrorResponse(httpStatus.value(), message));
    }

    public ResponseEntity<ErrorResponse> wrongHeaderError(HttpStatus httpStatus, String message) {
        return ResponseEntity.ofNullable(new ErrorResponse(httpStatus.value(), message));
    }
}
