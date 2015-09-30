package com.plataforma.myp7.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
import com.plataforma.myp7.util.Utils;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	@RequestMapping("Usuario")
	public String inicio(Model model){
		return "UsuarioInserir";
	}
	
	@RequestMapping("cadastroUsuario")
	public String inserir(Usuario usuario, Model model, String tpUsuario){
		try {
			this.usuarioBO.inserir(usuario, model, tpUsuario);
		} catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
			Utils.setMsgRetorno(model, "Falha na operação.");
			Utils.setCodRetorno(model, -1);
		}
		return "UsuarioInserir";
	}
	
	@RequestMapping(value="pesquisarUsuarioAJAX", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Usuario> pesquisarUsuarioAJAX(Usuario usuario) {
		return this.usuarioBO.selecionaComFiltro(usuario);
	}
	
	private String geraTabelaResultado(List<Usuario> lista){
		StringBuilder sb = new StringBuilder();
		
		for(Usuario usu : lista){
			sb.append("<tr>");
			sb.append("		<td class=\"idUsu\">");
			sb.append(			usu.getIdUsuario());
			sb.append("		</td>");
			sb.append("		<td class=\"razaoUsu\">");
			sb.append(			usu.getRazaoSocial());
			sb.append("		</td>");
			sb.append("		<td>");
			sb.append(			usu.getEmail());
			sb.append("		</td>");			
			sb.append("</tr>");
		}
		
		return sb.toString();
	}	
}
