package com.proyectofinal.bazar.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorResponseDTO {

    private LocalDateTime timestamp;
    private String message;
    private int status;
    private String path;



}
