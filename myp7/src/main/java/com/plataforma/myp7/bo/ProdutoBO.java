package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.setCodRetorno;
import static com.plataforma.myp7.util.Utils.setMsgRetorno;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.plataforma.myp7.dao.NcmDAO;
import com.plataforma.myp7.dao.ProdutoDAO;
import com.plataforma.myp7.data.NCM;
import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.enums.GeralEnum;
import com.plataforma.myp7.util.Upload;
import com.plataforma.myp7.util.Utils;

public class ProdutoBO {
	
	private ProdutoDAO produtoDAO;
	private NcmDAO ncmDAO;
	public ProdutoBO(){
		produtoDAO = new ProdutoDAO();
		ncmDAO = new NcmDAO();
	}
	
	public boolean salvar(Produto produto, HttpSession session, Model model, String imagemAnterior) throws Exception {
		if(this.isInsertValido(produto, model)){
			try{
				Upload up = new Upload();
				produto.setCaminhoImagem(up.armazenar(session, produto).getName().equals("")?imagemAnterior:up.armazenar(session, produto).getName());
				
				//remove o arquivo se nao houver id e se a imagem nao for a mesma.
				if(!Objects.isNull(produto.getIdProduto()) && !produto.getCaminhoImagem().equalsIgnoreCase(imagemAnterior)) 
					Utils.removeArquivo(session,imagemAnterior);
				this.produtoDAO.salvar(produto);
				return true;
			}catch(Exception e){ 
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		return false;
	}

	private boolean isInsertValido(Produto produto, Model model) {
		if(Objects.isNull(produto.getNcmProduto())){
			setMsgRetorno(model, "NCM n�o encontrado.");
			setCodRetorno(model, -1);
			return false;
		}
		return true;
	} 
	
	public List<Produto> obterProdutos(Produto produto, Model model) throws Exception{
		
		Integer count = this.produtoDAO.count(produto);
		
		if(count == 0){
			setMsgRetorno(model, "Nenhum registro localizado.");
			setCodRetorno(model, -1);
			return null;
		}
		
		if(count > Integer.parseInt(GeralEnum.LIMITE_COUNT.getValor())){
			Utils.setMsgRetorno(model, "Refine sua pesquisa.");
			Utils.setCodRetorno(model, -1);
			return null;
		}
		
		return produtoDAO.obterProdutos(produto);	
	}
	
	public Produto obterPorId(Long id){
		Produto produto = produtoDAO.obterPorId(id);
		NCM ncm = this.ncmDAO.obterNCMPorId(produto.getNcmProduto().getIdNcm());
		produto.setNcmProduto(ncm);
		return produto;
	}
}
