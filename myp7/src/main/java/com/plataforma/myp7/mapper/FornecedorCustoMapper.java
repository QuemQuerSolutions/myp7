package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.FornecedorCusto;

@Component
public interface FornecedorCustoMapper {
	
	List<FornecedorCusto> obterTodos();
	
	List<FornecedorCusto> obterComFiltro(FornecedorCusto fc);
}
