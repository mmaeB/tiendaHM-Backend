package com.tiendahm.service;

import com.tiendahm.model.Menu;

import java.util.List;

public interface IMenuService {

    List<Menu> getMenusByUsername(String username);
}
