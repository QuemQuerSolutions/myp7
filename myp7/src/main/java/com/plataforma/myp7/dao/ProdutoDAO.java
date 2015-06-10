package com.plataforma.myp7.dao;

import static com.plataforma.myp7.config.Conexao.getConexao;
import static com.plataforma.myp7.util.Utils.setCodRetorno;
import static com.plataforma.myp7.util.Utils.setMsgRetorno;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.util.Utils;

public class ProdutoDAO {
	private SqlSession session;
	
	public ProdutoDAO(){
		this.session = getConexao();
	}
	
	public List<Produto> obterTodos(){
		return this.session.selectList("obterTodos");
	}
	
	public List<Produto> obterProdutos(Produto produto) throws Exception{
		this.setLike(produto);
		List<Produto> lstProduto = this.session.selectList("obterProdutos", produto);
		this.cleanLike(produto);
		return lstProduto;
	}
	
	public Integer count(Produto produto) throws Exception{
		this.setLike(produto);
		Integer count = (Integer) this.session.selectOne("countProduto", produto);
		this.cleanLike(produto);
		return count;
	}
	
	private void setLike(Produto produto){
		produto.setDesProduto(Utils.toLike(produto.getDesProduto()));
	}
	
	private void cleanLike(Produto produto){
		produto.setDesProduto(Utils.cleanLike(produto.getDesProduto()));
		
	}

	public Boolean salvar(Produto produto, Model model) {
		try{
			this.session.insert("salvarProduto", produto);
			this.session.commit(true);
			
			setMsgRetorno(model, "Embalagem salva com sucesso");
			setCodRetorno(model, 0);
			
			return true;
		}catch(Exception e){
			setMsgRetorno(model, "Embalagem salva com sucesso");
			setCodRetorno(model, 1);
			
			return false;
		}
	}
	
}
