package com.santander.gf.controller;

import java.math.BigDecimal;
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

import com.santander.gf.dto.DespesaDto;
import com.santander.gf.model.Despesa;
import com.santander.gf.services.DespesaService;

@RestController
@RequestMapping(value = "/despesas")
public class DespesasController {

	@Autowired
	DespesaService service;

	@GetMapping
	public ResponseEntity<List<Despesa>> findAll() {
		List<Despesa> despesas = service.findAll();
		return ResponseEntity.ok(despesas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Despesa> findOne(@PathVariable("id") Integer id) {
		Despesa despesa = service.findOne(id);
		return ResponseEntity.ok(despesa);
	}

	@GetMapping("/somavalores/{categoria}")
	public ResponseEntity<?> findOne(@PathVariable("categoria") String categoria) {
		if(categoria != null) {
			BigDecimal despesa = service.verificaValores(categoria);
		return ResponseEntity.ok(despesa);
		}
		return ResponseEntity.badRequest().build();
		
	}

	@PostMapping
	public ResponseEntity<Despesa> save(@Valid @RequestBody DespesaDto despesaDto) {
		Despesa despesa = service.save(despesaDto);
		return ResponseEntity.ok(despesa);
		

	}

	@PutMapping
	public ResponseEntity<Despesa> update(@Valid @RequestBody Despesa despesa) {
		Despesa newDespesa = service.update(despesa);
		return ResponseEntity.ok(newDespesa);

	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		service.delete(id);
		ResponseEntity.ok();
	}

}
