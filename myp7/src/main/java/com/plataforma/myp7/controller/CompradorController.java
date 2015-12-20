package com.plataforma.myp7.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.CompradorBO;
import com.plataforma.myp7.data.Comprador;
import com.plataforma.myp7.enums.Mensagem;

import static com.plataforma.myp7.util.Utils.*;

@Controller
@RequestMapping(value={"/retaguarda", "/admin"})
public class CompradorController {

	@Autowired
	private CompradorBO compradorBO; 
	
	@RequestMapping("Comprador")
	public String inicio(Model model){
		return "CompradorLista";
	}
	
	@RequestMapping("CarregaListaComprador")
	public String carregaListaComprador(Model model, Comprador comprador, String origem){
		if("save".equals(origem)){
			setRetorno(model, Mensagem.SALVO_SUCESSO);
			return "CompradorLista";
		}
		model.addAttribute(comprador);
		model.addAttribute("lstComprador", compradorBO.obterPorParametro(model, comprador));
		return "CompradorLista";
	}
	
	@RequestMapping("editarComprador")
	public String editarComprador(Model model, Integer id){
		
		if(Objects.isNull(id)){
			model.addAttribute("obj", new Comprador());
			model.addAttribute("qtdEmpresa", 0);
			model.addAttribute("qtdRepresentante", 0);
			return "CompradorSalvar";
		}
		
		Comprador comprador = compradorBO.obterPorId(id);
		int qtdEmpresa = comprador.getEmpresa().size(),
			qtdRepresentante = comprador.getRepresentantes().size();
		
		comprador.setIdPessoa(Long.valueOf(comprador.getId()));
		model.addAttribute("obj", comprador); 
		model.addAttribute("qtdEmpresa", qtdEmpresa);
		model.addAttribute("qtdRepresentante", qtdRepresentante);
		
		return "CompradorSalvar";
	}
	
	@RequestMapping("salvarComprador")
	public String salvarComprador(Comprador comprador){
		compradorBO.salvar(comprador);
		return "redirect:CarregaListaComprador?origem=save";
	}
}
