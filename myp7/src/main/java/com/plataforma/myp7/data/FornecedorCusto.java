package com.plataforma.myp7.data;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.plataforma.myp7.enums.Mensagem;

@Alias("FornecedorCusto")
public class FornecedorCusto {
	
	private Integer idTabCustoFornecedor;
	private Fornecedor fornecedor;
	private Integer idEmpresa;
	private Produto produto;
	private BigDecimal valorAnterior;
	private BigDecimal valor;
	private Integer idRepresentante;
	private String situacao;
	private Usuario usuAprovacao;
	private Date dataAprovacao;
	private int qtdPorSituacao;
	
	//Mensagens
	private String msgRetorno;
	private Integer codRetorno;
	
	public FornecedorCusto() {
	}
	
	public FornecedorCusto(Mensagem msg) {
		this.msgRetorno = msg.getMensagem();
		this.codRetorno = msg.getCodigo();
	}
	
	public String getMsgRetorno() {
		return msgRetorno;
	}

	public void setMsgRetorno(String msgRetorno) {
		this.msgRetorno = msgRetorno;
	}

	public Integer getCodRetorno() {
		return codRetorno;
	}

	public void setCodRetorno(Integer codRetorno) {
		this.codRetorno = codRetorno;
	}

	public Integer getIdTabCustoFornecedor() {
		return idTabCustoFornecedor;
	}
	public void setIdTabCustoFornecedor(Integer idTabCustoFornecedor) {
		this.idTabCustoFornecedor = idTabCustoFornecedor;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public String getValorAnteriorFormatado() {
		try{
			String valorS = valorAnterior.toString();
			if(valorS.split("\\.").length > 1 && valorS.split("\\.")[1] != null)
				if(valorS.split("\\.")[1].length() >= 2)
					valorS = valorS.split("\\.")[0] + "," + valorS.split("\\.")[1].subSequence(0,2);
				else
					valorS = valorS.split("\\.")[0] + "," + valorS.split("\\.")[1] + "0";
			else
				valorS = valorS.split("\\.")[0] + ",00";
			return valorS;
		}catch(Exception e){
			return "0,00";
		}
	}
	public String getValorFormatado() {
		try{
			String valorS = valor.toString();
			if(valorS.split("\\.").length > 1 && valorS.split("\\.")[1] != null)
				if(valorS.split("\\.")[1].length() >= 2)
					valorS = valorS.split("\\.")[0] + "," + valorS.split("\\.")[1].subSequence(0,2);
				else
					valorS = valorS.split("\\.")[0] + "," + valorS.split("\\.")[1] + "0";
			else
				valorS = valorS.split("\\.")[0] + ",00";
			return valorS;
		}catch(Exception e){
			return "0,00";
		}
	}	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Integer getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	public BigDecimal getValorAnterior() {
		return valorAnterior;
	}
	public void setValorAnterior(BigDecimal valorAnterior) {
		this.valorAnterior = valorAnterior;
	}
	
	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Usuario getUsuAprovacao() {
		return usuAprovacao;
	}

	public void setUsuAprovacao(Usuario usuAprovacao) {
		this.usuAprovacao = usuAprovacao;
	}

	public Date getDataAprovacao() {
		return dataAprovacao;
	}

	public void setDataAprovacao(Date dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}
	
	public int getQtdPorSituacao() {
		return qtdPorSituacao;
	}

	public void setQtdPorSituacao(int qtdPorSituacao) {
		this.qtdPorSituacao = qtdPorSituacao;
	}	
}
