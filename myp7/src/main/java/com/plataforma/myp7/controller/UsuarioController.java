package com.plataforma.myp7.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
			Utils.setMsgRetorno(model, "Falha na opera��o.");
			Utils.setCodRetorno(model, -1);
		}
		return "UsuarioInserir";
	}
	
	@RequestMapping("pesquisarUsuarioAJAX")
	public @ResponseBody String pesquisarUsuarioAJAX(String razaoSocial, String email) {
		return this.geraTabelaResultado(usuarioBO.selecionaComFiltro(razaoSocial, email));
	}
	
	private String geraTabelaResultado(List<Usuario> lista){
		StringBuilder sb = new StringBuilder();
		
		for(Usuario usu : lista){
			sb.append("<tr>");
			sb.append("		<td>");
			sb.append(			usu.getIdUsuario());
			sb.append("		</td>");
			sb.append("		<td>");
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
