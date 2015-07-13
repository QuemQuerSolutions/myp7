package com.plataforma.myp7.data;

import org.apache.ibatis.type.Alias;

import com.plataforma.myp7.bo.UsuarioBO;

@Alias("Representante")
public class Representante {
	private Long idRepresentante;
	private String apelido;
	private String status;
	private Usuario usuario;
	
	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void setUsuario(Long idUsuario) {
		this.usuario = new UsuarioBO().obterPorId(idUsuario);
	}

	public Long getIdRepresentante() {
		return idRepresentante;
	}
	
	public void setIdRepresentante(Long idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
