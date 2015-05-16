package com.plataforma.myp7.data;

import java.io.Serializable;
import java.util.Date;

public class HistoricoSenha implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String senha;
	private Date dataInclusao;
	
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