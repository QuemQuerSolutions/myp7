package com.plataforma.myp7.data;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

@Alias("FornecedorCusto")
public class FornecedorCusto {
	private Integer idTabCustoFornecedor;
	private Fornecedor fornecedor;
	private Integer idEmpresa;
	private Produto produto;
	private BigDecimal valor;
	private Integer idRepresentante;
	
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
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Integer getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
}
