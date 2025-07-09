package com.tiendahm.controller;

import java.net.URI;
import java.util.List;

import com.tiendahm.dto.SaleDTO;
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

import com.tiendahm.model.Sale;
import com.tiendahm.service.ISaleService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SaleController {
	
	private final ISaleService service;
	private final ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<List<SaleDTO>> findAll() throws Exception{

		List<SaleDTO> list = service.findAll().stream().map(this::converToDto).toList();

		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SaleDTO> findById(@PathVariable("id") Integer id) throws Exception{

		SaleDTO obj = converToDto(service.findById(id));
		return ResponseEntity.ok(obj);
	}

	@PostMapping
	public ResponseEntity<Void> save(@RequestBody SaleDTO dto) throws Exception{
	
		Sale obj = service.save(convertToEntity(dto));

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getIdSale()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<SaleDTO> update(@PathVariable("id") Integer id, @RequestBody SaleDTO dto) throws Exception{
		Sale obj = service.update(convertToEntity(dto),id);
		SaleDTO dto1= converToDto(obj);
		return ResponseEntity.ok(dto1);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
			throws Exception{
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("hateoas/{id}")
	public EntityModel<SaleDTO> findByIdHateoas(@PathVariable("id") Integer id) throws Exception{
		Sale obj = service.findById(id);
		EntityModel<SaleDTO> resource = EntityModel.of(converToDto(obj));
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
		WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());
		resource.add(link1.withRel("publisher-self-info"));
		resource.add(link2.withRel("publisher-all-info"));
		return resource;
	}

	private SaleDTO converToDto(Sale obj){
		return modelMapper.map(obj, SaleDTO.class);
	}

	private Sale convertToEntity(SaleDTO dto){
		return modelMapper.map(dto, Sale.class);
	}	
}