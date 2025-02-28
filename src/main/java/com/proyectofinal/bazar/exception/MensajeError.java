package com.proyectofinal.bazar.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum MensajeError {

    PRODUCTO_NOT_FOUD("Producto no encontrado", HttpStatus.NOT_FOUND),
    PRODUCT_EXISTING("EL producto ya existe", HttpStatus.BAD_REQUEST),
    PRODUCT_NO_STOCK("No hay stock del producto", HttpStatus.NOT_FOUND),
    CLIENTE_NOT_FOUND("Cliente no encontrado", HttpStatus.NOT_FOUND),
    CLIENTE_EXISTING("Ya existe un cliente con ese DNI", HttpStatus.BAD_REQUEST),
    VENTA_SIN_PRODUCTO("La venta debe tener al menos un producto", HttpStatus.BAD_REQUEST),
    VENTA_NOT_FOUND("Venta no encontrada", HttpStatus.NOT_FOUND),
    VENTA_NOT_FOUND_FECHA("No hay ventas para esa fecha", HttpStatus.NOT_FOUND);




    private final String message;
    private final HttpStatus code;
}
