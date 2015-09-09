package com.plataforma.myp7.data;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.plataforma.myp7.util.Utils;

@Alias("Comprador")
public class Comprador implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer idUsuario;
	private String status;
	private Integer ediCodigo;
	private String apelido;
	
	public Comprador() {}
	
	public Comprador(Comprador comprador) {
		this.id = comprador.getId();
		this.apelido = Utils.toLike(comprador.getApelido()); 
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public String getStatus() {
		return status;
	}

	public Integer getEdiCodigo() {
		return ediCodigo;
	}

	public String getApelido() {
		return apelido;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setEdiCodigo(Integer ediCodigo) {
		this.ediCodigo = ediCodigo;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	
}
