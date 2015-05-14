package com.plataforma.myp7.data;

import java.io.Serializable;
import java.math.BigDecimal;

public class Produto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idProduto;
	private String codProduto;
	private String desProduto;
	private String codIndustria;
	private BigDecimal pesoBruto;
	private BigDecimal pesoLiquido;
	private BigDecimal alturaProduto;
	private BigDecimal larguraProduto;
	private BigDecimal profunProduto;
	private Imagem imagem;
	private NCM ncmProduto;
	private Embalagem embalagem;
	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	public String getCodProduto() {
		return codProduto;
	}
	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}
	public String getDesProduto() {
		return desProduto;
	}
	public void setDesProduto(String desProduto) {
		this.desProduto = desProduto;
	}
	public String getCodIndustria() {
		return codIndustria;
	}
	public void setCodIndustria(String codIndustria) {
		this.codIndustria = codIndustria;
	}
	public BigDecimal getPesoBruto() {
		return pesoBruto;
	}
	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}
	public BigDecimal getPesoLiquido() {
		return pesoLiquido;
	}
	public void setPesoLiquido(BigDecimal pesoLiquido) {
		this.pesoLiquido = pesoLiquido;
	}
	public BigDecimal getAlturaProduto() {
		return alturaProduto;
	}
	public void setAlturaProduto(BigDecimal alturaProduto) {
		this.alturaProduto = alturaProduto;
	}
	public BigDecimal getLarguraProduto() {
		return larguraProduto;
	}
	public void setLarguraProduto(BigDecimal larguraProduto) {
		this.larguraProduto = larguraProduto;
	}
	public BigDecimal getProfunProduto() {
		return profunProduto;
	}
	public void setProfunProduto(BigDecimal profunProduto) {
		this.profunProduto = profunProduto;
	}
	public Imagem getImagem() {
		return imagem;
	}
	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}
	public NCM getNcmProduto() {
		return ncmProduto;
	}
	public void setNcmProduto(NCM ncmProduto) {
		this.ncmProduto = ncmProduto;
	}
	public Embalagem getEmbalagem() {
		return embalagem;
	}
	public void setEmbalagem(Embalagem embalagem) {
		this.embalagem = embalagem;
	}
	
}
