package com.plataforma.myp7.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("Usuario")
public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String portal;
	private String retaguarda;

	private Long idUsuario;
	private Long idSuperior;
	private String razaoSocial;
	private String email;
	private String nDocumento;
	private String ativo;
	private String senha;
	private String theme;
	private String tipoUsuario;
	private Long alcada;
	private Integer flagAprovProduto;
	private List<Long> lstIdUsuariosSubordinado;
	private boolean isSubordinado;
	
	public Usuario(){
		this.portal="P";
		this.retaguarda = "R";
		this.lstIdUsuariosSubordinado = new ArrayList<Long>();
	}
	
	public Usuario(Long idUsuario){
		this.idUsuario = idUsuario;
	}
	
	public Usuario(Long idUsuario, String razao){
		this.idUsuario = idUsuario;
		this.razaoSocial = razao;
	}
	
	public Usuario(String razao){
		this.razaoSocial = razao;
	}
	
	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getnDocumento() {
		return nDocumento;
	}
	public void setnDocumento(String nDocumento) {
		this.nDocumento = nDocumento;
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPortal() {
		return portal;
	}

	public void setPortal(String portal) {
		this.portal = portal;
	}

	public String getRetaguarda() {
		return retaguarda;
	}

	public void setRetaguarda(String retaguarda) {
		this.retaguarda = retaguarda;
	}

	public Long getAlcada() {
		return alcada;
	}

	public Integer getFlagAprovProduto() {
		return flagAprovProduto;
	}

	public void setAlcada(Long alcada) {
		this.alcada = alcada;
	}

	public void setFlagAprovProduto(Integer flagAprovProduto) {
		this.flagAprovProduto = flagAprovProduto;
	}

	public Long getIdSuperior() {
		return idSuperior;
	}

	public void setIdSuperior(Long idSuperior) {
		this.idSuperior = idSuperior;
	}
	

	public List<Long> getLstIdUsuariosSubordinado() {
		return lstIdUsuariosSubordinado;
	}

	public void setLstIdUsuariosSubordinado(List<Long> lstIdUsuariosSubordinado) {
		this.lstIdUsuariosSubordinado = lstIdUsuariosSubordinado;
	}
	

	public boolean isSubordinado() {
		return isSubordinado;
	}

	public void setSubordinado(boolean isSubordinado) {
		this.isSubordinado = isSubordinado;
	}

	@Override
	public String toString() {
		return email;
	}
}
