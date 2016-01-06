package com.plataforma.myp7.data;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Embalagem")
public class Embalagem implements Serializable{
	
	private static final long serialVersionUID = 8876963048283964005L;

	private Long idEmbalagem;
	private String nomeEmbalagem;
	private String siglaEmbalagem;
	private String nomeEmbalagemCompra;
	
	public Embalagem(){
				
	}
	
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
	public String getNomeEmbalagemCompra() {
		return nomeEmbalagemCompra;
	}
	public void setNomeEmbalagemCompra(String nomeEmbalagemCompra) {
		this.nomeEmbalagemCompra = nomeEmbalagemCompra;
	}
	
	
}
