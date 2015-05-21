package com.plataforma.myp7.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.SenhaBO;
import com.plataforma.myp7.data.ParametroDominio;
import com.plataforma.myp7.data.Usuario;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public String test(HttpServletResponse response) throws IOException{
		
		SenhaBO sb = new SenhaBO();
		ParametroDominio dominio = new ParametroDominio();
		Usuario usuario = new Usuario();	
		
		dominio.setId(1);
		usuario.setIdUsuario(1L);
		if(sb.isValido("a1B1@", usuario, dominio)){
			System.out.println("Passou");
		}else{
			System.out.println("Não Passou");
		}
		
		return "login";
	}
}
