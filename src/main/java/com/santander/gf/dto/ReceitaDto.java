package com.santander.gf.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

public class ReceitaDto {

	@NotEmpty(message = "Campo Requerido")
	private String nome;

	private BigDecimal valor;
	private LocalDate dataLancamento;

	@NotEmpty(message = "Campo Requerido")
	private String categoriaId;

	public ReceitaDto() {
	}

	public ReceitaDto(String nome, BigDecimal valor, LocalDate dataLancamento, String categoriaId) {
		this.nome = nome;
		this.valor = valor;
		this.dataLancamento = dataLancamento;
		this.categoriaId = categoriaId;
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

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(String categoriaId) {
		this.categoriaId = categoriaId;
	}

	@Override
	public String toString() {
		return "ReceitaDto [nome=" + nome + ", valor=" + valor + ", dataLancamento=" + dataLancamento + ", categoriaId="
				+ categoriaId + "]";
	}

}
