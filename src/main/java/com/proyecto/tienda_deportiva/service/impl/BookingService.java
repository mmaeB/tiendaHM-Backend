package com.proyecto.tienda_deportiva.service.impl;

import com.proyecto.tienda_deportiva.model.Booking;
import com.proyecto.tienda_deportiva.repo.IBookingRepo;
import com.proyecto.tienda_deportiva.repo.IGenericRepo;
import com.proyecto.tienda_deportiva.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService extends GenericService<Booking, Integer> implements IBookingService {

    private final IBookingRepo repo;

    @Override
    protected IGenericRepo<Booking, Integer> getRepo() {
        return repo;
    }
}
