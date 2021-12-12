package com.santander.gf.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.santander.gf.dto.DespesaDto;
import com.santander.gf.exceptions.DataIntegrityException;
import com.santander.gf.model.Categoria;
import com.santander.gf.model.Despesa;
import com.santander.gf.repositories.DespesaRepository;

@Service
public class DespesaService {

	@Autowired
	DespesaRepository repository;

	@Autowired
	CategoriaService categoriaService;

	private Despesa despesa = new Despesa();
	private Categoria categoria = new Categoria();

	public List<Despesa> findAll() {
		List<Despesa> despesas = repository.findAll();
		return despesas;
	}

	public Despesa findOne(Integer id) {
		Optional<Despesa> despesa = repository.findById(id);
		return despesa.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Despesa.class.getName(), null));
	}

	public Despesa save(DespesaDto despesaDto) {

		try {
			this.categoria = categoriaService.findByNome(despesaDto.getCategoria().getNome());
			Despesa despesa = new Despesa(despesaDto.getNome(), despesaDto.getValor(), this.categoria);
			return repository.save(despesa);

		} catch (Exception e) {
			this.categoria = categoriaService.save(despesaDto.getCategoria());
			Despesa despesa = new Despesa(despesaDto.getNome(), despesaDto.getValor(), this.categoria);
			return repository.save(despesa);
		}

	}

	public Despesa update(Despesa despesa) {
		Despesa newObj = findOne(despesa.getId());
		updateData(newObj, despesa);
		return repository.save(newObj);

	}

	public void delete(Integer id) {
		findOne(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir essa despesa");
		}

	}

	private void updateData(Despesa newObj, Despesa obj) {
		newObj.setNome(obj.getNome());
	}

	public Despesa convertDespesaDto(DespesaDto despesaDto) {
		this.despesa.setNome(despesaDto.getNome());
		return despesa;
	}

}
