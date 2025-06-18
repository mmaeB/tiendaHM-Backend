package com.proyecto.tienda_deportiva.service.impl;

import com.proyecto.tienda_deportiva.model.Sale;
import com.proyecto.tienda_deportiva.repo.IGenericRepo;
import com.proyecto.tienda_deportiva.repo.ISaleRepo;
import com.proyecto.tienda_deportiva.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleService extends GenericService<Sale, Integer> implements ISaleService {

    private final ISaleRepo repo;

    @Override
    protected IGenericRepo<Sale, Integer> getRepo() {
        return repo;
    }
}
