package com.proyecto.tienda_deportiva.controller;

import com.proyecto.tienda_deportiva.dto.ProductoDTO;
import com.proyecto.tienda_deportiva.model.Producto;
import com.proyecto.tienda_deportiva.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductoController {

    private final IProductoService productoService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProductos() throws Exception {
        List<Producto> productos = productoService.findAll();
        List<ProductoDTO> productosDTO = productos.stream()
                .map(producto -> modelMapper.map(producto, ProductoDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(productosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Integer id) throws Exception {
        Producto producto = productoService.findById(id);
        ProductoDTO productoDTO = modelMapper.map(producto, ProductoDTO.class);
        return ResponseEntity.ok(productoDTO);
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDTO) throws Exception {
        Producto producto = modelMapper.map(productoDTO, Producto.class);
        Producto productoGuardado = productoService.save(producto);
        ProductoDTO productoGuardadoDTO = modelMapper.map(productoGuardado, ProductoDTO.class);
        return ResponseEntity.ok(productoGuardadoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@RequestBody ProductoDTO productoDTO, @PathVariable Integer id) throws Exception {
        Producto producto = modelMapper.map(productoDTO, Producto.class);
        Producto productoActualizado = productoService.update(producto, id);
        ProductoDTO productoActualizadoDTO = modelMapper.map(productoActualizado, ProductoDTO.class);
        return ResponseEntity.ok(productoActualizadoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) throws Exception {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
