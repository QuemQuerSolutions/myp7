package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.Pessoa;

@Component
public interface PessoaMapper {

	List<String> obterTodasUF();
	
	List<Pessoa> obterPessoaCodNome(Pessoa pessoa);
	
	void inserir(Pessoa pessoa);
	
	void atualiza(Pessoa pessoa);
	
	Pessoa obterPessoaPorId(Long idPessoa);
}
