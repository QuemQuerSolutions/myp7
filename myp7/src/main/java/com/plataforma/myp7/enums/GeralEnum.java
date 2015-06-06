package com.plataforma.myp7.enums;

public enum GeralEnum {
	LIMITE_COUNT("50"),
	THEME_DEFAULT(ThemeEnum.THEME_ORANGE.getValorCSS());
	
	private String valor;

	GeralEnum(String valor){
		this.valor = valor;
	}
	
	public String getValor() { return valor; }
}
