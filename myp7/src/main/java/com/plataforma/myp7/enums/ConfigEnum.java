package com.plataforma.myp7.enums;

public enum ConfigEnum {
	LIMITE_COUNT("50"),
	THEME_DEFAULT(ThemeEnum.THEME_ORANGE.getValorCSS()),
	FOLDER_UPLOAD_DEFAULT("resources/upload"),
	IMAGEM_DEFAULT_PRODUTO("/myp7/resources/img/default.png"),
	USUARIO_LOGADO("usuarioLogado");
	
	private String valor;

	ConfigEnum(String valor){
		this.valor = valor;
	}
	
	public String getValor() { return valor; }
	public int getValorInt() { return Integer.parseInt(valor); }
}
