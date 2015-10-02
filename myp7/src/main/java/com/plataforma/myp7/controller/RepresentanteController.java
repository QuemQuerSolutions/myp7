package com.plataforma.myp7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plataforma.myp7.bo.RepresentanteBO;
import com.plataforma.myp7.data.Representante;
import com.plataforma.myp7.util.Utils;

@Controller
public class RepresentanteController {
	
	@Autowired
	private RepresentanteBO representanteBO;
	
	@RequestMapping("Representante")
	public String inicio(Model model){
		return "RepresentanteLista";
	}
	
	@RequestMapping("carregaListaRepresentante")
	public String carregaListaRepresentante(Representante representante, String apelido, Model model){
		try{
			model.addAttribute("idRepresentante", representante != null ? representante.getIdRepresentante() : null );
			model.addAttribute("apelido", representante != null && !Utils.isEmpty(representante.getApelido()) ? representante.getApelido() : null );
			
			representante.setRazao(!Utils.isEmpty(representante.getRazao()) ? Utils.toLike(representante.getRazao()) : null );
			model.addAttribute("razao", representante.getRazao() );
			
			model.addAttribute("lstRepresentante", this.representanteBO.obterPorParametro(representante));
		}catch(Exception e){
			Utils.setMsgRetorno(model, "Falha na Operação");
			Utils.setCodRetorno(model, -1);
		}
		return "RepresentanteLista";
	}	
	
	@RequestMapping(value="obterRepresentantePorParametro", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Representante> obterRepresentantePorParametro(Representante representante) {
		return representanteBO.obterPorParametro(representante);
	}
	
}
