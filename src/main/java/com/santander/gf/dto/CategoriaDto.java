package com.santander.gf.dto;

import javax.validation.constraints.NotEmpty;

public class CategoriaDto {

	@NotEmpty(message = "Campo Requerido")
	private String nome;

	public CategoriaDto() {
	}

	public CategoriaDto(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
