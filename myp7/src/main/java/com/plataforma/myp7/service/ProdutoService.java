package com.plataforma.myp7.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.plataforma.myp7.bo.ProdutoBO;
import com.plataforma.myp7.data.Produto;

@Controller
@RequestMapping("/wsproduto")
public class ProdutoService {
	private Gson gson;
	
	public ProdutoService() {
		this.gson = new Gson();
	}
	
	@Autowired
	private ProdutoBO produtoBO;
	
	@RequestMapping(method=RequestMethod.GET, value="/consultaProduto",produces="application/json")
	public String consultaProduto(@RequestParam(value="id", required=false, defaultValue="0")Long id
								 ,@RequestParam(value="descricao", required=false, defaultValue="") String desc){
		try {
			Produto produto = new Produto();
			produto.setIdProduto(id);
			produto.setDesProduto(desc);
			
			return this.gson.toJson(produtoBO.consultaProdutoService(produto));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String inserirProduto(@RequestParam(value="idUsuario", required=true)Long idUsuario,
								 @RequestParam(value="descricaoProd", required=true) String descProd,
								 @RequestParam(value="codIndProd", required=true) String codIndProd,
								 @RequestParam(value="pesoBruto", required=true) BigDecimal pesoBruto,
								 @RequestParam(value="pesoLiquido", required=true) BigDecimal pesoLiquido,
								 @RequestParam(value="alturaProd", required=true) BigDecimal alturaProd, 
								 @RequestParam(value="profundidadeProd", required=true) BigDecimal profundidade,
								 @RequestParam(value="idNcmProd", required=true) Long idNcmProd,
								 @RequestParam(value="idEmbalagemProd", required=true) Long idEmbalagem,
								 @RequestParam(value="qtdEmbalagemProd", required=true) Integer qtdEmbalagem,
								 @RequestParam(value="eandun", required=true)String eandun){
		Produto produto = new Produto();
		String mensagemRetorno = "";
		try{
			produto.getUsuario().setIdUsuario(idUsuario);
			produto.setDesProduto(descProd);
			produto.setCodIndustria(codIndProd);
			produto.setPesoBruto(pesoBruto);
			produto.setPesoLiquido(pesoLiquido);
			produto.setAlturaProduto(alturaProd);
			produto.setProfunProduto(profundidade);
			produto.getNcmProduto().setIdNcm(idNcmProd);
			produto.getEmbalagem().setIdEmbalagem(idEmbalagem);
			produto.setQtdEmbalagem(qtdEmbalagem);
			produto.setEanDunProduto(eandun);
			this.produtoBO.inserirProdutoService(produto);
			mensagemRetorno = "Produto salvo com sucesso.";
		} catch (Exception e) {
			mensagemRetorno = "Erro na inser��o do produto.";
		}
		return this.gson.toJson(mensagemRetorno);
	}
	
	
}
