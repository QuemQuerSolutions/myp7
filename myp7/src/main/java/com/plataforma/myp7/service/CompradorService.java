package com.plataforma.myp7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.plataforma.myp7.bo.CompradorBO;
import com.plataforma.myp7.data.Comprador;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.exception.ManterEntidadeException;

@Controller
@RequestMapping("/wscomprador")
public class CompradorService {

	@Autowired
	private CompradorBO compradorBO;
	
	private Gson gson;
	
	public CompradorService(){
		this.gson = new Gson();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/manterFornecedor", produces="application/json")
	@ResponseBody
	public String manterFornecedor(@RequestParam(value="idComprador", required=false) Integer idComprador,
								   @RequestParam(value="ediCodigo", required=true) Integer ediCodigo,
								   @RequestParam(value="idUsuario", required=true) Long idUsuario,
								   @RequestParam(value="status", required=true) String status,
								   @RequestParam(value="apelido", required=true) String apelido){
		MensagemWS mensagem;
		
		Comprador comprador = new Comprador();
		comprador.setEdiCodigo(ediCodigo);
		comprador.setUsuario(new Usuario(idUsuario));
		comprador.setStatus(status);
		comprador.setApelido(apelido);
		try {
			if(idComprador != null){
				comprador.setId(idComprador);
				this.compradorBO.update(comprador);
				mensagem = MensagemWS.ATUALIZA_COMPRADOR_SUCESSO;
			}else{
				this.compradorBO.inserir(comprador);
				mensagem = MensagemWS.INSERT_COMPRADOR_SUCESSO;
			}
			return gson.toJson(MensagemWS.getMensagem(mensagem));
		} catch (ManterEntidadeException e) {
			return gson.toJson(MensagemWS.getMensagem(e.getMensagemEnum()));
		}
	}
}
