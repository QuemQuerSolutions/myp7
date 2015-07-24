package com.plataforma.myp7.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.Pessoa;
import com.plataforma.myp7.mapper.PessoaMapper;
import com.plataforma.myp7.util.MensagemRetorno;
import com.plataforma.myp7.util.Utils;

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
	
	public MensagemRetorno salvarPessoa(Pessoa pessoa) throws Exception{
		if(pessoa.getIdPessoa()==0L){
			this.pessoaMapper.inserir(pessoa);
			return Utils.formataMsgem(7);
		}else{
			this.pessoaMapper.atualiza(pessoa);
			return Utils.formataMsgem(12);
		}
	}
		
}
