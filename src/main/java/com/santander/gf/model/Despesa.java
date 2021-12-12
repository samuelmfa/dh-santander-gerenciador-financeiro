package com.santander.gf.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class Despesa extends Lancamento {

	private static final long serialVersionUID = 1L;

	private String type = "D";

	public Despesa() {
		super();
	}

	public Despesa(String nome, BigDecimal valor, Categoria categoria) {
		super(nome, valor, categoria);

	}
	
	public Despesa(String nome, BigDecimal valor, LocalDate dataLancamento, Categoria categoria) {
		super(nome, valor, dataLancamento, categoria);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
