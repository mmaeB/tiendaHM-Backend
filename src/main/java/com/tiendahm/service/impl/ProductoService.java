package com.tiendahm.service.impl;

import com.tiendahm.model.Producto;
import com.tiendahm.repo.IGenericRepo;
import com.tiendahm.repo.IProductoRepo;
import com.tiendahm.service.IProductoService;
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
