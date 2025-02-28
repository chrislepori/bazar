package com.proyectofinal.bazar.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponseDTO> handleApiException(ApiException ex, HttpServletRequest request){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(LocalDateTime.now() ,ex.getError().getMessage(), ex.getError().getCode().value(), request.getRequestURI());
        return ResponseEntity.status(ex.getError().getCode()).body(errorResponseDTO);
    }
}
