package com.tiendahm.service.impl;

import com.tiendahm.model.Booking;
import com.tiendahm.model.Inventory;
import com.tiendahm.repo.IBookingRepo;
import com.tiendahm.repo.IGenericRepo;
import com.tiendahm.service.IBookingService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

