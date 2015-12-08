package com.plataforma.myp7.enums;

public enum SituacaoIntegracaoEnum {
	INTEGRADO("I", "integrado", "Integrado"),
	APROVADO("A", "aprovado", "Aprovado"),
	REPROVADO("R", "reprovado", "Reprovado"),
	AGUARDANDO("G", "aguardando", "Aguard. Aprovação");
	
	private String valor;
	private String valorHtml;
	private String descricao;

	private SituacaoIntegracaoEnum(String valor, String valorHtml, String desc){
		this.valor = valor;
		this.valorHtml = valorHtml;
		this.descricao = desc;
	}
	
	public String getSigla() { return valor; }
	public String getValorHtml() { return valorHtml; }
	public String getDescricao() { return descricao; }
	
	public static String getDescricao(String sigla){
		for(SituacaoIntegracaoEnum situacao: SituacaoIntegracaoEnum.values()){
			if(sigla.equals(situacao.getSigla())){
				return situacao.getDescricao();
			}
		}
		return null;
	}
	
	public static String getSigla(String valorHtml){
		
		if("todos".equals(valorHtml)) return null;
		
		for(SituacaoIntegracaoEnum situacao: SituacaoIntegracaoEnum.values()){
			if(valorHtml.equals(situacao.getValorHtml())){
				return situacao.getSigla();
			}
		}
		return null;
	}
}
