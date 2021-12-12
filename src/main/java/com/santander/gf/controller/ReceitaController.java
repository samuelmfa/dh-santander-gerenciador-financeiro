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

import com.santander.gf.dto.ReceitaDto;
import com.santander.gf.model.Receita;
import com.santander.gf.services.ReceitaService;

@RestController
@RequestMapping(value = "/receitas")
public class ReceitaController {

	@Autowired
	ReceitaService service;

	@GetMapping
	public ResponseEntity<List<Receita>> findAll() {
		List<Receita> receitas = service.findAll();
		return ResponseEntity.ok(receitas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Receita> findOne(@PathVariable("id") Integer id) {
		Receita receita = service.findOne(id);
		return ResponseEntity.ok(receita);
	}

	@PostMapping
	public ResponseEntity<Receita> save(@Valid @RequestBody ReceitaDto receitaDto) {
		Receita receita = service.save(receitaDto);
		return ResponseEntity.ok(receita);

	}

	@PutMapping
	public ResponseEntity<Receita> update(@Valid @RequestBody Receita receita) {
		Receita newReceita = service.update(receita);
		return ResponseEntity.ok(newReceita);

	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		service.delete(id);
		ResponseEntity.ok();
	}

}
