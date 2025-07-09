package com.tiendahm.service.impl;

import com.tiendahm.model.Inventory;
import com.tiendahm.repo.IGenericRepo;
import com.tiendahm.repo.IInventoryRepo;
import com.tiendahm.service.IInventoryService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService extends GenericService<Inventory, Integer> implements IInventoryService {

    private final IInventoryRepo repo;
    
    
    public Inventory registrarInventario(Inventory inventario) {
        inventario.setUltimaActualizacion(LocalDateTime.now());
        return repo.save(inventario);
    }

    @Override
    protected IGenericRepo<Inventory, Integer> getRepo() {
        return repo;
    }
    


}
