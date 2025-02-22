package com.proyectofinal.bazar.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String marca;
    private double costo;
    private int cantidadDisponible;


    public void descontarCantidad(){
        if(this.cantidadDisponible > 0){
            this.cantidadDisponible -= 1;
        }
    }


    public void aumentarCantidad() {
        this.cantidadDisponible += 1;
    }

    public boolean tieneStock(){
        return this.cantidadDisponible > 0;
    }
}
