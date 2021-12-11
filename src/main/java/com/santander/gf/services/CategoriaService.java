package com.santander.gf.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santander.gf.dto.CategoriaDto;
import com.santander.gf.model.Categoria;
import com.santander.gf.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository repository;

	public List<Categoria> findAll() {
		List<Categoria> categorias = repository.findAll();
		return categorias;
	}

	public void save(CategoriaDto categoria) {

	}

}
