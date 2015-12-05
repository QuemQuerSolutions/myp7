package com.plataforma.myp7.data;

import java.io.Serializable;
import java.util.Date;

public class RelatorioAcordoComercial implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer nroTitulo;
	private String especie;
	private String comprador;
	private String representante;
	private String fornecedor;
	private Date dataInclusao;
	private Date dataVencimento;
	private Double valor;
	private String situacao;
	
	private String dataInclusaoDe;
	private String dataVencimentoDe;
	private String dataInclusaoAte;
	private String dataVencimentoAte;

	private Date dataInclusaoDeDate;
	private Date dataVencimentoDeDate;
	private Date dataInclusaoAteDate;
	private Date dataVencimentoAteDate;
	
	public RelatorioAcordoComercial() {	}

	public Integer getNroTitulo() {
		return nroTitulo;
	}

	public String getEspecie() {
		return especie;
	}

	public String getComprador() {
		return comprador;
	}

	public String getRepresentante() {
		return representante;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public Double getValor() {
		return valor;
	}

	public String getSituacao() {
		return situacao;
	}

	
	public String getDataInclusaoDe() {
		return dataInclusaoDe;
	}

	public String getDataVencimentoDe() {
		return dataVencimentoDe;
	}

	public String getDataInclusaoAte() {
		return dataInclusaoAte;
	}

	public String getDataVencimentoAte() {
		return dataVencimentoAte;
	}

	public void setDataInclusaoDe(String dataInclusaoDe) {
		this.dataInclusaoDe = dataInclusaoDe;
	}

	public void setDataVencimentoDe(String dataVencimentoDe) {
		this.dataVencimentoDe = dataVencimentoDe;
	}

	public void setDataInclusaoAte(String dataInclusaoAte) {
		this.dataInclusaoAte = dataInclusaoAte;
	}

	public void setDataVencimentoAte(String dataVencimentoAte) {
		this.dataVencimentoAte = dataVencimentoAte;
	}

	public void setNroTitulo(Integer nroTitulo) {
		this.nroTitulo = nroTitulo;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public void setComprador(String comprador) {
		this.comprador = comprador;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Date getDataInclusaoDeDate() {
		return dataInclusaoDeDate;
	}

	public Date getDataVencimentoDeDate() {
		return dataVencimentoDeDate;
	}

	public Date getDataInclusaoAteDate() {
		return dataInclusaoAteDate;
	}

	public Date getDataVencimentoAteDate() {
		return dataVencimentoAteDate;
	}

	public void setDataInclusaoDeDate(Date dataInclusaoDeDate) {
		this.dataInclusaoDeDate = dataInclusaoDeDate;
	}

	public void setDataVencimentoDeDate(Date dataVencimentoDeDate) {
		this.dataVencimentoDeDate = dataVencimentoDeDate;
	}

	public void setDataInclusaoAteDate(Date dataInclusaoAteDate) {
		this.dataInclusaoAteDate = dataInclusaoAteDate;
	}

	public void setDataVencimentoAteDate(Date dataVencimentoAteDate) {
		this.dataVencimentoAteDate = dataVencimentoAteDate;
	}

	
}
