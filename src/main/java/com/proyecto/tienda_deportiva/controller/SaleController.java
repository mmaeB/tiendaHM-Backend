package com.proyecto.tienda_deportiva.controller;

import com.proyecto.tienda_deportiva.dto.SaleDTO;
import com.proyecto.tienda_deportiva.model.Sale;
import com.proyecto.tienda_deportiva.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SaleController {

    private final ISaleService saleService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> listar() throws Exception {
        List<Sale> list = saleService.findAll();
        List<SaleDTO> dtoList = list.stream()
                .map(sale -> modelMapper.map(sale, SaleDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> obtenerPorId(@PathVariable Integer id) throws Exception {
        Sale sale = saleService.findById(id);
        return ResponseEntity.ok(modelMapper.map(sale, SaleDTO.class));
    }

    @PostMapping
    public ResponseEntity<SaleDTO> crear(@RequestBody SaleDTO dto) throws Exception {
        Sale sale = modelMapper.map(dto, Sale.class);
        Sale saved = saleService.save(sale);
        return ResponseEntity.ok(modelMapper.map(saved, SaleDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> actualizar(@RequestBody SaleDTO dto, @PathVariable Integer id) throws Exception {
        Sale sale = modelMapper.map(dto, Sale.class);
        Sale updated = saleService.update(sale, id);
        return ResponseEntity.ok(modelMapper.map(updated, SaleDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) throws Exception {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
