package com.plataforma.myp7.data;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Usuario")
public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String portal;
	
	private Long idUsuario;
	private String razaoSocial;
	private String email;
	private String nDocumento;
	private String ativo;
	private String senha;
	private String theme;
	private String tipoUsuario;
	
	public Usuario(){
		this.portal="P";
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
	
	
}
