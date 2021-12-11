package com.santander.gf.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santander.gf.dto.CategoriaDto;
import com.santander.gf.model.Categoria;
import com.santander.gf.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

	@Autowired
	CategoriaService service;

	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> categorias = service.findAll();
		return ResponseEntity.ok(categorias);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findOne(@PathVariable("id") Integer id) {
		Categoria categoria = service.findOne(id);
		return ResponseEntity.ok(categoria);
	}

	@PostMapping
	public ResponseEntity<Categoria> save(@Valid @RequestBody CategoriaDto categoriaDto) {
		Categoria categoria = service.save(categoriaDto);
		return ResponseEntity.ok(categoria);

	}

	@PutMapping
	public ResponseEntity<Categoria> update(@Valid @RequestBody Categoria categoria) {
		Categoria newCategoria = service.update(categoria);
		return ResponseEntity.ok(newCategoria);

	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		service.delete(id);
		ResponseEntity.ok();
	}

}
