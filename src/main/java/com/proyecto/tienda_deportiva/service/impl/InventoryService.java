package com.proyecto.tienda_deportiva.service.impl;

import com.proyecto.tienda_deportiva.model.Inventory;
import com.proyecto.tienda_deportiva.repo.IGenericRepo;
import com.proyecto.tienda_deportiva.repo.IInventoryRepo;
import com.proyecto.tienda_deportiva.service.IInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService extends GenericService<Inventory, Integer> implements IInventoryService {

    private final IInventoryRepo repo;

    @Override
    protected IGenericRepo<Inventory, Integer> getRepo() {
        return repo;
    }
}
