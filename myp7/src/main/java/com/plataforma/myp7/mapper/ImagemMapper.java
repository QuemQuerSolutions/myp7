package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.Imagem;


//@Component
public interface ImagemMapper {

	public Imagem obterPorId(Long id);
	
	public List<Imagem> obterTodos();
	
}
