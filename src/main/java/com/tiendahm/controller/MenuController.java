package com.tiendahm.controller;

import com.tiendahm.dto.MenuDTO;
import com.tiendahm.service.IMenuService;
import com.tiendahm.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {

    private final IMenuService service;
    private final MapperUtil mapperUtil;

    @GetMapping("/user")
    public ResponseEntity<List<MenuDTO>> getMenusByUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(">>> Usuario autenticado: " + username);

        List<MenuDTO> listDTO = mapperUtil.mapList(service.getMenusByUsername(username), MenuDTO.class);
        return ResponseEntity.ok(listDTO);
    }

}