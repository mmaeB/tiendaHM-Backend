package com.proyecto.tienda_deportiva.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SaleDTO {
    private Integer idSale;

    @NotNull(message = "El idClient es obligatorio")
    private Integer idClient;

    @NotBlank(message = "El nombreCliente es obligatorio")
    private String nombreCliente;

    @NotNull(message = "El idProduct es obligatorio")
    private Integer idProduct;

    @NotBlank(message = "El nombreProducto es obligatorio")
    private String nombreProducto;

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private int cantidad;

    @NotNull(message = "La fechaVenta es obligatoria")
    private LocalDateTime fechaVenta;
}
