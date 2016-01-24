package com.plataforma.myp7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.UsuarioBO;
import com.plataforma.myp7.dto.UsuarioDTO;

@Controller
@RequestMapping(value={"/admin", "/portal"})
public class UsuarioHierarquiaController {
	
	@Autowired
	private UsuarioBO usuarioBO;

	
	@RequestMapping(value="UsuarioHierarquia")
	public String direcionarTelaUsuarioHierarquia() {
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
}
