package com.plataforma.myp7.enums;

public enum Mensagem {
	REFINE_SUA_PESQUISA (-1, "Refine sua pesquisa."),
	NENHUM_REGISTRO_LOCALIZADO (-1, "Nenhum registro localizado."),
	
	SALVO_SUCESSO (0 , "Salvo com sucesso");
	
	private String mensagem;
	private int codigo;
	
	Mensagem(int codigo,String mensagem){
		this.mensagem = mensagem;
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public int getCodigo() {
		return codigo;
	}
}
