package com.plataforma.myp7.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.plataforma.myp7.bo.ProdutoBO;
import com.plataforma.myp7.data.Embalagem;
import com.plataforma.myp7.data.NCM;
import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.util.Utils;

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
	@ResponseBody
	public String consultaProduto(@RequestParam(value="id", required=false, defaultValue="0") Long id,
								  @RequestParam(value="descricao", required=false, defaultValue="") String desc){
		try {
			Produto produto = new Produto();
			produto.setIdProduto(id);
			produto.setDesProduto(Utils.emptyToNull(desc));
			List<Produto> lstProduto = this.produtoBO.consultaProdutoService(produto);
			return gson.toJson(lstProduto.size() == 0?Utils.formataMsgem(1):lstProduto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/inserirProduto",produces="application/json")
	@ResponseBody
	public String inserirProduto(@RequestParam(value="idUsuario", required=true)Long idUsuario,
								 @RequestParam(value="descricaoProd", required=true) String descProd,
								 @RequestParam(value="codIndProd", required=true) String codIndProd,
								 @RequestParam(value="pesoBruto", required=true) BigDecimal pesoBruto,
								 @RequestParam(value="pesoLiquido", required=true) BigDecimal pesoLiquido,
								 @RequestParam(value="alturaProd", required=true) BigDecimal alturaProd, 
								 @RequestParam(value="profundidadeProd", required=true) BigDecimal profundidade,
								 @RequestParam(value="larguraProduto", required=true) BigDecimal larguraProduto,
								 @RequestParam(value="idNcmProd", required=true) Long idNcmProd,
								 @RequestParam(value="idEmbalagemProd", required=true) Long idEmbalagem,
								 @RequestParam(value="qtdEmbalagemProd", required=true) Integer qtdEmbalagem,
								 @RequestParam(value="eandun", required=true)String eandun){
		Produto produto = new Produto();
		Usuario usuario = new Usuario();
		NCM ncm = new NCM();
		Embalagem embalagem = new Embalagem();
		
		usuario.setIdUsuario(idUsuario);
		produto.setUsuario(usuario);
		
		produto.setDesProduto(descProd);
		produto.setCodIndustria(codIndProd);
		produto.setPesoBruto(pesoBruto);
		produto.setPesoLiquido(pesoLiquido);
		produto.setAlturaProduto(alturaProd);
		produto.setProfunProduto(profundidade);
		produto.setLarguraProduto(larguraProduto);
		
		ncm.setIdNcm(idNcmProd);
		produto.setNcmProduto(ncm);
		
		embalagem.setIdEmbalagem(idEmbalagem);
		produto.setEmbalagem(embalagem);
		
		produto.setQtdEmbalagem(qtdEmbalagem);
		produto.setEanDunProduto(eandun);
		try{
			this.produtoBO.inserirProdutoService(produto);
			return gson.toJson(Utils.formataMsgem(3));
		} catch (Exception e) {
			e.printStackTrace();
			return gson.toJson(Utils.formataMsgem(2));
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/inserirProduto",produces="application/json")
	@ResponseBody
	public String editarFornecedor(@RequestParam(value="idUsuario", required=true)Long idUsuario,
									 @RequestParam(value="descricaoProd", required=true) String descProd,
									 @RequestParam(value="codIndProd", required=true) String codIndProd,
									 @RequestParam(value="pesoBruto", required=true) BigDecimal pesoBruto,
									 @RequestParam(value="pesoLiquido", required=true) BigDecimal pesoLiquido,
									 @RequestParam(value="alturaProd", required=true) BigDecimal alturaProd, 
									 @RequestParam(value="profundidadeProd", required=true) BigDecimal profundidade,
									 @RequestParam(value="larguraProduto", required=true) BigDecimal larguraProduto,
									 @RequestParam(value="idNcmProd", required=true) Long idNcmProd,
									 @RequestParam(value="idEmbalagemProd", required=true) Long idEmbalagem,
									 @RequestParam(value="qtdEmbalagemProd", required=true) Integer qtdEmbalagem,
									 @RequestParam(value="eandun", required=true)String eandun){
		Produto produto = new Produto();
		Usuario usuario = new Usuario();
		NCM ncm = new NCM();
		Embalagem embalagem = new Embalagem();
		
		usuario.setIdUsuario(idUsuario);
		produto.setUsuario(usuario);
		
		produto.setDesProduto(descProd);
		produto.setCodIndustria(codIndProd);
		produto.setPesoBruto(pesoBruto);
		produto.setPesoLiquido(pesoLiquido);
		produto.setAlturaProduto(alturaProd);
		produto.setProfunProduto(profundidade);
		produto.setLarguraProduto(larguraProduto);
		
		ncm.setIdNcm(idNcmProd);
		produto.setNcmProduto(ncm);
		
		embalagem.setIdEmbalagem(idEmbalagem);
		produto.setEmbalagem(embalagem);
		
		produto.setQtdEmbalagem(qtdEmbalagem);
		produto.setEanDunProduto(eandun);
		try{
			this.produtoBO.inserirProdutoService(produto);
			return gson.toJson(Utils.formataMsgem(3));
		} catch (Exception e) {
			e.printStackTrace();
			return gson.toJson(Utils.formataMsgem(2));
		}
	}
}
