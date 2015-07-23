package com.plataforma.myp7.enums;

public enum Mensagem {
	CONSUL_VAZIA(1,"Nenhum registro encontrado"),
	INSERT_PROD_ERRO (2, "Erro ao inserir o produto."),
	INSERT_PROD_SUCESSO(3, "Produto inserido com sucesso."),
	ATUALIZA_PROD_SUCESSO(9,"Produto atualizado com sucesso."),
	ATUALIZA_PRDO_ERRO(10, "Erro ao atualizar o produto"),
	
	
	INSERT_FORNC_ERRO(4,"Erro ao inserir o fornecedor."),
	INSERT_FORNC_SUCESSO(5,"Fornecedor inserido com sucesso."),
	
	INSERT_PESSOA_ERRO(6, "Erro ao inserir os dados da pessoa."),
	INSERT_PESSOA_SUCESSO(7,"Pessoa inserida com sucesso."),
	
	CONSULTA_EMB_NCM_VAZIO(8, "Embalagem, NCM ou Usuario invalidos.");
	
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
