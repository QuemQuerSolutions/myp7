package com.plataforma.myp7.service;

import static com.plataforma.myp7.util.Utils.emptyToNull;

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
import com.plataforma.myp7.enums.MensagemWS;

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
			produto.setDesProduto(emptyToNull(desc));
			List<Produto> lstProduto = this.produtoBO.consultaProdutoService(produto);
			return gson.toJson(lstProduto.size() == 0?MensagemWS.getMensagem(MensagemWS.CONSUL_VAZIA):lstProduto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/salvarProduto",produces="application/json")
	@ResponseBody
	public String salvarProduto(@RequestParam(value="idProduto", required=false, defaultValue="0")Long idProduto,
								@RequestParam(value="idUsuario", required=true)Long idUsuario,
								@RequestParam(value="descricaoProd", required=true) String descProd,
								@RequestParam(value="codIndProd", required=true) String codIndProd,
								@RequestParam(value="pesoBruto", required=true) Double pesoBruto,
								@RequestParam(value="pesoLiquido", required=true) Double pesoLiquido,
								@RequestParam(value="alturaProd", required=true) Double alturaProd, 
								@RequestParam(value="profundidadeProd", required=true) Double profundidade,
								@RequestParam(value="larguraProduto", required=true) Double larguraProduto,
								@RequestParam(value="idNcmProd", required=true) Long idNcmProd,
								@RequestParam(value="idEmbalagemProd", required=true) Long idEmbalagem,
								@RequestParam(value="qtdEmbalagemProd", required=true) Integer qtdEmbalagem,
								@RequestParam(value="eandun", required=true)String eandun,
								@RequestParam(value="caminhoImagem", required=false)String caminhoImagem){
		
		Produto produto = new Produto();
		Usuario usuario = new Usuario();
		NCM ncm = new NCM();
		Embalagem embalagem = new Embalagem();
		
		usuario.setIdUsuario(idUsuario);
		produto.setUsuario(usuario);
		
		produto.setIdProduto(idProduto);
		produto.setDesProduto(descProd);
		produto.setCodIndustria(codIndProd);
		produto.setPesoBruto(pesoBruto);
		produto.setPesoLiquido(pesoLiquido);
		produto.setAlturaProduto(alturaProd);
		produto.setProfunProduto(profundidade);
		produto.setLarguraProduto(larguraProduto);
		produto.setCaminhoImagem(caminhoImagem);
		
		ncm.setIdNcm(idNcmProd);
		produto.setNcmProduto(ncm);
		
		embalagem.setIdEmbalagem(idEmbalagem);
		produto.setEmbalagem(embalagem);
		
		produto.setQtdEmbalagem(qtdEmbalagem);
		produto.setEanDunProduto(eandun);
		try{
			
			return gson.toJson(this.produtoBO.salvarProduto(produto));
		} catch (Exception e) {
			return gson.toJson(MensagemWS.getMensagem(produto.getIdProduto()==0L?MensagemWS.INSERT_PROD_ERRO:MensagemWS.ATUALIZA_PRDO_ERRO));
		}
	}

}