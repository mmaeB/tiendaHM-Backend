package com.tiendahm.service.impl;

import com.tiendahm.model.Menu;
import com.tiendahm.repo.IGenericRepo;
import com.tiendahm.repo.IMenuRepo;
import com.tiendahm.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService extends GenericService<Menu, Integer> implements IMenuService {
    private final IMenuRepo repo;

    @Override
    protected IGenericRepo<Menu, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Menu> getMenusByUsername(String username) {
        return repo.getMenusByUsername(username);
    }
}
