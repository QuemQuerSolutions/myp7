package com.plataforma.myp7.data;

import java.io.Serializable;

public class Imagem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id_Imagem;
	private String caminhoImagem;
	public Long getId_Imagem() {
		return id_Imagem;
	}
	public void setId_Imagem(Long id_Imagem) {
		this.id_Imagem = id_Imagem;
	}
	public String getCaminhoImagem() {
		return caminhoImagem;
	}
	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}
	 
	
}
