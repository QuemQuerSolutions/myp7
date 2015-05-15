package com.plataforma.myp7.data;

import java.io.Serializable;

public class Parametro implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private DominioParametro dominio;
	private TipoParametro tipo;
	private String nome;
	private String auxiliar;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public DominioParametro getDominio() {
		return dominio;
	}
	public void setDominio(DominioParametro dominio) {
		this.dominio = dominio;
	}
	public TipoParametro getTipo() {
		return tipo;
	}
	public void setTipo(TipoParametro tipo) {
		this.tipo = tipo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAuxiliar() {
		return auxiliar;
	}
	public void setAuxiliar(String auxiliar) {
		this.auxiliar = auxiliar;
	}
}
