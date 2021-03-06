package com.plataforma.myp7.controller;

import static com.plataforma.myp7.util.Utils.*;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plataforma.myp7.bo.EmbalagemBO;
import com.plataforma.myp7.bo.NcmBO;
import com.plataforma.myp7.bo.ProdutoBO;
import com.plataforma.myp7.data.NCM;
import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.util.Utils;

@Controller
@RequestMapping(value={"/portal", "/admin", "/retaguarda"})
public class ProdutoController {
	
	@Autowired
	private ProdutoBO produtoBO;
	
	@Autowired
	private EmbalagemBO embalagemBO;
	
	@Autowired
	private NcmBO ncmBO;
	
	private String sucessoInsert;
	private String imagemAnterior;
	
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
	public String editar(HttpServletRequest request, Model model, Long codProduto, boolean allDisabled, String actionCancelar, String filtroAnterior){
		
		if(allDisabled){
			model.addAttribute("allDisabled", allDisabled);
			model.addAttribute("actionCancelar", String.format("%s?filtroAnterior=%s", actionCancelar, filtroAnterior));
		}
		
		Produto produto = produtoBO.obterPorId(codProduto);
		this.imagemAnterior = produto.getCaminhoImagem();
		this.carregaSelectEmbalagem(model);
		this.ajustarCaminhoImagem(produto, request);
		model.addAttribute("produto", produto);
		return "ProdutoInserir";
	}
	
	private void ajustarCaminhoImagem(Produto produto, HttpServletRequest request){
		String url = request.getRequestURL().toString();
		if(Utils.isEmpty(produto.getCaminhoImagem())){
			produto.setCaminhoImagem(url.substring(0, url.indexOf("myp7") + 5).concat("resources/img/default.png"));
		}else{
			produto.setCaminhoImagem(url.substring(0, url.indexOf("myp7") + 5) + "resources/upload/" + produto.getCaminhoImagem());
		}
	}
	
	@RequestMapping("NovoProduto")
	public String novo(Produto produto, HttpSession session, Model model){
		this.carregaSelectEmbalagem(model);
		produto.setCaminhoImagem(ConfigEnum.IMAGEM_DEFAULT_PRODUTO.getValor());
		model.addAttribute("produto", produto);
		return "ProdutoInserir";
	}
	
	@RequestMapping("InserirProduto")
	public String salvar(Produto produto, HttpSession session, Model model, HttpServletRequest req){
		if(!this.produtoBO.salvar(produto, session, model, imagemAnterior)){
			this.carregaSelectEmbalagem(model);
			model.addAttribute("produto", produto);
			return "ProdutoInserir";
		}
		
		this.sucessoInsert = "Produto salvo com sucesso";
		return "redirect:Produto";
	}
	
	private void carregaSelectEmbalagem(Model model){
		model.addAttribute("embalagens", this.embalagemBO.selecionaTodos());
	}
	
	@RequestMapping("carregaProdutos")
	public String carregaProdutos(HttpSession session, Produto produto, Model model){
		produto.setUsuario((Usuario) session.getAttribute(ConfigEnum.USUARIO_LOGADO.getValor()));
		model.addAttribute("produtos", this.produtoBO.obterProdutos(produto, model));
		return "ProdutoLista";
	}
	
	@RequestMapping("validaNcm")
	public @ResponseBody String validaNcmAjax(String ncm, Model model) {
		Produto produto = new Produto();
		produto.setNcmProduto(this.ncmBO.selecionaPorCodigo(new NCM(ncm)));
		
		if(this.produtoBO.isInsertValido(produto, model)){
			return "true";
		}
        return "false";
    }
}
