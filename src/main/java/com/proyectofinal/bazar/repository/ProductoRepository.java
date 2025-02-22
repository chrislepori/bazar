package com.proyectofinal.bazar.repository;

import com.proyectofinal.bazar.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    public List<Producto> findByCantidadDisponibleLessThanEqual(double cantidadDisponible);
    public boolean existsByNombre(String nombre);


}
