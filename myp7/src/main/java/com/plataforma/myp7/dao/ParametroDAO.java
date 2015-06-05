package com.plataforma.myp7.dao;

import static com.plataforma.myp7.config.Conexao.getConexao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.plataforma.myp7.data.Parametro;
import com.plataforma.myp7.data.ParametroDominio;

public class ParametroDAO {
	private SqlSession session;
	
	public ParametroDAO(){
		this.session = getConexao();
	}
	
	public List<Parametro> selecionarPorDominio(ParametroDominio dominio){
		return this.session.selectList("obterParametro", dominio.getId());
	}
	
	public Parametro obterParametroPorNome(final String nome){
		return this.session.selectOne("obterParametroPorNome", nome);
	}
}
