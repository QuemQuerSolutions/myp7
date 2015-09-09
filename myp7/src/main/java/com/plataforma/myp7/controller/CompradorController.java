package com.plataforma.myp7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.CompradorBO;
import com.plataforma.myp7.data.Comprador;

@Controller
public class CompradorController {

	@Autowired
	private CompradorBO compradorBO; 
	
	@RequestMapping("Comprador")
	public String inicio(Model model){
		return "CompradorLista";
	}
	
	@RequestMapping("CarregaListaComprador")
	public String carregaListaComprador(Model model, Comprador comprador){
		model.addAttribute("comprador", comprador);
		model.addAttribute("lstComprador", compradorBO.obterPorParametro(model, comprador));
		return "CompradorLista";
	}
}
