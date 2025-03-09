package com.proyectofinal.bazar.repository;

import com.proyectofinal.bazar.dto.VentaResponseDTO;
import com.proyectofinal.bazar.model.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

     List<Venta> findByFechaVenta(LocalDate fecha);
     Page<Venta> findAll(Pageable pageable);

}
