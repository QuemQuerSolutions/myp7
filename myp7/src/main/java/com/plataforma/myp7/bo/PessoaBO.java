package com.plataforma.myp7.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.Pessoa;
import com.plataforma.myp7.dto.MensagemRetornoDTO;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.enums.Mensagem;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.mapper.PessoaMapper;
import com.plataforma.myp7.util.Utils;

@Service
public class PessoaBO {
	
	@Autowired
	private PessoaMapper pessoaMapper;
	
	public List<String> selecionaTodasUF() {
		return this.pessoaMapper.obterTodasUF();
	}
	
	public List<Pessoa> obterPessoaPorParametro(Pessoa pessoa){
		pessoa.setRazao(Utils.toLike(pessoa.getRazao()));
		int countPessoa = this.pessoaMapper.countPessoa(pessoa);
		if (countPessoa == 0) 
			return new ArrayList<Pessoa>();
		
		if(countPessoa > ConfigEnum.LIMITE_COUNT.getValorInt()){
			List<Pessoa> lstPessoa = new ArrayList<Pessoa>();
			pessoa.setMsgRetorno(Mensagem.REFINE_SUA_PESQUISA.getMensagem());
			pessoa.setCodRetorno(Mensagem.REFINE_SUA_PESQUISA.getCodigo());
			lstPessoa.add(pessoa);
			return lstPessoa;
		}
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
