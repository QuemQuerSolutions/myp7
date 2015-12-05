package com.plataforma.myp7.enums;

public enum SituacaoTituloEnum {
	ABERTO("A", "Aberto"),
	QUITADO("Q", "Quitado"),
	PARCIAL("P", "Parcial Quitado");
	
	private String sigla;
	private String nome;
	
	private SituacaoTituloEnum(String sigla, String nome) {
		this.sigla = sigla;
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public String getNome() {
		return nome;
	}
	
}
