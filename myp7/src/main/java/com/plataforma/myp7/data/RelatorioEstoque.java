package com.plataforma.myp7.data;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

@Alias("RelatorioEstoque")
public class RelatorioEstoque implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String produto;
	private Integer qtdEstoque;
	private BigDecimal mediaVendaDia;
	
	private String dataUltimaCompra;
	private String comprador;
	private String representante;
	private String fornecedor;
	
	
	
	private Long idUsuario;
	private Long idProduto;
	private Long idRepresentante;
	
	public RelatorioEstoque(){
		
	}
	
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public Integer getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public BigDecimal getMediaVendaDia() {
		return mediaVendaDia;
	}
	public void setMediaVendaDia(BigDecimal mediaVendaDia) {
		this.mediaVendaDia = mediaVendaDia;
	}
	public String getDataUltimaCompra() {
		return dataUltimaCompra;
	}
	public void setDataUltimaCompra(String dataUltimaCompra) {
		this.dataUltimaCompra = dataUltimaCompra;
	}
	public String getComprador() {
		return comprador;
	}
	public void setComprador(String comprador) {
		this.comprador = comprador;
	}
	public String getRepresentante() {
		return representante;
	}
	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Long getIdRepresentante() {
		return idRepresentante;
	}

	public void setIdRepresentante(Long idRepresentante) {
		this.idRepresentante = idRepresentante;
	}

	
	
	
}
