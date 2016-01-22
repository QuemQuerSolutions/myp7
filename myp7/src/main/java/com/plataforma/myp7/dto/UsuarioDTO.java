package com.plataforma.myp7.dto;

import java.io.Serializable;
import java.util.List;

public class UsuarioDTO  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String portal;
	private String retaguarda;

	private Long idUsuario;
	private String razaoSocial;
	private String email;
	private String nDocumento;
	private String ativo;
	private String senha;
	private String theme;
	private String tipoUsuario;
	private Integer aprProd;
	private Integer valorAlcada;
	
	private List<Long> listIdsUsuarioRemoverLista;
	private String idsUsuarioParametrosSubordinados;
	private String idsUsuarioRemoverLista;
	private Boolean aprovacaoProduto;
	private Boolean aprovacaoCusto;
	private Boolean aprProdBoo;
	private Boolean aprCustoBoo;
	private Long idSuperior;
	
	
	public List<Long> getListIdsUsuarioRemoverLista() {
		return listIdsUsuarioRemoverLista;
	}

	public void setListIdsUsuarioRemoverLista(List<Long> list) {
		this.listIdsUsuarioRemoverLista = list;
	}

	public UsuarioDTO(){
		this.portal="P";
		this.retaguarda = "R";
	}
	
	public UsuarioDTO(Long idUsuario){
		this.idUsuario = idUsuario;
	}
	
	public UsuarioDTO(Long idUsuario, String razao){
		this.idUsuario = idUsuario;
		this.razaoSocial = razao;
	}
	
	public UsuarioDTO(String razao){
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
	
	public String getIdsUsuarioRemoverLista() {
		return idsUsuarioRemoverLista;
	}
	
	public void setIdsUsuarioRemoverLista(String idsUsuarioRemoverLista) {
		this.idsUsuarioRemoverLista = idsUsuarioRemoverLista;
	}

	public Boolean getAprovacaoProduto() {
		return aprovacaoProduto;
	}

	public void setAprovacaoProduto(Boolean aprovacaoProduto) {
		this.aprovacaoProduto = aprovacaoProduto;
	}

	public Boolean getAprovacaoCusto() {
		return aprovacaoCusto;
	}

	public void setAprovacaoCusto(Boolean aprovacaoCusto) {
		this.aprovacaoCusto = aprovacaoCusto;
	}

	public Integer getValorAlcada() {
		return valorAlcada;
	}

	public void setValorAlcada(Integer valorAlcada) {
		this.valorAlcada = valorAlcada;
	}

	public Integer getAprCustoAlcada() {
		return this.getValorAlcada();
	}

	public void setAprCustoAlcada(Integer valorAlcada) {
		this.setValorAlcada(valorAlcada);
	}

	public String getIdsUsuarioParametrosSubordinados() {
		return idsUsuarioParametrosSubordinados;
	}

	public void setIdsUsuarioParametrosSubordinados(
			String idsUsuarioParametrosSubordinados) {
		this.idsUsuarioParametrosSubordinados = idsUsuarioParametrosSubordinados;
	}

	public Boolean getAprProdBoo() {
		return aprProdBoo;
	}

	public void setAprProdBoo(Boolean aprProdBoo) {
		this.aprProdBoo = aprProdBoo;
	}

	public Boolean getAprCustoBoo() {
		return aprCustoBoo;
	}

	public void setAprCustoBoo(Boolean aprCustoBoo) {
		this.aprCustoBoo = aprCustoBoo;
	}

	public Long getIdSuperior() {
		return idSuperior;
	}

	public void setIdSuperior(Long idSuperior) {
		this.idSuperior = idSuperior;
	}

	public Integer getAprProd() {
		return aprProd;
	}

	public void setAprProd(Integer aprProd) {
		this.aprProd = aprProd;
	}
}
