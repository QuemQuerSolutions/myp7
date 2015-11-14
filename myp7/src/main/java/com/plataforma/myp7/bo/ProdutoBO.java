package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.cleanLike;
import static com.plataforma.myp7.util.Utils.setCodRetorno;
import static com.plataforma.myp7.util.Utils.setMsgRetorno;
import static com.plataforma.myp7.util.Utils.toLike;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.plataforma.myp7.data.NCM;
import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.dto.MensagemRetornoDTO;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.enums.Mensagem;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.enums.SituacaoEnum;
import com.plataforma.myp7.mapper.NcmMapper;
import com.plataforma.myp7.mapper.ProdutoMapper;
import com.plataforma.myp7.util.Upload;
import static com.plataforma.myp7.util.Utils.*;

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
				
				produto.setSituacao(isEmpty(produto.getSituacao()) ? "G" : produto.getSituacao());
				
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
			removeArquivo(session,imagemAnterior);
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
		
//		if(count > Integer.parseInt(ConfigEnum.LIMITE_COUNT.getValor())){
//			produto.setDesProduto(cleanLike(produto.getDesProduto()));
//			setMsgRetorno(model, "Refine sua pesquisa.");
//			setCodRetorno(model, -1);
//			return null;
//		}
//		
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
	
	public MensagemRetornoDTO salvarProduto(Produto produto) throws Exception{
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
	
	public List<Produto> obterParaAprovacao(Produto produto){
		produto.setDesProduto(toLike(produto.getDesProduto()));
		produto.setSituacao(SituacaoEnum.getSigla(produto.getSituacao()));
		
		int count = produtoMapper.countProdutoAprovacao(produto);
		
		if(count == 0)
			return new ArrayList<Produto>();
		
		if(count > ConfigEnum.LIMITE_COUNT.getValorInt()){
			List<Produto> ret = new ArrayList<Produto>();
			ret.add(new Produto(Mensagem.REFINE_SUA_PESQUISA));
			return ret;
		}
		List<Produto> lista = produtoMapper.obterProdutoAprovacao(produto);
		
		for(Produto p: lista)
			p.setDescSituacao(SituacaoEnum.getDescricao(p.getSituacao()));
			
		return lista;
	}
	
	public void aprovarProduto(Long idProduto, Usuario usuario){
		produtoMapper.updateStatus(SituacaoEnum.APROVADO.getSigla(), idProduto, usuario.getIdUsuario(), new Date());
	}
	
	public void reprovarProduto(Long idProduto){
		produtoMapper.updateStatus(SituacaoEnum.REPROVADO.getSigla(), idProduto, null,null);
	}

	public List<Produto> obterQtdPorSituacao(Long idUsuario) {
		return produtoMapper.qtdPorSituacao(idUsuario);
	}

}
