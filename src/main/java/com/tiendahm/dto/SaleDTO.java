package com.tiendahm.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

import com.tiendahm.model.Client;
import com.tiendahm.model.Producto;

@Data
public class SaleDTO {
    private Integer idSale;

 

   
    private Client client;
    private Producto product;


    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private int cantidad;

    @NotNull(message = "La fechaVenta es obligatoria")
    private LocalDateTime fechaVenta;
}
