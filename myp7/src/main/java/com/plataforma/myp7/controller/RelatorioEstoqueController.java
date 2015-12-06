package com.plataforma.myp7.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;
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
import com.plataforma.myp7.data.RelatorioEstoque;
import com.plataforma.myp7.data.Representante;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.interfaces.ComboPessoa;
import com.plataforma.myp7.util.Utils;

@Controller
public class RelatorioEstoqueController {
	
	private Usuario usuario;
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	@Autowired
	private RepresentanteBO representanteBO;
	
	@Autowired
	private CompradorBO compradorBO;
	
	@Autowired
	private RelatorioEstoqueBO relatorioEstoqueBO;
	
	@RequestMapping("RelatorioEstoque")
	public String relatorioEstoque(Model model, HttpSession session){
		this.usuario = usuarioBO.getUserSession(session);
		model.addAttribute("pessoas", this.carregaListaPessoa(usuario, model));
		
		return "RelatorioEstoque";
	} 
	
	private List<? extends ComboPessoa> carregaListaPessoa(Usuario usuario, Model model){
		Representante repr = representanteBO.selecionaPorIdUsuario(usuario.getIdUsuario());
		if(Objects.isNull(repr)){
			model.addAttribute("tituloPessoa", "Representantes");
			Comprador comp = compradorBO.obterPorIdUsuario(usuario.getIdUsuario());	
			
			if(Objects.isNull(comp))
				return null;
			
			return representanteBO.obterPorComprador(comp);
		}
		model.addAttribute("tituloPessoa", "Compradores");
		return compradorBO.obterPorIdRepresentante(repr);
	}
	
	
	@RequestMapping("rpdEstoque")
	public void visualizaRelEstoque(HttpServletResponse res, RelatorioEstoque relatorioEstoque) {
		relatorioEstoque.setIdUsuario(usuario.getIdUsuario());
		relatorioEstoque.setProduto("".equals(relatorioEstoque.getProduto()) ? null:relatorioEstoque.getProduto());
		relatorioEstoqueBO.gerarPDF(res, relatorioEstoque);
	}
	
	@RequestMapping(value="pesquisaRelatorioEstoque", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<RelatorioEstoque> pesquisaRelatorioEstoqueAJAX(String idProduto, String descProduto, String idPessoa) {
		try {
			RelatorioEstoque relEstoque = new RelatorioEstoque();
			
			relEstoque.setIdProduto("".equals(idProduto)? null: Long.parseLong(idProduto));
			relEstoque.setProduto("".equals(descProduto)? null: Utils.toLike(descProduto));
			relEstoque.setIdUsuario(this.usuario.getIdUsuario());
			relEstoque.setIdRepresentante("-1".equals(idPessoa)?null: Long.parseLong(idPessoa));
			return this.relatorioEstoqueBO.obterPorParametros(relEstoque);
		} catch (Exception e) {
			return new ArrayList<RelatorioEstoque>();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
