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
import com.plataforma.myp7.enums.GeralEnum;
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
			this.sucessoInsert = null;
		}
		return "ProdutoLista";
	}
	
	@RequestMapping("EditarProduto")
	public String editar(Model model, Long codProduto){
		Produto produto = produtoBO.obterPorId(codProduto);
		this.carregaSelectEmbalagem(model);
		model.addAttribute("produto", produto);
		return "ProdutoInserir";
	}
	
	@RequestMapping("NovoProduto")
	public String novo(Produto produto, HttpSession session, Model model){
		this.carregaSelectEmbalagem(model);
		return "ProdutoInserir";
	}
	
	@RequestMapping("InserirProduto")
	public String salvar(Produto produto, HttpSession session, Model model){
		try{
			produto.setUsuario((Usuario) session.getAttribute(GeralEnum.USUARIO_LOGADO.getValor()));
			this.produtoBO.salvar(produto, model);
			model.addAttribute("produto", produto);
			this.carregaSelectEmbalagem(model);
			this.sucessoInsert = "Produto salvo com sucesso";
			
			return "ProdutoInserir";
		}catch(Exception e){
			Utils.setMsgRetorno(model, "Falha na operação.");
			Utils.setCodRetorno(model, -1);
			e.printStackTrace();
		}
		return "redirect:Produto";
	}
	
	private void carregaSelectEmbalagem(Model model){
		model.addAttribute("embalagens", this.embalagemBO.selecionaTodos());
	}
	
	@RequestMapping("carregaProdutos")
	public String carregaProdutos(HttpSession session, Produto produto, Model model){
		try {
			produto.setUsuario((Usuario) session.getAttribute(GeralEnum.USUARIO_LOGADO.getValor()));
			model.addAttribute("produtos", this.produtoBO.obterProdutos(produto, model));
		} catch (Exception e) {
			Utils.setMsgRetorno(model, "Falha na operação.");
			Utils.setCodRetorno(model, -1);
			e.printStackTrace();
		}
		return "ProdutoLista";
	}
	
}
