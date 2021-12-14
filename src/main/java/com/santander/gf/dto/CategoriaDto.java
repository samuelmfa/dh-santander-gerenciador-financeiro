package com.santander.gf.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

public class CategoriaDto {

	@NotEmpty(message = "Campo Requerido")
	private String nome;
	private Boolean limite = false;
	private BigDecimal limiteMaximo;

	public CategoriaDto() {
	}

	public CategoriaDto(String nome, Boolean limite, BigDecimal limiteMaximo) {
		this.nome = nome;
		this.limite = limite;
		this.limiteMaximo = limiteMaximo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getLimite() {
		return limite;
	}

	public void setLimite(Boolean limite) {
		this.limite = limite;
	}

	public BigDecimal getLimiteMaximo() {
		return limiteMaximo;
	}

	public void setLimiteMaximo(BigDecimal limiteMaximo) {
		this.limiteMaximo = limiteMaximo;
	}

	@Override
	public String toString() {
		return "CategoriaDto [nome=" + nome + ", limite=" + limite + ", limiteMaximo=" + limiteMaximo + "]";
	}

}
