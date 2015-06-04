package com.plataforma.myp7.data;

import java.io.Serializable;
import java.math.BigDecimal;

public class Produto implements Serializable{

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
	
	public void setPesoBruto(String pesoBruto) {
		try{
			this.pesoBruto = new BigDecimal(pesoBruto);
		}catch(Exception e){
			this.pesoBruto = null;
		}		
	}	
	
	public BigDecimal getPesoLiquido() {
		return pesoLiquido;
	}
	
	public void setPesoLiquido(BigDecimal pesoLiquido) {
		this.pesoLiquido = pesoLiquido;
	}
	
	public void setPesoLiquido(String pesoLiquido) {
		try{
			this.pesoLiquido = new BigDecimal(pesoLiquido);
		}catch(Exception e){
			this.pesoLiquido = null;
		}			
	}	
	
	public BigDecimal getAlturaProduto() {
		return alturaProduto;
	}
	
	public void setAlturaProduto(BigDecimal alturaProduto) {
		this.alturaProduto = alturaProduto;
	}
	
	public void setAlturaProduto(String alturaProduto) {
		try{
			this.alturaProduto = new BigDecimal(alturaProduto);
		}catch(Exception e){
			this.alturaProduto = null;
		}			
	}	
	
	public BigDecimal getLarguraProduto() {
		return larguraProduto;
	}
	
	public void setLarguraProduto(BigDecimal larguraProduto) {
		this.larguraProduto = larguraProduto;
	}
	
	public void setLarguraProduto(String larguraProduto) {
		try{
			this.larguraProduto = new BigDecimal(larguraProduto);
		}catch(Exception e){
			this.larguraProduto = null;
		}		
	}	
	
	public BigDecimal getProfunProduto() {
		return profunProduto;
	}
	
	public void setProfunProduto(BigDecimal profunProduto) {
		this.profunProduto = profunProduto;
	}
	
	public void setProfunProduto(String profunProduto) {
		try{
			this.profunProduto = new BigDecimal(profunProduto);
		}catch(Exception e){
			this.profunProduto = null;
		}
	}	
	
	public Imagem getImagem() {
		return imagem;
	}
	
//	public void setImagem(Imagem imagem) {
//		this.imagem = imagem;
//	}
	
	public NCM getNcmProduto() {
		return ncmProduto;
	}
	
	public void setNcmProduto(NCM ncmProduto) {
		this.ncmProduto = ncmProduto;
	}
	
	public void setNcmProdutoST(String ncmProdutoST) {
		NCM ncm = new NCM();
		ncm.setCodNcm(ncmProdutoST);
		
		this.ncmProduto = ncm;
	}	
	
	public Embalagem getEmbalagem() {
		return embalagem;
	}
	
	public void setEmbalagem(Embalagem embalagem) {
		this.embalagem = embalagem;
	}
	
	public void setEmbalagemST(String embalagemST) {
		Embalagem emb = new Embalagem();
		try{
			emb.setIdEmbalagem(Long.parseLong(embalagemST));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		this.embalagem = emb;
	}
}
