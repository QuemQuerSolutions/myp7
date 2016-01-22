package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.dto.UsuarioDTO;

@Component
public interface UsuarioMapper {
	
	List<Usuario> obterTodos();
	
	Usuario obterPorId(Long id);
	
	Usuario obterPorEmail(String email);
	
	void incluir(Usuario usuario);
	
	void updateTheme(Usuario usuario);
	
	void update(Usuario usuario);
	
	void updateStatus(Usuario usuario);

	List<Usuario> obterUsuarioComFiltro(UsuarioDTO usuario);
	
	void updateHierarquia(UsuarioDTO usuario);
	
	void updateHierarquiaGeral(Long idSuperior);
}
