package com.proyectofinal.bazar.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate fechaVenta;
    private Double total;
    @ManyToMany
    private List<Producto> productos;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    public double obtenerMonto() {
        double totalMonto = 0;
        for (Producto p : productos) {
            totalMonto += p.getCosto();
        }
        this.total = totalMonto;
        return totalMonto;
    }
}
