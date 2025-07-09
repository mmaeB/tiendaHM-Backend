package com.tiendahm.service.impl;

import com.tiendahm.model.Sale;
import com.tiendahm.repo.IGenericRepo;
import com.tiendahm.repo.ISaleRepo;
import com.tiendahm.service.ISaleService;
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
