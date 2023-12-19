package dev.kush.questionservice.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorDetails(String message, HttpStatus status, LocalDateTime timestamp) {
}
