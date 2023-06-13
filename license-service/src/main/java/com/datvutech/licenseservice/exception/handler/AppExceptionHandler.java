package com.datvutech.licenseservice.exception.handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

   /*  @ExceptionHandler({ TimeoutException.class, RuntimeException.class })
    public ResponseEntity<ErrorMessage> handleIOException(RuntimeException e) {

        ErrorMessage errorMsg = new ErrorMessage(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value() + "",
                e.getMessage());

        return ResponseEntity.ok(errorMsg);
    } */
}
