package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.HistoricoSenha;
import com.plataforma.myp7.data.Usuario;

@Component
public interface HistoricoSenhaMapper {

	List<HistoricoSenha> obterPorUsuarioELimit(Long usuario, int qtd);
	
	Usuario obterUsuarioPorId(Long usuario);
}
