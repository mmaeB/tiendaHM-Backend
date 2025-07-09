package com.tiendahm.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;
import com.tiendahm.model.Producto;

@Data
public class InventoryDTO {
    private Integer idInventory;

    @NotNull(message = "El producto es obligatorio")
    private Producto producto;



    @Min(value = 0, message = "El stockActual no puede ser negativo")
    private int stockActual;

    @Min(value = 0, message = "El stockMinimo no puede ser negativo")
    private int stockMinimo;

    @NotNull(message = "La ultimaActualizacion es obligatoria")
    private LocalDateTime ultimaActualizacion;
}
