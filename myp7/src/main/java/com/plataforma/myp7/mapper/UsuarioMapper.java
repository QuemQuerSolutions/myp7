package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.Usuario;


@Component
public interface UsuarioMapper {

	public Usuario obterPorId(Long id);
	
	public List<Usuario> obterTodos();
}
