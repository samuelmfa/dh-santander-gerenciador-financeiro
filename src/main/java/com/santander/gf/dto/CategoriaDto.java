package com.santander.gf.dto;

import javax.validation.constraints.NotEmpty;

public class CategoriaDto {

	private String nome;

	public CategoriaDto() {
	}

	public CategoriaDto(String nome) {
		this.nome = nome;
	}

	@NotEmpty(message = "Campo Requerido")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
