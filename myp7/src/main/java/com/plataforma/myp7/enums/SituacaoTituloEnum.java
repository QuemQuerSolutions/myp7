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

	public String getSigla() { return sigla; }
	public String getNome() { return nome; }
	
	public static String getSigla(String nome){
		
		for(SituacaoTituloEnum sit: SituacaoTituloEnum.values())
			if(sit.getNome().equals(nome))
				return sit.getSigla();
		
		return null;
	}
	
	public static String getNome(String sigla){
		
		for(SituacaoTituloEnum sit: SituacaoTituloEnum.values())
			if(sit.getSigla().equals(sigla))
				return sit.getNome();
		
		return null;
	}
}
