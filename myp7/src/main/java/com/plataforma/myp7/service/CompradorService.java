package com.plataforma.myp7.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.plataforma.myp7.bo.CompradorBO;
import com.plataforma.myp7.bo.PessoaBO;
import com.plataforma.myp7.data.Comprador;
import com.plataforma.myp7.data.Pessoa;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.exception.ManterEntidadeException;

@Controller
@RequestMapping("/wscomprador")
public class CompradorService {

	@Autowired
	private CompradorBO compradorBO;
	
	@Autowired
	private PessoaBO pessoaBO;
	
	private Gson gson;
	
	public CompradorService(){
		this.gson = new Gson();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/manterComprador", produces="application/json")
	@ResponseBody
	public String manterComprador(@RequestParam(value="idComprador", required=true) Integer idComprador,
								   @RequestParam(value="ediCodigo", required=true) Integer ediCodigo,
								   @RequestParam(value="idUsuario", required=true) Long idUsuario,
								   @RequestParam(value="status", required=true) String status,
								   @RequestParam(value="apelido", required=true) String apelido){
		MensagemWS mensagem;
		
		try {
			if(idComprador != null){	
				Pessoa pessoa = new Pessoa();
				pessoa.setIdPessoa(Long.parseLong(idComprador.toString()));
				List<Pessoa> pessoas = this.pessoaBO.obterPessoaCodNome(pessoa);
				if(pessoas != null && pessoas.size() > 0){
					Comprador comprador = this.compradorBO.obterPorId(idComprador);
					
					if(comprador == null){
						comprador = new Comprador();
						comprador.setId(idComprador);
						this.populaComprador(comprador, idComprador, ediCodigo, idUsuario, status, apelido);
						
						this.compradorBO.inserir(comprador);
						mensagem = MensagemWS.INSERT_COMPRADOR_SUCESSO;
					}else{
						this.populaComprador(comprador, idComprador, ediCodigo, idUsuario, status, apelido);
						this.compradorBO.update(comprador);
						mensagem = MensagemWS.ATUALIZA_COMPRADOR_SUCESSO;
					}
				}else{
					throw new ManterEntidadeException(MensagemWS.INSERT_COMPRADOR_ERRO);
				}
			}else{
				throw new ManterEntidadeException(MensagemWS.INSERT_COMPRADOR_ERRO);
			}
			return gson.toJson(MensagemWS.getMensagem(mensagem));
		} catch (ManterEntidadeException e) {
			return gson.toJson(MensagemWS.getMensagem(e.getMensagemEnum()));
		}
	}
	
	private void populaComprador(Comprador comprador, Integer idComprador, Integer ediCodigo, Long idUsuario, String status, String apelido){
		
		comprador.setEdiCodigo(ediCodigo);
		comprador.setUsuario(new Usuario(idUsuario));
		comprador.setStatus(status);
		comprador.setApelido(apelido);
	}
}
