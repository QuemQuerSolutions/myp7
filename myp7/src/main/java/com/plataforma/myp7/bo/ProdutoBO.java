package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.cleanLike;
import static com.plataforma.myp7.util.Utils.isEmpty;
import static com.plataforma.myp7.util.Utils.removeArquivo;
import static com.plataforma.myp7.util.Utils.setCodRetorno;
import static com.plataforma.myp7.util.Utils.setMsgRetorno;
import static com.plataforma.myp7.util.Utils.toLike;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.plataforma.myp7.data.NCM;
import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.dto.MensagemRetornoDTO;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.enums.SituacaoIntegracaoEnum;
import com.plataforma.myp7.mapper.NcmMapper;
import com.plataforma.myp7.mapper.ProdutoMapper;
import com.plataforma.myp7.util.Upload;

@Service
public class ProdutoBO {
	
	private final static Logger log = Logger.getLogger(ProdutoBO.class);
			
	@Autowired
	private ProdutoMapper produtoMapper;
	
	@Autowired
	private NcmMapper ncmMapper;
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	@Autowired
	private EmbalagemBO embalagemBO;
	
	public boolean salvar(Produto produto, HttpSession session, Model model, String imagemAnterior){
		try {
			if(this.isInsertValido(produto, model)){
				this.setCaminhoImagem(produto, session, imagemAnterior);
				produto.setUsuario(this.usuarioBO.getUserSession(session));
				produto.setNcmProduto(this.ncmMapper.obterNcmPorCodigo(produto.getNcmProduto()));
				
				produto.setSituacao(isEmpty(produto.getSituacao()) ? "G" : produto.getSituacao());

				if(Objects.isNull(produto.getIdProduto()))
					this.produtoMapper.salvarProduto(produto);
				else
					this.produtoMapper.atualizaProduto(produto);
				
				return true;
			}
			
			//se o retorno do insert nao for valido ele pega o caminho da ultima imagem
			produto.setCaminhoImagem(imagemAnterior);
			return false;
		} catch (Exception e) {
			setMsgRetorno(model, "Falha na operação.");
			setCodRetorno(model, -1);
			log.error("ProdutoBO.salvar", e);
			return false;
		}
	}

	private void setCaminhoImagem(Produto produto, HttpSession session,	String imagemAnterior) {
		try {
			Upload up = new Upload();
			String imagemAtual = up.armazenar(session, produto).getName();
			produto.setCaminhoImagem(imagemAtual.equals("") ? imagemAnterior: imagemAtual);
			
			//remove o arquivo se nao houver id e se a imagem nao for a mesma.
			if(!Objects.isNull(produto.getIdProduto()) && imagemAnterior!= null && !imagemAnterior.equalsIgnoreCase(produto.getCaminhoImagem())) 
				removeArquivo(session, imagemAnterior);
		} catch (Exception e) {
			log.error("ProdutoBO.setCaminhoImagem", e);
		}
	}

	public boolean isInsertValido(Produto produto, Model model) {
		if(Objects.isNull(produto.getNcmProduto())){
			setMsgRetorno(model, "NCM não encontrado.");
			setCodRetorno(model, -1);
			return false;
		}
		return true;
	} 
	
	public List<Produto> obterProdutos(Produto produto, Model model){
		try {
			produto.setDesProduto(toLike(produto.getDesProduto()));
			Integer count = this.produtoMapper.countProduto(produto);
			
			if(count == 0){
				produto.setDesProduto(cleanLike(produto.getDesProduto()));
				setMsgRetorno(model, "Nenhum registro localizado.");
				setCodRetorno(model, -1);
				return null;
			}
			
			List<Produto> lista = produtoMapper.obterProdutos(produto);
			produto.setDesProduto(cleanLike(produto.getDesProduto()));
			return 	lista;
		} catch (Exception e) {
			setMsgRetorno(model, "Falha na operação.");
			setCodRetorno(model, -1);
			log.error("ProdutoBO.obterProdutos", e);
			return null;
		}
	}
	
	public Produto obterPorId(Long id){
		try {
			Produto produto = produtoMapper.obterProdutoPorId(id);
			NCM ncm = this.ncmMapper.obterNcmPorId(produto.getNcmProduto().getIdNcm());
			produto.setNcmProduto(ncm);
			return produto;
		} catch (Exception e) {
			log.error("ProdutoBO.obterPorId", e);
			return null;
		}
	}
	
	public List<Produto> consultaProdutoService(Produto produto) throws Exception{
		try {
			produto.setDesProduto(toLike(produto.getDesProduto()));
			List<Produto> lstProduto = produtoMapper.consultaProdutoService(produto);
			produto.setDesProduto(cleanLike(produto.getDesProduto()));
			return lstProduto;
		} catch (Exception e) {
			log.error("ProdutoBO.consultaProdutoService", e);
			return null;
		}
	}
	
	public MensagemRetornoDTO salvarProduto(Produto produto) throws Exception{
		try {
			if(this.isValidoService(produto)){
				if (produto.getIdProduto()==0L){
					this.produtoMapper.salvarProduto(produto);
					return MensagemWS.getMensagem(MensagemWS.INSERT_PROD_SUCESSO);
				}else{
					this.produtoMapper.atualizaProduto(produto);
					return MensagemWS.getMensagem(MensagemWS.ATUALIZA_PROD_SUCESSO);
				}
			}else{
				return MensagemWS.getMensagem(MensagemWS.CONSULTA_EMB_NCM_VAZIO);
			}
		} catch (Exception e) {
			log.error("ProdutoBO.salvarProduto", e);
			return null;
		}
	}
	
	public boolean isValidoService(Produto produto){
		try {
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
		} catch (Exception e) {
			log.error("ProdutoBO.isValidoService", e);
			return false;
		}
	}
	
	public List<Produto> obterParaAprovacao(Produto produto){
		try {
			produto.setDesProduto(toLike(produto.getDesProduto()));
			produto.setSituacao(SituacaoIntegracaoEnum.getSigla(produto.getSituacao()));
			
			int count = produtoMapper.countProdutoAprovacao(produto);
			
			if(count == 0)
				return new ArrayList<Produto>();
			
			List<Produto> lista = produtoMapper.obterProdutoAprovacao(produto);
			
			for(Produto p: lista)
				p.setDescSituacao(SituacaoIntegracaoEnum.getDescricao(p.getSituacao()));
				
			return lista;
		} catch (Exception e) {
			log.error("ProdutoBO.obterParaAprovacao", e);
			return null;
		}
	}
	
	public void aprovarProduto(Long idProduto, Usuario usuario){
		try {
			produtoMapper.updateStatus(SituacaoIntegracaoEnum.APROVADO.getSigla(), idProduto, usuario.getIdUsuario(), new Date());
		} catch (Exception e) {
			log.error("ProdutoBO.aprovarProduto", e);
		}
	}
	
	public void reprovarProduto(Long idProduto){
		try {
			produtoMapper.updateStatus(SituacaoIntegracaoEnum.REPROVADO.getSigla(), idProduto, null,null);
		} catch (Exception e) {
			log.error("ProdutoBO.reprovarProduto", e);
		}
	}

	public List<Produto> obterQtdPorSituacao(Long idUsuario) {
		try {
			return produtoMapper.qtdPorSituacao(idUsuario);
		} catch (Exception e) {
			log.error("ProdutoBO.obterQtdPorSituacao", e);
			return null;
		}
	}

}
