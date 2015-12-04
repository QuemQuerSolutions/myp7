package com.plataforma.myp7.enums;

public enum TipoUsuarioEnum {

	COMPRADOR		(1, "Comprador"),
	REPRESENTANTE	(2, "Representante");
	
	private Integer valor;
	private String descricao;

	private TipoUsuarioEnum(Integer valor, String desc){
		this.valor = valor;
		this.descricao = desc;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
