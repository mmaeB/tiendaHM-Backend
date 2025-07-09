package com.tiendahm.controller;

import java.net.URI;
import java.util.List;

import com.tiendahm.dto.ClientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiendahm.model.Client;
import com.tiendahm.service.IClientService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClientController {
	
	private final IClientService service;
	private final ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() throws Exception{

		List<ClientDTO> list = service.findAll().stream().map(this::converToDto).toList();

		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable("id") Integer id) throws Exception{

		ClientDTO obj = converToDto(service.findById(id));
		return ResponseEntity.ok(obj);
	}

	@PostMapping
	public ResponseEntity<Void> save(@RequestBody ClientDTO dto) throws Exception{
	
		Client obj = service.save(convertToEntity(dto));

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getIdClient()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable("id") Integer id, @RequestBody ClientDTO dto) throws Exception{
		Client obj = service.update(convertToEntity(dto),id);
		ClientDTO dto1= converToDto(obj);
		return ResponseEntity.ok(dto1);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
			throws Exception{
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("hateoas/{id}")
	public EntityModel<ClientDTO> findByIdHateoas(@PathVariable("id") Integer id) throws Exception{
		Client obj = service.findById(id);
		EntityModel<ClientDTO> resource = EntityModel.of(converToDto(obj));
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
		WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());
		resource.add(link1.withRel("publisher-self-info"));
		resource.add(link2.withRel("publisher-all-info"));
		return resource;
	}

	private ClientDTO converToDto(Client obj){
		return modelMapper.map(obj, ClientDTO.class);
	}

	private Client convertToEntity(ClientDTO dto){
		return modelMapper.map(dto, Client.class);
	}	
}