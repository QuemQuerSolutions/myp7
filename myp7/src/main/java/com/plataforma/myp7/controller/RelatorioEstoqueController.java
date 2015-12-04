package com.plataforma.myp7.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plataforma.myp7.bo.CompradorBO;
import com.plataforma.myp7.bo.RelatorioEstoqueBO;
import com.plataforma.myp7.bo.RepresentanteBO;
import com.plataforma.myp7.bo.UsuarioBO;
import com.plataforma.myp7.data.Comprador;
import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.data.Representante;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.interfaces.ComboPessoa;
import com.plataforma.myp7.util.Utils;

@Controller
public class RelatorioEstoqueController {
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	@Autowired
	private RepresentanteBO representanteBO;
	
	@Autowired
	private CompradorBO compradorBO;
	
	@Autowired
	private RelatorioEstoqueBO relatorioEstoqueBO;
	
	@RequestMapping("RelatorioEstoque")
	public String produtoAprovacao(Model model, HttpSession session){
		
		model.addAttribute("pessoas", this.carregaListaPessoa(usuarioBO.getUserSession(session), model));
		
		return "RelatorioEstoque";
	} 
	
	private List<? extends ComboPessoa> carregaListaPessoa(Usuario usuario, Model model){
		Representante repr = representanteBO.selecionaPorIdUsuario(usuario.getIdUsuario());
		if(null == repr){
			model.addAttribute("tituloPessoa", "Representantes");
			Comprador comp = compradorBO.obterPorIdUsuario(usuario.getIdUsuario());
			
			if(null == comp)
				return null;
			
			return representanteBO.obterPorComprador(comp);
		}
		model.addAttribute("tituloPessoa", "Compradores");
		return compradorBO.obterPorIdRepresentante(repr);
	}
	
	@RequestMapping(value="pesquisaRelatorioEstoque", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String pesquisaRelatorioEstoqueAJAX(String idProduto, String descProduto, String idPessoa) {
		try {
			Produto produto = null;
			ComboPessoa pessoa = null;
			
			if(!Utils.isEmpty(idProduto) || !Utils.isEmpty(descProduto)){
				produto = new Produto();
				produto.setIdProduto(Long.parseLong(idProduto));
				produto.setDesProduto(descProduto);
			}

			if(!Utils.isEmpty(idPessoa) || !idPessoa.equalsIgnoreCase("-1")){
				Comprador comprador = new Comprador();
				comprador.setId(Integer.parseInt(idPessoa));
				pessoa = comprador;
			}
			
			//TODO
			return this.relatorioEstoqueBO.obterPorParametros(produto, pessoa).toString();
		} catch (Exception e) {
			return "false";
		}
	}
}
