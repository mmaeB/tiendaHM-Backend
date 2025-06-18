package com.proyecto.tienda_deportiva.service.impl;

import com.proyecto.tienda_deportiva.model.Category;
import com.proyecto.tienda_deportiva.repo.ICategoryRepo;
import com.proyecto.tienda_deportiva.repo.IGenericRepo;
import com.proyecto.tienda_deportiva.service.ICategoryService;
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
