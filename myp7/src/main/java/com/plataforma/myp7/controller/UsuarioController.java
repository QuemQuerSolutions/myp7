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
		try {
			this.usuarioBO.inserir(usuario, model);
		} catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
			Utils.setMsgRetorno(model, "Falha na operação.");
			Utils.setCodRetorno(model, -1);
		}
		return "components/login";
	}
}
