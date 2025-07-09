	package com.tiendahm.model;
	
	import jakarta.persistence.*;
	import lombok.AllArgsConstructor;
	import lombok.Data;
	import lombok.EqualsAndHashCode;
	import lombok.NoArgsConstructor;
	
	import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Entity
	@Table(name = "Inventory")
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public class Inventory {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @EqualsAndHashCode.Include
	    private Integer IdInventory;
	
	    @OneToOne(optional = false)
	    @JoinColumn(name = "id_product", nullable = false, unique = true)
	    @JsonManagedReference
	    private Producto producto;
	    
	    @Column(name = "stock_actual", nullable = false)
	    private int stockActual;
	
	    @Column(name = "stock_minimo", nullable = false)
	    private int stockMinimo;
	
	    @Column(name = "ultima_actualizacion", nullable = false)
	    private LocalDateTime ultimaActualizacion;
	    @PrePersist
	    @PreUpdate
	    public void actualizarFecha() {
	        this.ultimaActualizacion = LocalDateTime.now();
	    }
	
	}
