package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.toLike;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.Pessoa;
import com.plataforma.myp7.dto.MensagemRetornoDTO;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.mapper.PessoaMapper;

@Service
public class PessoaBO {
	
	@Autowired
	private PessoaMapper pessoaMapper;
	
	public List<String> selecionaTodasUF() {
		return this.pessoaMapper.obterTodasUF();
	}
	
	public List<Pessoa> obterPessoaPorParametro(Pessoa pessoa){
		pessoa.setRazao(toLike(pessoa.getRazao()));
		return this.pessoaMapper.obterPessoaCodNome(pessoa);
	}
	
	public List<Pessoa> obterPessoaCodNome(Pessoa pessoa){
		return this.pessoaMapper.obterPessoaCodNome(pessoa);
	}
	
	public MensagemRetornoDTO salvarPessoa(Pessoa pessoa) throws Exception{
		if(pessoa.getIdPessoa()==0L){
			this.pessoaMapper.inserir(pessoa);
			return MensagemWS.getMensagem(MensagemWS.INSERT_PESSOA_SUCESSO);
		}else{
			this.pessoaMapper.atualiza(pessoa);
			return MensagemWS.getMensagem(MensagemWS.ATUALIZA_PESSOA_SUCESSO);
		}
	}
		
}
