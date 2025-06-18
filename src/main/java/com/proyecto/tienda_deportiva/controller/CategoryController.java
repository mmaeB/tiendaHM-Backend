package com.proyecto.tienda_deportiva.controller;

import com.proyecto.tienda_deportiva.dto.CategoryDTO;
import com.proyecto.tienda_deportiva.model.Category;
import com.proyecto.tienda_deportiva.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryController {

    private final ICategoryService categoryService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listar() throws Exception {
        List<Category> list = categoryService.findAll();
        List<CategoryDTO> dtoList = list.stream()
                .map(c -> modelMapper.map(c, CategoryDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> obtenerPorId(@PathVariable Integer id) throws Exception {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(modelMapper.map(category, CategoryDTO.class));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody CategoryDTO dto, BindingResult result) throws Exception {
        if(result.hasErrors()){
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errores);
        }
        Category category = modelMapper.map(dto, Category.class);
        Category saved = categoryService.save(category);
        return ResponseEntity.ok(modelMapper.map(saved, CategoryDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody CategoryDTO dto, BindingResult result, @PathVariable Integer id) throws Exception {
        if(result.hasErrors()){
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errores);
        }
        Category category = modelMapper.map(dto, Category.class);
        Category updated = categoryService.update(category, id);
        return ResponseEntity.ok(modelMapper.map(updated, CategoryDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) throws Exception {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
