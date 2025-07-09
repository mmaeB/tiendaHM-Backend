package com.tiendahm.controller;

import java.awt.PageAttributes.MediaType;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.tiendahm.dto.ProductoDTO;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tiendahm.model.Producto;
import com.tiendahm.service.IProductoService;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductoController {
	
	private final IProductoService service;
	private final ModelMapper modelMapper;

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping
	public ResponseEntity<List<ProductoDTO>> findAll() throws Exception {
	    List<ProductoDTO> list = service.findAll().stream().map(this::converToDto).toList();
	    return ResponseEntity.ok(list);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping("/{id}")
	public ResponseEntity<ProductoDTO> findById(@PathVariable("id") Integer id) throws Exception {
	    ProductoDTO obj = converToDto(service.findById(id));
	    return ResponseEntity.ok(obj);
	}


	@PostMapping
	public ResponseEntity<Void> save(@RequestBody ProductoDTO dto) throws Exception{
	
		Producto obj = service.save(convertToEntity(dto));

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getIdProduct()).toUri();
		return ResponseEntity.created(location).build();
	}

	// ✅ Solo ADMIN puede actualizar
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<ProductoDTO> update(@PathVariable("id") Integer id, @RequestBody ProductoDTO dto) throws Exception {
	    Producto obj = service.update(convertToEntity(dto), id);
	    ProductoDTO dto1 = converToDto(obj);
	    return ResponseEntity.ok(dto1);
	}

	// ✅ Solo ADMIN puede eliminar
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
	    service.delete(id);
	    return ResponseEntity.noContent().build();
	}
	@GetMapping("hateoas/{id}")
	public EntityModel<ProductoDTO> findByIdHateoas(@PathVariable("id") Integer id) throws Exception{
		Producto obj = service.findById(id);
		EntityModel<ProductoDTO> resource = EntityModel.of(converToDto(obj));
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
		WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());
		resource.add(link1.withRel("publisher-self-info"));
		resource.add(link2.withRel("publisher-all-info"));
		return resource;
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/with-image", consumes = "multipart/form-data")
	public ResponseEntity<Void> saveWithImage(
	        @RequestPart("producto") ProductoDTO dto,
	        @RequestPart("file") MultipartFile file) throws Exception {

	    // 1. Guardar archivo físicamente
	    String fileName = saveFile(file);
	    dto.setImagen(fileName); // asigna el nombre del archivo a la propiedad imagen

	    // 2. Mapear y guardar producto
	    Producto obj = service.save(convertToEntity(dto));

	    // 3. Retornar la URI del nuevo recurso
	    URI location = ServletUriComponentsBuilder
	            .fromCurrentRequest()
	            .path("/{id}")
	            .buildAndExpand(obj.getIdProduct())
	            .toUri();

	    return ResponseEntity.created(location).build();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/with-image/{id}", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ProductoDTO> updateWithImage(
	        @PathVariable Integer id,
	        @RequestPart("producto") ProductoDTO dto,
	        @RequestPart(value = "file", required = false) MultipartFile file) throws Exception {

	    // Buscar producto actual para conservar imagen si no se envía una nueva
	    Producto existing = service.findById(id);

	    if (file != null && !file.isEmpty()) {
	        String fileName = saveFile(file);
	        dto.setImagen(fileName);
	    } else {
	        dto.setImagen(existing.getImagen()); // mantener imagen anterior
	    }

	    Producto updated = service.update(convertToEntity(dto), id);
	    ProductoDTO updatedDto = converToDto(updated);
	    return ResponseEntity.ok(updatedDto);
	}

	private String saveFile(MultipartFile file) throws java.io.IOException {
	    String uploadDir = "uploads"; // carpeta donde se guardan las imágenes
	    Files.createDirectories(Paths.get(uploadDir));

	    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	    Path filePath = Paths.get(uploadDir).resolve(fileName);
	    Files.copy(file.getInputStream(), filePath);

	    return fileName;
	}




	private ProductoDTO converToDto(Producto obj){
		return modelMapper.map(obj, ProductoDTO.class);
	}

	private Producto convertToEntity(ProductoDTO dto){
		return modelMapper.map(dto, Producto.class);
	}	
}