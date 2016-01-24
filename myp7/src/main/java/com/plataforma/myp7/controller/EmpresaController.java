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
@RequestMapping(value={"/retaguarda", "/admin", "/portal"})
public class EmpresaController {
	
	@Autowired
	private EmpresaBO empresaBO;
	
	@RequestMapping(value="obterEmpresaPorParametro", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Empresa> obterEmpresaPorParametro(Empresa empresa) {
		return empresaBO.obterPorParametro(empresa);
	}
	
	private List<Empresa> obtemComboEmpresa(String uf){
		return this.empresaBO.selecionaPorUF(uf);
	}
	
	@RequestMapping(value="consultaEmpresaPorUF")
	public @ResponseBody String consultaEmpresaPorUFAJAX(String uf) {
		return this.getComboEmpresa(obtemComboEmpresa(uf));
	}
	
	private String getComboEmpresa(List<Empresa> lista){
		StringBuilder sb = new StringBuilder();
		
		sb.append("<option value='-1'>Selecione uma Empresa</option>");
		for(Empresa emp : lista){
			sb.append("<option value='");
			sb.append(emp.getIdEmpresa());
			sb.append("'>");
			sb.append(emp.getNomeReduzido());
			sb.append("</option>");
		}
		sb.append("<option value='999'>Todos</option>");
		
		return sb.toString();
	}

}
