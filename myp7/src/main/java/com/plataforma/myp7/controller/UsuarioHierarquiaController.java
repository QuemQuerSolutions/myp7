package com.plataforma.myp7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plataforma.myp7.bo.UsuarioBO;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.dto.UsuarioDTO;
import com.plataforma.myp7.enums.Mensagem;
import com.plataforma.myp7.util.Utils;

@Controller
@RequestMapping(value={"/admin", "/portal"})
public class UsuarioHierarquiaController {
	
	@Autowired
	private UsuarioBO usuarioBO;

	
	@RequestMapping(value="UsuarioHierarquia")
	public String direcionarTelaUsuarioHierarquia(Model model, String origem) {
		if("save".equals(origem))
			Utils.setRetorno(model, Mensagem.SALVO_SUCESSO);
		
		if("error".equals(origem))
			Utils.setRetorno(model, Mensagem.FALHA_NA_OPERACAO);
		
		return "UsuarioHierarquia";
	}
	
	@RequestMapping("salvarHierarquia")
	public String salvarFornecedor(UsuarioDTO usuario, Model model){
		try{
			this.usuarioBO.salvarHierarquia(usuario);
		}catch(Exception e){
			return "redirect:UsuarioHierarquia?origem=error";
		}
		return "redirect:UsuarioHierarquia?origem=save";
	}
	
	@RequestMapping(value="verificaSubordinadosAJAX", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean verificaSubordinadosAJAX(String idEAlcadaUsuarios) {
		Boolean retorno = Boolean.TRUE;
		
		if(idEAlcadaUsuarios != null){
			String[] dadosUsuarios = idEAlcadaUsuarios.split(";");
			for(String dadosUsuario : dadosUsuarios){
				if(dadosUsuario != null && !dadosUsuario.trim().equals("")){
					String[] dados = dadosUsuario.split(",");
					if(dados != null && dados.length > 0){
						Usuario usuario = new Usuario();
						
						usuario.setIdUsuario(Long.parseLong(dados[0]));
						usuario.setFlagAprovProduto(Integer.parseInt(dados[2]));
						
						if(dados[3] != null && dados[3].equalsIgnoreCase("1"))
							usuario.setAlcada(Long.parseLong(dados[1]));
						else
							usuario.setAlcada(0L);
						
						retorno = retorno && this.usuarioBO.validarSubordinados(usuario);
					}
				}
			}
		}
		
		return retorno;
	}	
}
