package com.plataforma.myp7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.plataforma.myp7.bo.PessoaBO;
import com.plataforma.myp7.bo.RepresentanteBO;
import com.plataforma.myp7.data.Pessoa;
import com.plataforma.myp7.data.Representante;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.exception.ManterEntidadeException;

@Controller
@RequestMapping("/wsrepresentante")
public class RepresentanteService {

	@Autowired
	private PessoaBO pessoaBO;
	
	@Autowired
	private RepresentanteBO representanteBO;
	
	private Gson gson;
	
	public RepresentanteService(){
		this.gson = new Gson();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/manterRepresentante", produces="application/json")
	@ResponseBody
	public String manterRepresentante(@RequestParam(value="idRepresentante", required=true) Long idRepresentante,
								   @RequestParam(value="apelido", required=true) String apelido,
								   @RequestParam(value="status", required=true) String status,
								   @RequestParam(value="idUsuario", required=true) Long idUsuario){
		
		MensagemWS mensagem;
		Representante representante;
		
		try {
			if(idRepresentante != null){
				Pessoa pessoa = new Pessoa();
				pessoa.setIdPessoa(Long.parseLong(idRepresentante.toString()));
				List<Pessoa> pessoas = this.pessoaBO.obterPessoaCodNome(pessoa);
				if(pessoas != null && pessoas.size() > 0){
					representante = representanteBO.selecionaPorId(idRepresentante);
					if(representante == null){
						representante = new Representante(idRepresentante);
						
						this.populaRepresentante(representante, apelido, status, idUsuario);
						representanteBO.insert(representante);
						mensagem = MensagemWS.INSERT_REPRESENTANTE_SUCESSO;
					}else{
						representante = representanteBO.selecionaPorId(idRepresentante);
						
						this.populaRepresentante(representante, apelido, status, idUsuario);
						representanteBO.update(representante);
						mensagem = MensagemWS.ATUALIZA_REPRESENTANTE_SUCESSO;
					}
					return gson.toJson(MensagemWS.getMensagem(mensagem));
				}else{
					throw new ManterEntidadeException(MensagemWS.INSERT_REPRESENTANTE_ERRO);
				}
			}else{
				throw new ManterEntidadeException(MensagemWS.INSERT_REPRESENTANTE_ERRO);
			}
		} catch (ManterEntidadeException e) {
			return gson.toJson(MensagemWS.getMensagem(e.getMensagemEnum()));
		}
	}
	
	private void populaRepresentante(Representante representante, String apelido, String status, Long idUsuario){
		representante.setApelido(apelido);
		representante.setStatus(status);
		representante.setUsuario(new Usuario(idUsuario));
	}
}
