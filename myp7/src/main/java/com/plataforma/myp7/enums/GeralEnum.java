package com.plataforma.myp7.enums;

public enum GeralEnum {
	LIMITE_COUNT(50);
	
	private Integer valor;

	GeralEnum(Integer valor){
		this.valor = valor;
	}
	
	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	
}
