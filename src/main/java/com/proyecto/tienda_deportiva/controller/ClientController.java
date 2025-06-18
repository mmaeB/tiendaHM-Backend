package com.proyecto.tienda_deportiva.controller;

import com.proyecto.tienda_deportiva.dto.ClientDTO;
import com.proyecto.tienda_deportiva.model.Client;
import com.proyecto.tienda_deportiva.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClientController {

    private final IClientService clientService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> listar() throws Exception {
        List<Client> list = clientService.findAll();
        List<ClientDTO> dtoList = list.stream()
                .map(c -> modelMapper.map(c, ClientDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> obtenerPorId(@PathVariable Integer id) throws Exception {
        Client client = clientService.findById(id);
        return ResponseEntity.ok(modelMapper.map(client, ClientDTO.class));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody ClientDTO dto, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errores);
        }

        Client client = modelMapper.map(dto, Client.class);
        Client saved = clientService.save(client);
        return ResponseEntity.ok(modelMapper.map(saved, ClientDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody ClientDTO dto, BindingResult result, @PathVariable Integer id) throws Exception {
        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errores);
        }

        Client client = modelMapper.map(dto, Client.class);
        Client updated = clientService.update(client, id);
        return ResponseEntity.ok(modelMapper.map(updated, ClientDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) throws Exception {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
