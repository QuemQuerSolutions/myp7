package com.plataforma.myp7.data;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Parametro")
public class Parametro implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private ParametroDominio parametroDominio;
	private ParametroTipo tipo;
	private String nome;
	private String auxiliar;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ParametroDominio getParametroDominio() {
		return parametroDominio;
	}
	public void setParametroDominio(ParametroDominio parametroDominio) {
		this.parametroDominio = parametroDominio;
	}
	public ParametroTipo getTipo() {
		return tipo;
	}
	public void setTipo(ParametroTipo tipo) {
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
