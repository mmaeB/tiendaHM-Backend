package com.tiendahm.service.impl;

import com.tiendahm.model.Category;
import com.tiendahm.repo.ICategoryRepo;
import com.tiendahm.repo.IGenericRepo;
import com.tiendahm.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService extends GenericService<Category, Integer> implements ICategoryService {

    private final ICategoryRepo repo;

    @Override
    protected IGenericRepo<Category, Integer> getRepo() {
        return repo;
    }
}
