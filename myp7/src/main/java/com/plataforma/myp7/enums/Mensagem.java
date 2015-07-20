package com.plataforma.myp7.enums;

public enum Mensagem {
	CONSUL_VAZIA(1,"Nenhum Registro Encontrado"),
	INSERT_PROD_ERRO (2, "Erro na inserção do produto."),
	INSERT_PROD_SUCESSO(3, "Produto inserido com sucesso."),
	
	INSERT_FORNC_ERRO(4,"Erro na inserção do Fornecedor."),
	INSERT_FORNC_SUCESSO(5,"Fornecedor inserido com sucesso.");
	
	private String mensagem;
	private Integer codigo;
	
	Mensagem(Integer codigo,String mensagem){
		this.mensagem=mensagem;
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	
}
