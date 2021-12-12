package com.santander.gf.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.santander.gf.dto.ReceitaDto;
import com.santander.gf.exceptions.DataIntegrityException;
import com.santander.gf.model.Categoria;
import com.santander.gf.model.Receita;
import com.santander.gf.repositories.ReceitaRepository;

@Service
public class ReceitaService {

	@Autowired
	ReceitaRepository repository;

	@Autowired
	CategoriaService categoriaService;

	private Receita receita = new Receita();
	private Categoria categoria = new Categoria();

	public List<Receita> findAll() {
		List<Receita> receitas = repository.findAll();
		return receitas;
	}

	public Receita findOne(Integer id) {
		Optional<Receita> receita = repository.findById(id);
		return receita.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Receita.class.getName(), null));
	}

	public Receita save(ReceitaDto receitaDto) {

		try {
			this.categoria = categoriaService.findByNome(receitaDto.getCategoria().getNome());
			Receita receita = new Receita(receitaDto.getNome(), receitaDto.getValor(), this.categoria);
			return repository.save(receita);
			
		} catch (Exception e) {
			this.categoria = categoriaService.save(receitaDto.getCategoria());
			Receita receita = new Receita(receitaDto.getNome(), receitaDto.getValor(), this.categoria);
			return repository.save(receita);
			
		}

	}

	public Receita update(Receita receita) {
		Receita newObj = findOne(receita.getId());
		updateData(newObj, receita);
		return repository.save(newObj);

	}

	public void delete(Integer id) {
		findOne(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir essa receita");
		}

	}

	private void updateData(Receita newObj, Receita obj) {
		newObj.setNome(obj.getNome());
	}

	public Receita convertReceitaDto(ReceitaDto receitaDto) {
		this.receita.setNome(receitaDto.getNome());
		return receita;
	}

}
