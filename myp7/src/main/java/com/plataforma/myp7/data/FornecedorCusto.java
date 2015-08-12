package com.plataforma.myp7.data;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

@Alias("FornecedorCusto")
public class FornecedorCusto {
	private Integer idTabCustoFornecedor;
	private Fornecedor fornecedor;
	private Integer idEmpresa;
	private Produto produto;
	private BigDecimal valorAnterior;
	private BigDecimal valor;
	private Integer idRepresentante;
	
	public Integer getIdTabCustoFornecedor() {
		return idTabCustoFornecedor;
	}
	public void setIdTabCustoFornecedor(Integer idTabCustoFornecedor) {
		this.idTabCustoFornecedor = idTabCustoFornecedor;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public String getValorAnteriorFormatado() {
		try{
			String valorS = valorAnterior.toString();
			if(valorS.split("\\.").length > 1 && valorS.split("\\.")[1] != null)
				if(valorS.split("\\.")[1].length() >= 2)
					valorS = valorS.split("\\.")[0] + "," + valorS.split("\\.")[1].subSequence(0,2);
				else
					valorS = valorS.split("\\.")[0] + "," + valorS.split("\\.")[1] + "0";
			else
				valorS = valorS.split("\\.")[0] + ",00";
			return valorS;
		}catch(Exception e){
			return "0,00";
		}
	}
	public String getValorFormatado() {
		try{
			String valorS = valor.toString();
			if(valorS.split("\\.").length > 1 && valorS.split("\\.")[1] != null)
				if(valorS.split("\\.")[1].length() >= 2)
					valorS = valorS.split("\\.")[0] + "," + valorS.split("\\.")[1].subSequence(0,2);
				else
					valorS = valorS.split("\\.")[0] + "," + valorS.split("\\.")[1] + "0";
			else
				valorS = valorS.split("\\.")[0] + ",00";
			return valorS;
		}catch(Exception e){
			return "0,00";
		}
	}	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Integer getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	public BigDecimal getValorAnterior() {
		return valorAnterior;
	}
	public void setValorAnterior(BigDecimal valorAnterior) {
		this.valorAnterior = valorAnterior;
	}
}
