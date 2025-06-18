package com.proyecto.tienda_deportiva.repo;

import com.proyecto.tienda_deportiva.model.Client;

public interface IClientRepo extends IGenericRepo<Client, Integer> {
    boolean existsByDni(String dni);
}
