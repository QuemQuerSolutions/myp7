package com.plataforma.myp7.data;

public class RelatorioEstoque {

	public String empresa;
	public String produto;
	public Integer qtdEstoque;
	public Integer qtdEstoqueTroca;
	public Integer qtdPendenteCompras;
	public Integer qtdTransito;
	public Integer qtdPendenteExpedir;
	public Integer mediaVendaDia;
	public Integer diasEstoque;
	public Integer diasUltimaEntrada;
	
	public String getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
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
	
	public Integer getQtdEstoqueTroca() {
		return qtdEstoqueTroca;
	}
	
	public void setQtdEstoqueTroca(Integer qtdEstoqueTroca) {
		this.qtdEstoqueTroca = qtdEstoqueTroca;
	}
	
	public Integer getQtdPendenteCompras() {
		return qtdPendenteCompras;
	}
	
	public void setQtdPendenteCompras(Integer qtdPendenteCompras) {
		this.qtdPendenteCompras = qtdPendenteCompras;
	}
	
	public Integer getQtdTransito() {
		return qtdTransito;
	}
	
	public void setQtdTransito(Integer qtdTransito) {
		this.qtdTransito = qtdTransito;
	}
	
	public Integer getQtdPendenteExpedir() {
		return qtdPendenteExpedir;
	}
	
	public void setQtdPendenteExpedir(Integer qtdPendenteExpedir) {
		this.qtdPendenteExpedir = qtdPendenteExpedir;
	}
	
	public Integer getMediaVendaDia() {
		return mediaVendaDia;
	}
	
	public void setMediaVendaDia(Integer mediaVendaDia) {
		this.mediaVendaDia = mediaVendaDia;
	}
	
	public Integer getDiasEstoque() {
		return diasEstoque;
	}
	
	public void setDiasEstoque(Integer diasEstoque) {
		this.diasEstoque = diasEstoque;
	}
	
	public Integer getDiasUltimaEntrada() {
		return diasUltimaEntrada;
	}
	
	public void setDiasUltimaEntrada(Integer diasUltimaEntrada) {
		this.diasUltimaEntrada = diasUltimaEntrada;
	}
}
