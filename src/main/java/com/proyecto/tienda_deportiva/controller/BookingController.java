package com.proyecto.tienda_deportiva.controller;

import com.proyecto.tienda_deportiva.dto.BookingDTO;
import com.proyecto.tienda_deportiva.model.Booking;
import com.proyecto.tienda_deportiva.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BookingController {

    private final IBookingService bookingService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<BookingDTO>> listar() throws Exception {
        List<Booking> list = bookingService.findAll();
        List<BookingDTO> dtoList = list.stream()
                .map(b -> modelMapper.map(b, BookingDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> obtenerPorId(@PathVariable Integer id) throws Exception {
        Booking booking = bookingService.findById(id);
        return ResponseEntity.ok(modelMapper.map(booking, BookingDTO.class));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody BookingDTO dto, BindingResult result) throws Exception {
        if(result.hasErrors()){
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errores);
        }
        Booking booking = modelMapper.map(dto, Booking.class);
        Booking saved = bookingService.save(booking);
        return ResponseEntity.ok(modelMapper.map(saved, BookingDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody BookingDTO dto, BindingResult result, @PathVariable Integer id) throws Exception {
        if(result.hasErrors()){
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errores);
        }
        Booking booking = modelMapper.map(dto, Booking.class);
        Booking updated = bookingService.update(booking, id);
        return ResponseEntity.ok(modelMapper.map(updated, BookingDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) throws Exception {
        bookingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

