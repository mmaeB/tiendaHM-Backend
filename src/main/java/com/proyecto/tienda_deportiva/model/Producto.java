package com.proyecto.tienda_deportiva.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer IdProduct;

    @Column(nullable = false, length = 100)
    private String nombre;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;

    @Column(nullable = false)
    private double precio;

    @Column(length = 255)
    private String descripcion;

    @Column(length = 50)
    private String marca;

    @Column(length = 10)
    private String talla;

    @Column(length = 20)
    private String genero;

    @Column(length = 100)
    private String equipo;



    @Column(nullable = false)
    private int stock;

    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL)
    private Inventory inventario;

}

