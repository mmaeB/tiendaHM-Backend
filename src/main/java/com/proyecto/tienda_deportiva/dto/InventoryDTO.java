package com.proyecto.tienda_deportiva.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class InventoryDTO {
    private Integer idInventory;

    @NotNull(message = "El idProduct es obligatorio")
    private Integer idProduct;

    @NotBlank(message = "El nombreProducto es obligatorio")
    private String nombreProducto;

    @Min(value = 0, message = "El stockActual no puede ser negativo")
    private int stockActual;

    @Min(value = 0, message = "El stockMinimo no puede ser negativo")
    private int stockMinimo;

    @NotNull(message = "La ultimaActualizacion es obligatoria")
    private LocalDateTime ultimaActualizacion;
}
