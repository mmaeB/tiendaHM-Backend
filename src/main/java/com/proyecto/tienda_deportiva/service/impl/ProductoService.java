package com.proyecto.tienda_deportiva.service.impl;

import com.proyecto.tienda_deportiva.model.Producto;
import com.proyecto.tienda_deportiva.repo.IGenericRepo;
import com.proyecto.tienda_deportiva.repo.IProductoRepo;
import com.proyecto.tienda_deportiva.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductoService extends GenericService<Producto, Integer> implements IProductoService {

	private final IProductoRepo repo;

	@Override
	protected IGenericRepo<Producto, Integer> getRepo() {
		return repo;
	}
}
