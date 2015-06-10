package com.plataforma.myp7.dao;

import static com.plataforma.myp7.config.Conexao.getConexao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

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

	public Boolean salvar(Produto produto) {
		try{
			this.session.insert("salvarProduto", produto);
			this.session.commit(true);
			
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
}
