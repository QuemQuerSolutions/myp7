package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.cleanLike;
import static com.plataforma.myp7.util.Utils.setCodRetorno;
import static com.plataforma.myp7.util.Utils.setMsgRetorno;
import static com.plataforma.myp7.util.Utils.toLike;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.plataforma.myp7.data.NCM;
import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.mapper.NcmMapper;
import com.plataforma.myp7.mapper.ProdutoMapper;
import com.plataforma.myp7.util.MensagemRetorno;
import com.plataforma.myp7.util.Upload;
import com.plataforma.myp7.util.Utils;

@Service
public class ProdutoBO {
	
	@Autowired
	private ProdutoMapper produtoMapper;
	
	@Autowired
	private NcmMapper ncmMapper;
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	@Autowired
	private EmbalagemBO embalagemBO;
	
	public boolean salvar(Produto produto, HttpSession session, Model model, String imagemAnterior) throws Exception {
		if(this.isInsertValido(produto, model)){
			try{
				this.setCaminhoImagem(produto, session, imagemAnterior);
				produto.setUsuario(this.usuarioBO.getUserSession(session));
				produto.setNcmProduto(this.ncmMapper.obterNcmPorCodigo(produto.getNcmProduto()));
				
				if(Objects.isNull(produto.getIdProduto()))
					this.produtoMapper.salvarProduto(produto);
				else
					this.produtoMapper.atualizaProduto(produto);
				
				return true;
			}catch(Exception e){ 
				e.printStackTrace();
			}
		}
		
		//se o retorno do insert não for valido ele pega o caminho da ultima imagem
		produto.setCaminhoImagem(imagemAnterior);
		return false;
	}

	private void setCaminhoImagem(Produto produto, HttpSession session,	String imagemAnterior) {
		Upload up = new Upload();
		String imagemAtual = up.armazenar(session, produto).getName();
		produto.setCaminhoImagem(imagemAtual.equals("")?imagemAnterior:imagemAtual);
		
		//remove o arquivo se nao houver id e se a imagem nao for a mesma.
		if(!Objects.isNull(produto.getIdProduto()) && !produto.getCaminhoImagem().equalsIgnoreCase(imagemAnterior)) 
			Utils.removeArquivo(session,imagemAnterior);
	}

	public boolean isInsertValido(Produto produto, Model model) {
		if(Objects.isNull(produto.getNcmProduto())){
			setMsgRetorno(model, "NCM não encontrado.");
			setCodRetorno(model, -1);
			return false;
		}
		return true;
	} 
	
	public List<Produto> obterProdutos(Produto produto, Model model) throws Exception{
		produto.setDesProduto(toLike(produto.getDesProduto()));
		Integer count = this.produtoMapper.countProduto(produto);
		
		if(count == 0){
			produto.setDesProduto(cleanLike(produto.getDesProduto()));
			setMsgRetorno(model, "Nenhum registro localizado.");
			setCodRetorno(model, -1);
			return null;
		}
		
		if(count > Integer.parseInt(ConfigEnum.LIMITE_COUNT.getValor())){
			produto.setDesProduto(cleanLike(produto.getDesProduto()));
			Utils.setMsgRetorno(model, "Refine sua pesquisa.");
			Utils.setCodRetorno(model, -1);
			return null;
		}
		
		List<Produto> lista = produtoMapper.obterProdutos(produto);
		produto.setDesProduto(cleanLike(produto.getDesProduto()));
		return 	lista;
	}
	
	public Produto obterPorId(Long id){
		Produto produto = produtoMapper.obterProdutoPorId(id);
		NCM ncm = this.ncmMapper.obterNcmPorId(produto.getNcmProduto().getIdNcm());
		produto.setNcmProduto(ncm);
		return produto;
	}
	
	public List<Produto> consultaProdutoService(Produto produto) throws Exception{
		produto.setDesProduto(toLike(produto.getDesProduto()));
		List<Produto> lstProduto = produtoMapper.consultaProdutoService(produto);
		produto.setDesProduto(cleanLike(produto.getDesProduto()));
		return lstProduto;
	}

	public MensagemRetorno inserirProdutoService(Produto produto) throws Exception{
		if(this.isValidoService(produto)){
			this.produtoMapper.salvarProduto(produto);
			return Utils.formataMsgem(3);
		}else{
			return Utils.formataMsgem(8);
		}
	}
	
	public MensagemRetorno editarProdutoService(Produto produto) throws Exception{
		if(this.isValidoService(produto)){
			this.produtoMapper.atualizaProduto(produto);
			return Utils.formataMsgem(9);
		}else{
			return Utils.formataMsgem(8);
		}
	}
	
	public boolean isValidoService(Produto produto){
		if(!Objects.isNull(produto.getUsuario())
		   && !Objects.isNull(usuarioBO.obterPorId(produto.getUsuario().getIdUsuario()))
		   && !Objects.isNull(produto.getNcmProduto()) 
		   && !Objects.isNull(this.ncmMapper.obterNcmPorId(produto.getNcmProduto().getIdNcm())) 
		   && !Objects.isNull(produto.getEmbalagem().getIdEmbalagem()) 
		   && !Objects.isNull(this.embalagemBO.obterEmbalagemPorId(produto.getEmbalagem().getIdEmbalagem()))){
			return true;
		}else{
			return false;
		}
	}
}
