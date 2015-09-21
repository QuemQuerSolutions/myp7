package com.plataforma.myp7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plataforma.myp7.bo.EmpresaBO;
import com.plataforma.myp7.data.Empresa;

@Controller
public class EmpresaController {
	
	@Autowired
	private EmpresaBO empresaBO;
	
	@RequestMapping(value="obterEmpresaPorParametro", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Empresa> obterEmpresaPorParametro(Empresa empresa) {
		return empresaBO.obterPorParametro(empresa);
	}
	
}
