package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.ProductoResponseDTO;
import com.proyectofinal.bazar.dto.VentaDTO;
import com.proyectofinal.bazar.dto.VentaResponseDTO;
import com.proyectofinal.bazar.exception.ApiException;
import com.proyectofinal.bazar.exception.MensajeError;
import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.model.Venta;
import com.proyectofinal.bazar.repository.ClienteRepository;
import com.proyectofinal.bazar.repository.ProductoRepository;
import com.proyectofinal.bazar.repository.VentaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepo;
    private final ProductoRepository productoRepo;
    private final ClienteRepository clienteRepo;
    private final ModelMapper modelMapper;

    public VentaResponseDTO createVenta(VentaDTO ventaDTO) {

        List<Producto> productos = obtenerProductosValidos(ventaDTO);

        Cliente cliente = validarCliente(ventaDTO);

        Venta nuevaVenta = getVenta(productos, cliente);

        Venta ventaGuardada = ventaRepo.save(nuevaVenta);

        descontarProductos(productos);

        return modelMapper.map(ventaGuardada, VentaResponseDTO.class);
    }


    private List<Producto> obtenerProductosValidos(VentaDTO ventaDTO) {
        List<Producto> productos = new ArrayList<>();
        for (Long id : ventaDTO.getProductosIds()) {
            Producto producto = productoRepo.findById(id)
                    .orElseThrow(() -> new ApiException(MensajeError.PRODUCTO_NOT_FOUD));

            if (!producto.tieneStock()) {
                throw new ApiException(MensajeError.PRODUCT_NO_STOCK);
            }

            productos.add(producto);
        }
        return productos;
    }

    private Cliente validarCliente(VentaDTO ventaDTO) {
        Cliente cliente = clienteRepo.findById(ventaDTO.getClienteId())
                .orElseThrow(() -> new ApiException(MensajeError.CLIENTE_NOT_FOUND));

        return cliente;
    }

    private Venta getVenta(List<Producto> productos, Cliente cliente) {
        Venta nuevaVenta = new Venta();
        nuevaVenta.setFechaVenta(LocalDate.now());
        nuevaVenta.setProductos(productos);
        nuevaVenta.setCliente(cliente);
        nuevaVenta.obtenerMonto();
        return nuevaVenta;
    }

    private void descontarProductos(List<Producto> productos) {
        for (Producto p : productos) {
            p.descontarCantidad();
        }
        productoRepo.saveAll(productos);
    }

    public List<Venta> getVentas() {
        return ventaRepo.findAll();
    }


    public VentaResponseDTO findVenta(Long id) {
        Venta venta = ventaRepo.findById(id)
                .orElseThrow(() -> new ApiException(MensajeError.VENTA_NOT_FOUND));
        return modelMapper.map(venta, VentaResponseDTO.class);
    }

    public void deleteVenta(Long id) {
        Venta venta = ventaRepo.findById(id)
                .orElseThrow(() -> new ApiException(MensajeError.VENTA_NOT_FOUND));

        for (Producto p : venta.getProductos()) {
            p.aumentarCantidad();
        }


        productoRepo.saveAll(venta.getProductos());


        ventaRepo.delete(venta);
    }


    public List<ProductoResponseDTO> productosDeUnaVenta(Long id) {
        Venta venta = ventaRepo.findById(id)
                .orElseThrow(() -> new ApiException(MensajeError.VENTA_NOT_FOUND));

        return venta.getProductos().stream()
                .map(producto -> modelMapper.map(producto, ProductoResponseDTO.class))
                .toList();
    }


    public List<VentaResponseDTO> ventasPorDia(LocalDate fecha) {
        List<Venta> ventasPorDia =  ventaRepo.findByFechaVenta(fecha);
        return ventasPorDia.stream()
                .map(venta -> modelMapper.map(venta, VentaResponseDTO.class))
                .toList();

    }

    public Page<Venta> getVentasPagination(int numeroPagina, int cantidad ){
        Pageable pageable = PageRequest.of(numeroPagina, cantidad);
        return ventaRepo.findAll(pageable);
    }


    public VentaResponseDTO obtenerVentaMayor() {
        List<Venta> listaVentas = this.getVentas();
        Venta ventaMayor = listaVentas.stream()
                .max(Comparator.comparingDouble(Venta::obtenerMonto))
                .orElseThrow(() -> new ApiException(MensajeError.VENTA_NOT_FOUND));
        return modelMapper.map(ventaMayor, VentaResponseDTO.class);


    }


}
