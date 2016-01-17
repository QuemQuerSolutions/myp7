package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.Usuario;

@Component
public interface UsuarioMapper {
	
	List<Usuario> obterTodos();
	
	Usuario obterPorId(Long id);
	
	Usuario obterPorEmail(String email);
	
	void incluir(Usuario usuario);
	
	void updateTheme(Usuario usuario);
	
	void update(Usuario usuario);
	
	void updateStatus(Usuario usuario);

	List<Usuario> obterUsuarioComFiltro(Usuario usuario);
}
