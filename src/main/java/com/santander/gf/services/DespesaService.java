package com.santander.gf.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.santander.gf.dto.DespesaDto;
import com.santander.gf.exceptions.DataIntegrityException;
import com.santander.gf.exceptions.LimiteDespesaError;
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
		categoria = categoriaService.findOne(Integer.parseInt(despesaDto.getCategoriaId()));
		if (categoria.getLimite()) {
			Boolean valid = verificaLimitedeGastos(categoria.getNome(), despesaDto.getValor());
			if (!valid) {
				Despesa despesa = new Despesa(despesaDto.getNome(), despesaDto.getValor(), categoria);
				return repository.save(despesa);
			} else {
				throw new LimiteDespesaError("Limite de Gastos Excedido por Categoria");
			}

		} else {
			Despesa despesa = new Despesa(despesaDto.getNome(), despesaDto.getValor(), categoria);
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

	public BigDecimal verificaValores(String categoria) {
		Categoria pesCategoria = categoriaService.findByNome(categoria);
		return verificaSomaDespesas(pesCategoria.getId());
	}

	private void updateData(Despesa newObj, Despesa obj) {
		newObj.setNome(obj.getNome());
	}

	public Despesa convertDespesaDto(DespesaDto despesaDto) {
		this.despesa.setNome(despesaDto.getNome());
		return despesa;
	}

	public Boolean verificaLimitedeGastos(String categoriaNome, BigDecimal valorEntrada) {

		Categoria categoria = categoriaService.findByNome(categoriaNome);
		BigDecimal limite = categoria.getLimiteMaximo();
		BigDecimal somaDespesas = verificaSomaDespesas(categoria.getId());

		if (somaDespesas != null) {
			BigDecimal total = somaDespesas.add(valorEntrada);
			Boolean resultado = total.compareTo(limite) == 1;
			return resultado;
		}

		return false;

	}

	private BigDecimal verificaSomaDespesas(Integer categoriaId) {
		Month mes = LocalDate.now().getMonth();
		int ano = LocalDate.now().getYear();
		BigDecimal resultado = repository.somaValores(LocalDate.of(ano, mes, 01), LocalDate.now(), categoriaId);
		return resultado;
	}

}
