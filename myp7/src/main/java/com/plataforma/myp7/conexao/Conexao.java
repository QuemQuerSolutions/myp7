package com.plataforma.myp7.conexao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.plataforma.myp7.config.MyBatisConnectionFactory;

public class Conexao {
	
	private static Conexao instance;
	
	private SqlSession sqlSession;
	
	private Conexao(){
		this.sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	}
	
	public static Conexao getInstance(){
		if(Conexao.instance == null){
			return new Conexao();
		}
		return Conexao.instance;
	}
	
	public SqlSession getSqlSession(){
		return this.sqlSession;
	}
}
