package com.plataforma.myp7.data;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("NCM")
public class NCM implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idNcm;
	private String codNcm;
	private String descNcm;
	
	public Long getIdNcm() {
		return idNcm;
	}
	public void setIdNcm(Long idNcm) {
		this.idNcm = idNcm;
	}
	public String getCodNcm() {
		return codNcm;
	}
	public void setCodNcm(String codNcm) {
		this.codNcm = codNcm;
	}
	public String getDescNcm() {
		return descNcm;
	}
	public void setDescNcm(String descNcm) {
		this.descNcm = descNcm;
	}
	
	
}
