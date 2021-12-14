package com.santander.gf.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.santander.gf.model.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Integer> {

	@Query("SELECT DISTINCT sum(v.valor) from Lancamento v WHERE v.dataLancamento  between :dataInicial and :dataFinal and v.type = 'D' and :categoriaId = v.categoria.id")
	BigDecimal somaValores(LocalDate dataInicial, LocalDate dataFinal, Integer categoriaId);

}
