package com.plataforma.myp7.data;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.plataforma.myp7.enums.Mensagem;

@Alias("Produto")
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idProduto;
	private String desProduto;
	private String codIndustria;
	private Double pesoBruto;
	private Double pesoLiquido;
	private Double alturaProduto;
	private Double larguraProduto;
	private Double profunProduto;
	private Long qtdEmbalagem;
	private MultipartFile imagem;
	private String caminhoImagem;
	private String eanDunProduto;
	private NCM ncmProduto;
	private Embalagem embalagem; 
	private Usuario usuario;
	private int qtdPorSituacao;
	private String situacao;
	private String descSituacao;
	private Long idUsuario;
	
	//Mensagens
	private String msgRetorno;
	private Integer codRetorno;
	
	public Produto(){
		ncmProduto = new NCM();
		embalagem = new Embalagem();
	}
	
	public Produto(Mensagem msg) {
		this.msgRetorno = msg.getMensagem();
		this.codRetorno = msg.getCodigo();
	}

	public Produto(String desProduto){
		this();
		this.setDesProduto(desProduto);
	}
	
	public String getDescSituacao() {
		return descSituacao;
	}

	public void setDescSituacao(String descSituacao) {
		this.descSituacao = descSituacao;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getMsgRetorno() {
		return msgRetorno;
	}


	public Integer getCodRetorno() {
		return codRetorno;
	}


	public void setMsgRetorno(String msgRetorno) {
		this.msgRetorno = msgRetorno;
	}


	public void setCodRetorno(Integer codRetorno) {
		this.codRetorno = codRetorno;
	}


	public String getSituacao() {
		return situacao;
	}


	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}


	public int getQtdPorSituacao() {
		return qtdPorSituacao;
	}


	public void setQtdPorSituacao(int qtdPorSituacao) {
		this.qtdPorSituacao = qtdPorSituacao;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getIdProduto() {
		return idProduto;
	}
	
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	
	public String getDesProduto() {
		return desProduto;
	}
	
	public void setDesProduto(String desProduto) {
		this.desProduto = desProduto;
	}
	
	public String getCodIndustria() {
		return codIndustria;
	}
	
	public void setCodIndustria(String codIndustria) {
		this.codIndustria = codIndustria;
	}
	
	public Double getPesoBruto() {
		return pesoBruto;
	}
	
	public void setPesoBruto(Double pesoBruto) {
		this.pesoBruto = pesoBruto;
	}
	
	public void setPesoBrutoSt(String pesoBruto) {
		try{
			this.pesoBruto = new Double(pesoBruto.replace(".", "").replace(",", "."));
		}catch(Exception e){
			this.pesoBruto = null;
		}		
	}	
	
	public Double getPesoLiquido() {
		return pesoLiquido;
	}
	
	public void setPesoLiquido(Double pesoLiquido) {
		this.pesoLiquido = pesoLiquido;
	}
	
	public void setPesoLiquidoSt(String pesoLiquido) {
		try{
			this.pesoLiquido = new Double(pesoLiquido.replace(".", "").replace(",", "."));
		}catch(Exception e){
			this.pesoLiquido = null;
		}			
	}	
	
	public Double getAlturaProduto() {
		return alturaProduto;
	}
	
	public void setAlturaProduto(Double alturaProduto) {
		this.alturaProduto = alturaProduto;
	}
	
	public void setAlturaProdutoSt(String alturaProduto) {
		try{
			this.alturaProduto = new Double(alturaProduto.replace(".", "").replace(",", "."));
		}catch(Exception e){
			this.alturaProduto = null;
		}			
	}	
	
	public Double getLarguraProduto() {
		return larguraProduto;
	}
	
	public void setLarguraProduto(Double larguraProduto) {
		this.larguraProduto = larguraProduto;
	}
	
	public void setLarguraProdutoSt(String larguraProduto) {
		try{
			this.larguraProduto = new Double(larguraProduto.replace(".", "").replace(",", "."));
		}catch(Exception e){
			this.larguraProduto = null;
		}		
	}	
	
	public Double getProfunProduto() {
		return profunProduto;
	}
	
	public void setProfunProduto(Double profunProduto) {
		this.profunProduto = profunProduto;
	}
	
	public void setProfunProdutoSt(String profunProduto) {
		try{
			this.profunProduto = new Double(profunProduto.replace(".", "").replace(",", "."));
		}catch(Exception e){
			this.profunProduto = null;
		}
	}	
	
	public NCM getNcmProduto() {
		return ncmProduto;
	}
	
	public void setNcmProduto(NCM ncmProduto) {
		this.ncmProduto = ncmProduto;
	}
	
	public void setNcmProdutoST(String ncmProdutoST) {
		this.ncmProduto.setCodNcm(ncmProdutoST);
	}	
	
	public Embalagem getEmbalagem() {
		return embalagem;
	}
	
	public void setEmbalagem(Embalagem embalagem) {
		this.embalagem = embalagem;
	}
	
	public String getEanDunProduto() {
		return eanDunProduto;
	}

	public void setEanDunProduto(String eanDunProduto) {
		this.eanDunProduto = eanDunProduto;
	}
	
	public void setEmbalagemST(String embalagemST) {
		this.embalagem.setIdEmbalagem(Long.parseLong(embalagemST));
	}

	public Long getQtdEmbalagem() {
		return qtdEmbalagem;
	}

	public void setQtdEmbalagem(Long qtdEmbalagem) {
		this.qtdEmbalagem = qtdEmbalagem;
	}
	
	public void setQtdEmbalagem(String qtdEmbalagem) {
		try{
			this.qtdEmbalagem = Long.valueOf(qtdEmbalagem);
		}catch(Exception e){
			this.qtdEmbalagem = 0L;
		}
	}	
	
	public MultipartFile getImagem() {
		return imagem;
	}
	
	public void setImagem(MultipartFile imagem) {
		this.imagem = imagem;
	}
	
	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}
}
