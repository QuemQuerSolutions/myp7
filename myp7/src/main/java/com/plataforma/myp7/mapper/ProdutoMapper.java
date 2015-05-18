package com.plataforma.myp7.mapper;

import java.util.List;

//import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.Produto;


//@Component
public interface ProdutoMapper {

	public Produto obterPorId(Long id);
	
	public List<Produto> obterTodos();
	
}
