package com.proyecto.tienda_deportiva.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingDTO {
    private Integer idBooking;
    private Integer idClient;
    private String nombreCliente;
    private Integer idProduct;
    private String nombreProducto;
    private int cantidad;
    private LocalDateTime fechaReserva;
    private String estado;
}
