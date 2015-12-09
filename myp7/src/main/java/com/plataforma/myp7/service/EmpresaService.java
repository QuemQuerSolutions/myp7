package com.plataforma.myp7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.plataforma.myp7.bo.EmpresaBO;
import com.plataforma.myp7.bo.PessoaBO;
import com.plataforma.myp7.data.Empresa;
import com.plataforma.myp7.data.Pessoa;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.exception.ManterEntidadeException;

@Controller
@RequestMapping("/wsempresa")
public class EmpresaService {

	@Autowired
	private PessoaBO pessoaBO;
	
	@Autowired
	private EmpresaBO empresaeBO;
	
	private Gson gson;
	
	public EmpresaService(){
		this.gson = new Gson();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/manterEmpresa", produces="application/json")
	@ResponseBody
	public String manterEmpresa(@RequestParam(value="idEmpresa", required=false) Long idEmpresa,
								   @RequestParam(value="nomeReduzido", required=true) String nomeReduzido,
								   @RequestParam(value="idPessoa", required=true) Integer idPessoa){
		
		MensagemWS mensagem;
		Empresa empresa = new Empresa();
		
		try {
			if(idEmpresa == null){
				empresa.setIdEmpresa(idEmpresa);
				this.populaEmporesa(empresa, nomeReduzido, idPessoa);
				
				empresaeBO.insert(empresa);
				mensagem = MensagemWS.INSERT_EMPRESA_SUCESSO;
			}else{
				empresa = empresaeBO.selecionaPorId(idEmpresa);
				
				if(empresa != null){
					this.populaEmporesa(empresa, nomeReduzido, idPessoa);
					
					empresaeBO.update(empresa);
					mensagem = MensagemWS.ATUALIZA_EMPRESA_SUCESSO;
				}else{
					throw new ManterEntidadeException(MensagemWS.ATUALIZA_EMPRESA_ERRO);
				}
			}
			
			return gson.toJson(MensagemWS.getMensagem(mensagem));
		} catch (ManterEntidadeException e) {
			return gson.toJson(MensagemWS.getMensagem(e.getMensagemEnum()));
		}
	}
	
	private void populaEmporesa(Empresa empresa, String nomeReduzido, Integer idPessoa){
		empresa.setIdPessoa(new Pessoa(idPessoa));
		empresa.setNomeReduzido(nomeReduzido);
	}
}
