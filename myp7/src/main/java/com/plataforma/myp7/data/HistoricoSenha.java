package com.plataforma.myp7.data;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("HistoricoSenha")
public class HistoricoSenha implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private String senha;
	private Date dataInclusao;
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Date getData() {
		return dataInclusao;
	}
	public void setData(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
}
