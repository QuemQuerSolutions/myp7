package com.plataforma.myp7.enums;

public enum TipoUsuarioEnum {
	RETAGUARDA("R"),
	PORTAL("P");
	
	private String siglaUsuario;
	
	TipoUsuarioEnum(String siglaUsuario){
		this.siglaUsuario=siglaUsuario;
	}

	public String getSiglaUsuario() {
		return siglaUsuario;
	}
	
}
