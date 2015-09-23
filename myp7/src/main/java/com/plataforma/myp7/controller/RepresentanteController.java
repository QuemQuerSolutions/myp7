package com.plataforma.myp7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plataforma.myp7.bo.RepresentanteBO;
import com.plataforma.myp7.data.Representante;

@Controller
public class RepresentanteController {
	
	@Autowired
	private RepresentanteBO representanteBO;
	
	@RequestMapping(value="obterRepresentantePorParametro", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Representante> obterRepresentantePorParametro(Representante representante) {
		return representanteBO.obterPorParametro(representante);
	}
	
}
