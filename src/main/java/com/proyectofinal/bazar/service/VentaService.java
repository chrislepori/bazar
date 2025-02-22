package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.ProductoVentaDTO;
import com.proyectofinal.bazar.dto.VentaDTO;
import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.model.Venta;
import com.proyectofinal.bazar.repository.ClienteRepository;
import com.proyectofinal.bazar.repository.ProductoRepository;
import com.proyectofinal.bazar.repository.VentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepo;
    private final ProductoRepository productoRepo;
    private final ClienteRepository clienteRepo;

    private boolean noHayProductos(VentaDTO ventaDTO) {
        return ventaDTO.getProductosIds() == null || ventaDTO.getProductosIds().isEmpty();
    }

    private List<Producto> obtenerProductosValidos(VentaDTO ventaDTO) {
        List<Producto> productos = new ArrayList<>();
        for (Long id : ventaDTO.getProductosIds()) {
            Producto producto = productoRepo.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + id));

            if (!producto.tieneStock()) {
                throw new IllegalArgumentException("El producto " + producto.getNombre() + " no tiene stock suficiente.");
            }

            productos.add(producto);
        }
        return productos;
    }

    private Cliente validarCliente(VentaDTO ventaDTO) {
        Cliente cliente = null;
        if (ventaDTO.getClienteId() != null) {
            cliente = clienteRepo.findById(ventaDTO.getClienteId())
                    .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado."));
        }
        return cliente;
    }

    private Venta getVenta(List<Producto> productos, Cliente cliente) {
        Venta nuevaVenta = new Venta();
        nuevaVenta.setFechaVenta(LocalDate.now()); // Fecha actual
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


    public Venta createVenta(VentaDTO ventaDTO) {
        // Validar que haya productos en la venta
        if (noHayProductos(ventaDTO)) {
            throw new IllegalArgumentException("La venta debe tener al menos un producto.");
        }

        // Buscar los productos en la BD y validar stock
        List<Producto> productos = obtenerProductosValidos(ventaDTO);

        // Validar que el cliente exista si se envió un ID
        Cliente cliente = validarCliente(ventaDTO);

        // Crear la venta con los datos validados
        Venta nuevaVenta = getVenta(productos, cliente);

        // Guardar la venta en la BD
        Venta ventaGuardada = ventaRepo.save(nuevaVenta);

        // Descontar stock y actualizar productos en la BD
        descontarProductos(productos);

        return ventaGuardada; // Se devuelve la venta creada
    }


    public List<Venta> getVentas() {
        return ventaRepo.findAll();
    }


    public Venta findVenta(Long id) {
        return ventaRepo.findById(id).orElse(null);
    }

    public void deleteVenta(Long id) {
        Venta venta = ventaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada con ID: " + id));

        for (Producto p : venta.getProductos()) {
            p.aumentarCantidad();
        }

        // Guardar los productos actualizados en la base de datos
        productoRepo.saveAll(venta.getProductos());

        // Eliminar la venta de la base de datos
        ventaRepo.delete(venta);
    }


    public List<Producto> productosDeUnaVenta(Long id) {
        Venta venta = ventaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));
        return venta.getProductos();
    }


    public List<Venta> ventasPorDia(LocalDate fecha) {
        List<Venta> ventas = ventaRepo.findByFechaVenta(fecha)
                .orElseThrow(()-> new IllegalArgumentException("No hay ventas para esa fecha"));
        return ventas;

    }

    private ProductoVentaDTO convertirAVentaDTO(Venta venta) {
        return new ProductoVentaDTO(venta.getId(), venta.getTotal(), venta.getProductos().size(), venta.getCliente().getNombre(), venta.getCliente().getApellido());
    }


    public ProductoVentaDTO obtenerVentaMayor() {
        List<Venta> listaVentas = this.getVentas();
        Venta ventaMayor = listaVentas.stream()
                .max(Comparator.comparingDouble(Venta::obtenerMonto))
                .orElseThrow(() -> new IllegalArgumentException("No hay ventas registradas"));
        return convertirAVentaDTO(ventaMayor);


    }




}
