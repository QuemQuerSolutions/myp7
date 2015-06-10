package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.setCodRetorno;
import static com.plataforma.myp7.util.Utils.setMsgRetorno;

import java.util.List;

import org.springframework.ui.Model;

import com.plataforma.myp7.dao.ProdutoDAO;
import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.enums.GeralEnum;
import com.plataforma.myp7.util.Utils;

public class ProdutoBO {

	private ProdutoDAO produtoDAO;
	public ProdutoBO(){
		produtoDAO = new ProdutoDAO();
	}
	
	public Boolean salvar(Produto produto, Model model) {
		if(this.isInsertValido(produto, model)){
			return new ProdutoDAO().salvar(produto, model);
		}
		return false;
	}

	public Boolean isInsertValido(Produto produto, Model model) {
		if(produto.getNcmProduto() == null){
			setMsgRetorno(model, "NCM n�o encontrado.");
			setCodRetorno(model, 1);
			
			return false;
		}
		return true;
	} 
	
	public List<Produto> obterProdutos(Produto produto, Model model) throws Exception{
		
		Integer count = this.produtoDAO.count(produto);
		
		if(count == 0){
			setMsgRetorno(model, "Nenhum registro localizado.");
			setCodRetorno(model, -1);
			return null;
		}
		
		if(count > Integer.parseInt(GeralEnum.LIMITE_COUNT.getValor())){
			Utils.setMsgRetorno(model, "Refine sua pesquisa.");
			Utils.setCodRetorno(model, -1);
			return null;
		}
		
		return produtoDAO.obterProdutos(produto);	
	}
}