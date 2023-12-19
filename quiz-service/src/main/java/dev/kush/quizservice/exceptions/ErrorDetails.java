package dev.kush.quizservice.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorDetails(String message, HttpStatus status, LocalDateTime timestamp) {
}
