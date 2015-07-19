package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.Pessoa;

@Component
public interface PessoaMapper {

	List<String> obterTodasUF();
	
	List<Pessoa> obterPessoaCodNome(Long idPessoa, String fantasia);
		
}
