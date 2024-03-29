package com.githooknotifyline.gitlabxlineapplication.handler.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {

        log.error(ex.getMessage(), ex);
        String message =
                ex.getName() + " should be of type " + ex.getRequiredType().getName();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ReponseHandler apiError =
                new ReponseHandler(httpStatus.value(), message);

        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), httpStatus);
    }

    @ExceptionHandler({InvalidHeaderException.class})
    public ResponseEntity<Object> handleInvalidHeader(
            InvalidHeaderException ex, WebRequest request) {

        log.error(ex.getMessage(), ex);
        String message = ex.getMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ReponseHandler apiError =
                new ReponseHandler(httpStatus.value(), message);

        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), httpStatus);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

        log.error(ex.getMessage(), ex);
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String error = ex.getMessage() != null ? ex.getMessage() : ex.getClass().getName();
        String message = String.format("error : %s", error);
        ReponseHandler apiError = new ReponseHandler(
                httpStatus.value(), message);
        System.out.println(httpStatus.value() + " " + message);
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), httpStatus);
    }

    @Data
    @AllArgsConstructor
    public class ReponseHandler {
        int status;
        String message;
    }
}
