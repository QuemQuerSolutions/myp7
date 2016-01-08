package com.plataforma.myp7.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.FornecedorCusto;

@Component
public interface FornecedorCustoMapper {
	
	List<FornecedorCusto> obterTodos();
	
	List<FornecedorCusto> obterComFiltro(FornecedorCusto fc);

	void atualizarFornecedorCusto(FornecedorCusto fc) throws Exception;

	List<FornecedorCusto> qtdPorSituacao(Long idUsuario);

	List<FornecedorCusto> obterFornecedorCustoAprovacao(FornecedorCusto fornecedorCusto);
	
	List<FornecedorCusto> obterCustoAprovacaoPorFornecedor(FornecedorCusto fornecedorCusto);

	int countFornecedorCustoAprovacao(FornecedorCusto fornecedorCusto);

	void updateStatusFornecedorCusto(@Param("situacao") String situacao, 
									 @Param("idFornecedorCusto") Long idFornecedorCusto,
									 @Param("idUsuAprovacao") Long idUsuAprovacao,
									 @Param("dataAprovacao") Date dataAprovacao);
}
