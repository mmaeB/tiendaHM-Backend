package com.tiendahm.service.impl;

import com.tiendahm.model.Client;
import com.tiendahm.repo.IClientRepo;
import com.tiendahm.repo.IGenericRepo;
import com.tiendahm.service.IClientService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService extends GenericService<Client, Integer> implements IClientService {

    private final IClientRepo repo;
 

    @Override
    protected IGenericRepo<Client, Integer> getRepo() {
        return repo;
    }
}
