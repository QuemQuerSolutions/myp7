package com.plataforma.myp7.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.plataforma.myp7.interfaces.ComboPessoa;

import static com.plataforma.myp7.util.Utils.*;

@Alias("Comprador")
public class Comprador extends Pessoa implements Serializable, ComboPessoa {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Usuario usuario;
	private Usuario gerente;
	private String status;
	private Integer ediCodigo;
	private String apelido;
	private List<Empresa> empresa;
	private List<Representante> representantes;
	
	public Comprador() {
		super();
		usuario = new Usuario();
		gerente = new Usuario();
		empresa = new ArrayList<Empresa>();
		representantes = new ArrayList<Representante>();
	}
	
	public Comprador(Integer id) {
		this();
		this.id = id;
	}
	
	public Comprador(Comprador comprador) {
		this();
		this.id = comprador.getId();
		this.apelido = toLike(comprador.getApelido()); 
	}


	public Usuario getGerente() {
		return gerente;
	}

	public void setGerente(Usuario gerente) {
		this.gerente = gerente;
	}

	public List<Representante> getRepresentantes() {
		return representantes;
	}

	public void setRepresentantes(List<Representante> representantes) {
		this.representantes = representantes;
	}

	public List<Empresa> getEmpresa() {
		return empresa;
	}

	public void setEmpresa(List<Empresa> empresa) {
		this.empresa = empresa;
	}

	public Integer getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
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

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	@Override
	public Long getIdCombo() {
		return this.getId().longValue();
	}

	@Override
	public String getDescricaoCombo() {
		return this.getApelido();
	}

	
}
