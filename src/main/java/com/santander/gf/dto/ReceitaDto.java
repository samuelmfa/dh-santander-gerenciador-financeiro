package com.santander.gf.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

public class ReceitaDto {

	@NotEmpty(message = "Campo Requerido")
	private String nome;

	private BigDecimal valor;
	private LocalDate dataLancamento;
		
	private CategoriaDto categoria;

	public ReceitaDto() {
	}

	public ReceitaDto(String nome, BigDecimal valor, CategoriaDto categoria) {
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

	public CategoriaDto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDto categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "ReceitaDto [nome=" + nome + ", valor=" + valor + ", dataLancamento=" + dataLancamento + ", categoria="
				+ categoria.getNome() + "]";
	}

	

}
