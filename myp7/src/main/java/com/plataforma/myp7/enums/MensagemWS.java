package com.plataforma.myp7.enums;

import com.plataforma.myp7.dto.MensagemRetornoDTO;

public enum MensagemWS {
	CONSUL_VAZIA(1,"Nenhum registro encontrado"),
	INSERT_PROD_ERRO (2, "Erro ao inserir o produto."),
	INSERT_PROD_SUCESSO(3, "Produto inserido com sucesso."),
	ATUALIZA_PROD_SUCESSO(9,"Produto atualizado com sucesso."),
	ATUALIZA_PRDO_ERRO(10, "Erro ao atualizar o produto"),
	
	
	INSERT_FORNC_ERRO(4,"Erro ao inserir o fornecedor."),
	INSERT_FORNC_SUCESSO(5,"Fornecedor inserido com sucesso."),
	ATUALIZA_FORNC_SUCESSO(13,"Fornecedor atualizado com sucesso."),
	ATUALIZA_FORNC_ERRO(14, "Erro ao atualizar o fornecedor"),
	
	INSERT_COMPRADOR_ERRO(15,"Erro ao inserir o comprador."),
	INSERT_COMPRADOR_SUCESSO(16,"Comprador inserido com sucesso."),
	ATUALIZA_COMPRADOR_SUCESSO(17,"Comprador atualizado com sucesso."),
	ATUALIZA_COMPRADOR_ERRO(18, "Erro ao atualizar o comprador"),	
	
	INSERT_REPRESENTANTE_ERRO(19,"Erro ao inserir o representante."),
	INSERT_REPRESENTANTE_SUCESSO(20,"Representante inserido com sucesso."),
	ATUALIZA_REPRESENTANTE_SUCESSO(21,"Representante atualizado com sucesso."),
	ATUALIZA_REPRESENTANTE_ERRO(22, "Erro ao atualizar o representante"),
	
	INSERT_EMPRESA_ERRO(23,"Erro ao inserir o empresa."),
	INSERT_EMPRESA_SUCESSO(24,"Empresa inserido com sucesso."),
	ATUALIZA_EMPRESA_SUCESSO(25,"Empresa atualizado com sucesso."),
	ATUALIZA_EMPRESA_ERRO(26, "Erro ao atualizar a empresa"),	
	
	INSERT_PESSOA_ERRO(6, "Erro ao inserir os dados da pessoa."),
	INSERT_PESSOA_SUCESSO(7,"Pessoa inserida com sucesso."),
	ATUALIZA_PESSOA_SUCESSO(11, "Pessoa atualizada com sucesso."),
	ATUALIZA_PESSOA_ERRO(12, "Erro ao atualizar a pessoa."),
	
	CONSULTA_EMB_NCM_VAZIO(8, "Embalagem, NCM ou Usuario invalidos.");
	
	private String mensagem;
	private Integer codigo;
	
	MensagemWS(Integer codigo,String mensagem){
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
	
	public static MensagemRetornoDTO getMensagem(MensagemWS mensagem){
		MensagemRetornoDTO msgem = new MensagemRetornoDTO();
		for(MensagemWS msg:MensagemWS.values()){
			if (mensagem.getCodigo()== msg.getCodigo()){
				msgem.setCodRetorno(msg.getCodigo());
				msgem.setMsgRetorno(msg.getMensagem());
				return msgem;
			}
		}
		return msgem;
	}
}
