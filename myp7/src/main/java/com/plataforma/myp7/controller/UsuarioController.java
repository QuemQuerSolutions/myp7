package com.plataforma.myp7.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.Util.Utils;
import com.plataforma.myp7.bo.UsuarioBO;
import com.plataforma.myp7.data.Usuario;

@Controller
public class UsuarioController {
	
	private UsuarioBO usuarioBO;
	
	public UsuarioController(){
		this.usuarioBO = new UsuarioBO();
	}
	
	
	@RequestMapping("cadastroUsuario")
	public String inserir(Usuario usuario, Model model){
		String pagina = "";
		try {
			pagina = usuarioBO.inserir(usuario, model);
		} catch (SQLException e) {
			Utils.setMsgRetorno(model, "Falha na operação.");
			model.addAttribute("codMsgem", -1);
		} catch (NoSuchAlgorithmException e) {
			Utils.setMsgRetorno(model, "Falha na operação.");
			model.addAttribute("codMsgem", -1);
		} catch (UnsupportedEncodingException e) {
			Utils.setMsgRetorno(model, "Falha na operação.");
			model.addAttribute("codMsgem", -1);
		}
		return pagina;
	}
}
