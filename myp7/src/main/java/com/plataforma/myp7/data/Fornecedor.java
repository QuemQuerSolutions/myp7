package com.plataforma.myp7.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.type.Alias;

@Alias("Fornecedor")
public class Fornecedor extends Pessoa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idFornecedor;
	private String statusFornecedor;
	private String utilTabCustoFornc;
	private String cnpjFormatado;
	private String uuid;
	private List<Representante> representantes;
	private Usuario usuario;
	private String param;
	
	//Mensagens
	private String msgRetorno;
	private Integer codRetorno;
	
	public Fornecedor(){
		super();
		uuid = UUID.randomUUID().toString();
		this.representantes = new ArrayList<Representante>();
		this.usuario=new Usuario();
	}
	
	public Fornecedor(Long idFornecedor){
		this();
		this.setIdFornecedor(idFornecedor);
	}
	
	
	
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMsgRetorno() {
		return msgRetorno;
	}

	public void setMsgRetorno(String msgRetorno) {
		this.msgRetorno = msgRetorno;
	}

	public Integer getCodRetorno() {
		return codRetorno;
	}

	public void setCodRetorno(Integer codRetorno) {
		this.codRetorno = codRetorno;
	}

	public Long getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public String getStatusFornecedor() {
		return statusFornecedor;
	}
	public void setStatusFornecedor(String statusFornecedor) {
		this.statusFornecedor = statusFornecedor;
	}
	public String getUtilTabCustoFornc() {
		return utilTabCustoFornc;
	}
	public void setUtilTabCustoFornc(String utilTabCustoFornc) {
		this.utilTabCustoFornc = utilTabCustoFornc;
	}

	public String getCnpjFormatado() {
		return cnpjFormatado;
	}

	public void setCnpjFormatado(String cnpjFormatado) {
		this.cnpjFormatado = cnpjFormatado;
	}

	public List<Representante> getRepresentantes() {
		return representantes;
	}

	public void setRepresentantes(List<Representante> representantes) {
		this.representantes = representantes;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
}
