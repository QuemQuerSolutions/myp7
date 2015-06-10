package com.plataforma.myp7.controller;

import static com.plataforma.myp7.util.Utils.setCodRetorno;
import static com.plataforma.myp7.util.Utils.setMsgRetorno;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.EmbalagemBO;
import com.plataforma.myp7.bo.ProdutoBO;
import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.util.Utils;

@Controller
public class ProdutoController {
	private ProdutoBO produtoBO;
	private EmbalagemBO embalagemBO;
	private String sucessoInsert;
	
	public ProdutoController() {
		this.produtoBO = new ProdutoBO();
		this.embalagemBO = new EmbalagemBO();
	}
	
	@RequestMapping("Produto")
	public String inicio(HttpSession session, Model model){
		if(!Objects.isNull(this.sucessoInsert)){
			setMsgRetorno(model, this.sucessoInsert);
			setCodRetorno(model, 0);
		}
		return "ProdutoLista";
	}
	
	@RequestMapping("NovoProduto")
	public String novo(Produto produto, HttpSession session, Model model){
		System.out.println("Estaá entrando aqui?"+ produto.getIdProduto());
		if(!Objects.isNull(produto.getIdProduto()) && produto.getIdProduto() != 0L){
			produto = produtoBO.obterPorId(produto.getIdProduto());
			this.carregaEmbalagemPorId(model, produto.getEmbalagem().getIdEmbalagem());
			model.addAttribute("produto", produto);
		}else{
			this.carregaSelectEmbalagem(model);
		}
		return "ProdutoInserir";
	}
	
	@RequestMapping("InserirProduto")
	public String salvar(Produto produto, HttpSession session, Model model){
		produto.setUsuario((Usuario) session.getAttribute("usuarioLogado"));
		
		if(!produtoBO.salvar(produto, model)){
			model.addAttribute("produto", produto);
			this.carregaSelectEmbalagem(model);
			return "ProdutoInserir";
		}
		this.sucessoInsert = "Produto salvo com sucesso";
		return "redirect:Produto";
	}
	
	private void carregaSelectEmbalagem(Model model){
		model.addAttribute("embalagens", this.embalagemBO.selecionaTodos());
	}
	
	@RequestMapping("carregaProdutos")
	public String obterEmbalagens(HttpSession session, Produto produto,Model model){
		try {
			model.addAttribute("produtos", this.produtoBO.obterProdutos(produto, model));
		} catch (Exception e) {
			Utils.setMsgRetorno(model, "Falha na operação.");
			Utils.setCodRetorno(model, -1);
			e.printStackTrace();
		}
		return "ProdutoLista";
	}
	
	private void carregaEmbalagemPorId(Model model, Long id){
		model.addAttribute("embalagens", this.embalagemBO.obterEmbalagemPorId(id));
	}
	
	
}
