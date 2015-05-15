package com.plataforma.myp7.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.config.TesteConexao;
import com.plataforma.myp7.data.Usuario;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public String test(HttpServletResponse response) throws IOException{
		
		TesteConexao t = new TesteConexao();
    	
   	 List<Usuario> list = t.obterTodos();
   	 System.out.println(list);
		return "teste";
	}
}
