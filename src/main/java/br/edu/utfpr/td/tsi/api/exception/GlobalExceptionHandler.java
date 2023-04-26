package br.edu.utfpr.td.tsi.api.exception;

import br.edu.utfpr.td.tsi.api.exception.NoDataFoundException;
import br.edu.utfpr.td.tsi.api.exception.NotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(NotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<String> handleNoDataFoundException(NoDataFoundException ex) {
        return ResponseEntity.notFound().build();
    }
}

