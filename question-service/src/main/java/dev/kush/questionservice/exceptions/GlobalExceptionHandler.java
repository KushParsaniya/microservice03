package dev.kush.questionservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(errorDetails,NOT_FOUND);
    }
}
