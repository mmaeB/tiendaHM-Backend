package com.tiendahm.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import com.tiendahm.model.Category;

@Data
public class ProductoDTO {

    @NotNull(message = "El idProduct es obligatorio")
    private Integer idProduct;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Positive(message = "El precio debe ser un valor positivo")
    private double precio;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @NotBlank(message = "La talla es obligatoria")
    private String talla;

    @NotBlank(message = "El género es obligatorio")
    private String genero;

    @NotBlank(message = "El equipo es obligatorio")
    private String equipo;
    
    @NotBlank(message = "El género es obligatorio")
    private String imagen;


    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;

    @NotNull(message = "La categoría es obligatoria")
    private Category category;
}
