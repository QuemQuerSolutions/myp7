package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.FornecedorCusto;

@Component
public interface FornecedorCustoMapper {
	
	List<FornecedorCusto> obterTodos();
	
	List<FornecedorCusto> obterComFiltro(FornecedorCusto fc);

	void atualizarFornecedorCusto(FornecedorCusto fc) throws Exception;

	List<FornecedorCusto> qtdPorSituacao(Long idUsuario);

	List<FornecedorCusto> obterFornecedorCustoAprovacao(FornecedorCusto fornecedorCusto);

	int countFornecedorCustoAprovacao(FornecedorCusto fornecedorCusto);

	void updateStatus(String sigla, Long idFornecedorCusto);
}
