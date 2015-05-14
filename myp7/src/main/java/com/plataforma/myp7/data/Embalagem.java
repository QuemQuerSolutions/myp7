package com.plataforma.myp7.data;

import java.io.Serializable;

public class Embalagem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idEmbalagem;
	private String nomeEmbalagem;
	private String siglaEmbalagem;
	private Integer qtdEmbalagem;
	private String nomeEmbalagemCompra;
	public Long getIdEmbalagem() {
		return idEmbalagem;
	}
	public void setIdEmbalagem(Long idEmbalagem) {
		this.idEmbalagem = idEmbalagem;
	}
	public String getNomeEmbalagem() {
		return nomeEmbalagem;
	}
	public void setNomeEmbalagem(String nomeEmbalagem) {
		this.nomeEmbalagem = nomeEmbalagem;
	}
	public String getSiglaEmbalagem() {
		return siglaEmbalagem;
	}
	public void setSiglaEmbalagem(String siglaEmbalagem) {
		this.siglaEmbalagem = siglaEmbalagem;
	}
	public Integer getQtdEmbalagem() {
		return qtdEmbalagem;
	}
	public void setQtdEmbalagem(Integer qtdEmbalagem) {
		this.qtdEmbalagem = qtdEmbalagem;
	}
	public String getNomeEmbalagemCompra() {
		return nomeEmbalagemCompra;
	}
	public void setNomeEmbalagemCompra(String nomeEmbalagemCompra) {
		this.nomeEmbalagemCompra = nomeEmbalagemCompra;
	}
	
	
}
