package com.tiendahm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer IdBooking;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_product", nullable = false)
    private Producto product;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "fecha_reserva", nullable = false)
    private LocalDateTime fechaReserva;
    @PrePersist
    public void asignarFechasIniciales() {
        LocalDateTime ahora = LocalDateTime.now();       
        this.fechaReserva = ahora;
    }

    @Column(length = 20, nullable = false)
    private String estado;
}
