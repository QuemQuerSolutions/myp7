package com.plataforma.myp7.config;

import org.apache.ibatis.session.SqlSession;

public class Conexao {
	
	private static Conexao instance;
	
	private SqlSession sqlSession;
	
	private Conexao(){
		this.sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	}
	
	private static Conexao getInstance(){
		if(Conexao.instance == null){
			return new Conexao();
		}
		return Conexao.instance;
	}
	
	private SqlSession getSqlSession(){
		return this.sqlSession;
	}
	
	public static SqlSession getConexao(){
		return Conexao.getInstance().getSqlSession();
	}
}
