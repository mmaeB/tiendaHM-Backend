package com.tiendahm.dto;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import com.tiendahm.model.Producto;
import com.tiendahm.model.Client;
@Data
public class BookingDTO {
    private Integer idBooking;
    private Client client;
    private Producto product;
    private int cantidad;
    private LocalDateTime fechaReserva;
    private String estado;
}
