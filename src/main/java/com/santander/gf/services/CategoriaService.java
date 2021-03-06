package com.santander.gf.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.santander.gf.dto.CategoriaDto;
import com.santander.gf.exceptions.DataIntegrityException;
import com.santander.gf.model.Categoria;
import com.santander.gf.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository repository;

	private Categoria categoria = new Categoria();

	public List<Categoria> findAll() {
		List<Categoria> categorias = repository.findAll();
		return categorias;
	}

	public Categoria findOne(Integer id) {
		Optional<Categoria> categoria = repository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName(), null));

	}

	public Categoria findByNome(String nome) {
		Optional<Categoria> categoria = repository.findByNome(nome);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Nome: " + nome + ", Tipo: " + Categoria.class.getName(), null));
	}

	public Categoria save(CategoriaDto categoriaDto) {
		return repository.save(convertCategoriaDto(categoriaDto));
	}

	public Categoria update(Categoria categoria) {
		Categoria newObj = findOne(categoria.getId());
		updateData(newObj, categoria);
		return repository.save(newObj);

	}

	public void delete(Integer id) {
		findOne(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possivel excluir uma categoria que possui Vinculo Receitas/Despesas");
		}

	}

	public Categoria updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
		newObj.setLimite(obj.getLimite());
		newObj.setLimiteMaximo(obj.getLimiteMaximo());
		return newObj;
	}

	public Categoria convertCategoriaDto(CategoriaDto categoriaDto) {
		this.categoria.setNome(categoriaDto.getNome());
		this.categoria.setLimite(categoriaDto.getLimite());
		this.categoria.setLimiteMaximo(categoriaDto.getLimiteMaximo());
		return categoria;
	}

}
