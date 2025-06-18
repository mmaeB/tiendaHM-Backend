package com.proyecto.tienda_deportiva.controller;

import com.proyecto.tienda_deportiva.dto.InventoryDTO;
import com.proyecto.tienda_deportiva.model.Inventory;
import com.proyecto.tienda_deportiva.model.Producto;
import com.proyecto.tienda_deportiva.service.IInventoryService;
import com.proyecto.tienda_deportiva.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InventoryController {

    private final IInventoryService inventoryService;
    private final IProductoService productoService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<InventoryDTO>> listar() throws Exception {
        List<Inventory> list = inventoryService.findAll();
        List<InventoryDTO> dtoList = list.stream()
                .map(inv -> {
                    InventoryDTO dto = modelMapper.map(inv, InventoryDTO.class);
                    dto.setIdProduct(inv.getProducto().getIdProduct());
                    dto.setNombreProducto(inv.getProducto().getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> obtenerPorId(@PathVariable Integer id) throws Exception {
        Inventory inv = inventoryService.findById(id);
        InventoryDTO dto = modelMapper.map(inv, InventoryDTO.class);
        dto.setIdProduct(inv.getProducto().getIdProduct());
        dto.setNombreProducto(inv.getProducto().getNombre());
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<InventoryDTO> crear(@RequestBody InventoryDTO dto) throws Exception {
        Inventory inv = new Inventory();
        inv.setStockActual(dto.getStockActual());
        inv.setStockMinimo(dto.getStockMinimo());
        inv.setUltimaActualizacion(dto.getUltimaActualizacion());

        Producto producto = productoService.findById(dto.getIdProduct());
        inv.setProducto(producto);

        Inventory saved = inventoryService.save(inv);
        InventoryDTO response = modelMapper.map(saved, InventoryDTO.class);
        response.setIdProduct(saved.getProducto().getIdProduct());
        response.setNombreProducto(saved.getProducto().getNombre());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> actualizar(@RequestBody InventoryDTO dto, @PathVariable Integer id) throws Exception {
        Inventory inv = inventoryService.findById(id);
        inv.setStockActual(dto.getStockActual());
        inv.setStockMinimo(dto.getStockMinimo());
        inv.setUltimaActualizacion(dto.getUltimaActualizacion());

        Producto producto = productoService.findById(dto.getIdProduct());
        inv.setProducto(producto);

        Inventory updated = inventoryService.update(inv, id);
        InventoryDTO response = modelMapper.map(updated, InventoryDTO.class);
        response.setIdProduct(updated.getProducto().getIdProduct());
        response.setNombreProducto(updated.getProducto().getNombre());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) throws Exception {
        inventoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
