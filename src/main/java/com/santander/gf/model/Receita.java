package com.santander.gf.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class Receita extends Lancamento {

	private static final long serialVersionUID = 1L;

	private String type = "R";

	public Receita() {
	}

	public Receita(String nome, BigDecimal valor, Categoria categoria) {
		super(nome, valor, categoria);
	}

	public Receita(String nome, BigDecimal valor, LocalDate dataLancamento, Categoria categoria) {
		super(nome, valor, dataLancamento, categoria);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
