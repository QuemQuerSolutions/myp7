package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.toLike;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.Pessoa;
import com.plataforma.myp7.dto.MensagemRetornoDTO;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.mapper.PessoaMapper;

@Service
public class PessoaBO {
	
	private final static Logger log = Logger.getLogger(PessoaBO.class);
			
	@Autowired
	private PessoaMapper pessoaMapper;
	
	public List<String> selecionaTodasUF() {
		try {
			return this.pessoaMapper.obterTodasUF();
		} catch (Exception e) {
			log.error("PessoaBO.selecionaTodasUF", e);
			return null;
		}
	}
	
	public List<Pessoa> obterPessoaPorParametro(Pessoa pessoa){
		try {
			pessoa.setRazao(toLike(pessoa.getRazao()));
			return this.pessoaMapper.obterPessoaCodNome(pessoa);
		} catch (Exception e) {
			log.error("PessoaBO.obterPessoaPorParametro", e);
			return null;
		}
	}
	
	public List<Pessoa> obterPessoaCodNome(Pessoa pessoa){
		try {
			return this.pessoaMapper.obterPessoaCodNome(pessoa);
		} catch (Exception e) {
			log.error("PessoaBO.obterPessoaCodNome", e);
			return null;
		}
	}
	
	public MensagemRetornoDTO salvarPessoa(Pessoa pessoa) throws Exception{
		try {
			if(pessoa.getIdPessoa()==0L){
				this.pessoaMapper.inserir(pessoa);
				return MensagemWS.getMensagem(MensagemWS.INSERT_PESSOA_SUCESSO);
			}else{
				this.pessoaMapper.atualiza(pessoa);
				return MensagemWS.getMensagem(MensagemWS.ATUALIZA_PESSOA_SUCESSO);
			}
		} catch (Exception e) {
			log.error("PessoaBO.salvarPessoa", e);
			return null;
		}
	}
		
}
