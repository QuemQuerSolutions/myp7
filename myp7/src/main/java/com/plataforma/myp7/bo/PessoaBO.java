package com.plataforma.myp7.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.Pessoa;
import com.plataforma.myp7.mapper.PessoaMapper;

@Service
public class PessoaBO {
	
	@Autowired
	private PessoaMapper pessoaMapper;
	
	public List<String> selecionaTodasUF() {
		return this.pessoaMapper.obterTodasUF();
	}
	
	public List<Pessoa> obterPessoaCodNome(Long idPessoa, String razao){
		return this.pessoaMapper.obterPessoaCodNome(idPessoa, razao);
	}
}
