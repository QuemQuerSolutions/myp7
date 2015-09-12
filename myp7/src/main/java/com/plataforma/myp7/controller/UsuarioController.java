package com.plataforma.myp7.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
