package com.santander.gf.dto;

import java.math.BigDecimal;

import com.santander.gf.model.Categoria;

public class DespesaDto {

	private String nome;
	private BigDecimal valor;
	private Categoria categoria;
	
	public DespesaDto() {}

	public DespesaDto(String nome, BigDecimal valor, Categoria categoria) {		
		this.nome = nome;
		this.valor = valor;
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}	
	

}
