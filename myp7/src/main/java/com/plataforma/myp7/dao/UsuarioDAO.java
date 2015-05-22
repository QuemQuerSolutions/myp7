package com.plataforma.myp7.dao;

import static com.plataforma.myp7.config.Conexao.getConexao;

import org.apache.ibatis.session.SqlSession;

import com.plataforma.myp7.data.Usuario;

public class UsuarioDAO {
	private SqlSession session;
	
	public UsuarioDAO(){
		this.session = getConexao();
	}
	
	public Usuario selecionarPorEmail(String email){
		return this.session.selectOne("obterPorEmail", email);
	}
}
