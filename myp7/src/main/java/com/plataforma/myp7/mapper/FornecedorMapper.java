package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.Fornecedor;

@Component
public interface FornecedorMapper {
	
	Fornecedor obterFornecedorPorId(Long idFornecedor);

	List<Fornecedor> obterTodos();
	
	void inserir(Fornecedor fornecedor);
}
