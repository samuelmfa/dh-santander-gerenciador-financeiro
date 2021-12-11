package com.santander.gf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santander.gf.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
