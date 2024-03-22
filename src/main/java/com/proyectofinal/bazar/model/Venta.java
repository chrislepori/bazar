package com.proyectofinal.bazar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigoVenta;
    private LocalDate fechaVenta;
    private Double total;
    @ManyToMany
    private List<Producto> listaProductos;
    @ManyToOne
    private Cliente unCliente;

    public Venta() {
    }

    public Venta(Long codigoVenta, LocalDate fechaVenta, Double total, List<Producto> listaProductos, Cliente unCliente) {
        this.codigoVenta = codigoVenta;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.listaProductos = listaProductos;
        this.unCliente = unCliente;
    }

    public double obtenerMonto() {
        double totalMonto = 0;
        for (Producto p : listaProductos) {
            totalMonto += p.getCosto();
        }
        return totalMonto;
    }
}
