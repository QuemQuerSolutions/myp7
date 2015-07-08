package com.plataforma.myp7.dao;

import static com.plataforma.myp7.config.Conexao.getConexao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.plataforma.myp7.data.Usuario;

public class UsuarioDAO {
	private SqlSession session;
	
	public Usuario selecionarPorEmail(String email){
		this.session = getConexao();
		Usuario user = this.session.selectOne("obterPorEmail", email);
		this.session.close();
		return user;
	}
	
	public void inserir(Usuario usuario) throws SQLException{
		this.session = getConexao();
		this.session.insert("incluir", usuario);
		this.session.commit();
		this.session.close();
	}
	
	
	public void updateTheme(Usuario usuario) throws SQLException{
		this.session = getConexao();
		this.session.update("updateTheme", usuario);
		this.session.commit();
		this.session.close();
	}

	public Usuario selecionarPorId(Long id) {
		this.session = getConexao();
		Usuario user = this.session.selectOne("obterPorId", id);
		this.session.close();
		return user;
	}
}
