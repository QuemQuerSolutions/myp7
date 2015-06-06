package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.setCodRetorno;
import static com.plataforma.myp7.util.Utils.setMsgRetorno;

import java.sql.SQLException;
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
			setMsgRetorno(model, "Embalagem salva com sucesso");
			setCodRetorno(model, 0);
			return true;
		}
		return false;
	}

	public Boolean isInsertValido(Produto produto, Model model) {
		return new NcmBO().validaNcm(produto.getNcmProduto(), model);
	} 
	
	public List<Produto> obterProdutos(Produto produto, Model model) throws SQLException{
		
		if(this.produtoDAO.count(produto) > Integer.parseInt(GeralEnum.LIMITE_COUNT.getValor())){
			Utils.setMsgRetorno(model, "Refina sua pesquisa.");
			Utils.setCodRetorno(model, -1);
			return null;
		}
		return produtoDAO.obterProdutos(produto);	
	}
}
