package com.plataforma.myp7.data;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

@Alias("Pessoa")
public class Pessoa {
	private Long idPessoa;
	private String razao;
	private String fantasia;
	private String fisicaJuridica;
	private String sexo;
	private String cep;
	private String cidade;
	private String uf;
	private String bairro;
	private String logradouro;
	private String logradouroNro;
	private String logradrouroCompl;
	private String fone1;
	private String fone2;
	private String fone3;
	private BigDecimal nroCpfCnpj;
	private Integer digCpfCnpj;
	private String nroRgInscrEstadual;
	
	public Pessoa(){}
	
	public Pessoa(Integer id){
		this.idPessoa = id.longValue();
	}
	
	public Long getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}
	public String getRazao() {
		return razao;
	}
	public void setRazao(String razao) {
		this.razao = razao;
	}
	public String getFantasia() {
		return fantasia;
	}
	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}
	public String getFisicaJuridica() {
		return fisicaJuridica;
	}
	public void setFisicaJuridica(String fisicaJuridica) {
		this.fisicaJuridica = fisicaJuridica;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getLogradouroNro() {
		return logradouroNro;
	}
	public void setLogradouroNro(String logradouroNro) {
		this.logradouroNro = logradouroNro;
	}
	public String getLogradrouroCompl() {
		return logradrouroCompl;
	}
	public void setLogradrouroCompl(String logradrouroCompl) {
		this.logradrouroCompl = logradrouroCompl;
	}
	public String getFone1() {
		return fone1;
	}
	public void setFone1(String fone1) {
		this.fone1 = fone1;
	}
	public String getFone2() {
		return fone2;
	}
	public void setFone2(String fone2) {
		this.fone2 = fone2;
	}
	public String getFone3() {
		return fone3;
	}
	public void setFone3(String fone3) {
		this.fone3 = fone3;
	}
	public BigDecimal getNroCpfCnpj() {
		return nroCpfCnpj;
	}
	public void setNroCpfCnpj(BigDecimal nroCpfCnpj) {
		this.nroCpfCnpj = nroCpfCnpj;
	}
	public Integer getDigCpfCnpj() {
		return digCpfCnpj;
	}
	public void setDigCpfCnpj(Integer digCpfCnpj) {
		this.digCpfCnpj = digCpfCnpj;
	}
	public String getNroRgInscrEstadual() {
		return nroRgInscrEstadual;
	}
	public void setNroRgInscrEstadual(String nroRgInscrEstadual) {
		this.nroRgInscrEstadual = nroRgInscrEstadual;
	}
}
