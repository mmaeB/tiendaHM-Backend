package com.tiendahm.repo;

import com.tiendahm.model.Client;
import com.tiendahm.model.User;

public interface IClientRepo extends IGenericRepo<Client, Integer> {
    boolean existsByDni(String dni);

}
