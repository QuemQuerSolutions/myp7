package com.plataforma.myp7.dao;

import static com.plataforma.myp7.config.Conexao.getConexao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.plataforma.myp7.data.Parametro;
import com.plataforma.myp7.data.ParametroDominio;

public class ParametroDAO {
	private SqlSession session;
	
	public List<Parametro> selecionarPorDominio(ParametroDominio dominio){
		this.session = getConexao();
		List<Parametro> lista = this.session.selectList("obterParametro", dominio.getId());
		this.session.close();
		return lista;
	}
	
	public Parametro obterParametroPorNome(final String nome){
		this.session = getConexao();
		Parametro parametro = this.session.selectOne("obterParametroPorNome", nome);
		this.session.close();
		return parametro;
	}
}
