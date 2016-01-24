package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.emptyToNull;
import static com.plataforma.myp7.util.Utils.toLike;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.Fornecedor;
import com.plataforma.myp7.data.FornecedorCusto;
import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.data.Representante;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.enums.Mensagem;
import com.plataforma.myp7.enums.SituacaoIntegracaoEnum;
import com.plataforma.myp7.mapper.FornecedorCustoMapper;
import com.plataforma.myp7.util.Utils;

@Service
public class FornecedorCustoBO {
	
	private final static Logger log = Logger.getLogger(FornecedorCustoBO.class);
	
	@Autowired
	private FornecedorCustoMapper fornecedorCustoMapper;
	
	@Autowired
	private FornecedorBO fornecedorBO;
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	public List<FornecedorCusto> seleciona() {
		return this.fornecedorCustoMapper.obterTodos();
	}

	public FornecedorCusto atualizaManutencaoCusto(String id, String novoValor, String valorAtual) throws Exception {
		try {
			FornecedorCusto fc = new FornecedorCusto();
			
			fc.setIdTabCustoFornecedor(Integer.parseInt(id));
			fc.setValor(new BigDecimal(novoValor.replace(',', '.')));
			valorAtual = valorAtual.replace("\t", "");
			fc.setValorAnterior(new BigDecimal(valorAtual.replace(',', '.')));
			
			this.fornecedorCustoMapper.atualizarFornecedorCusto(fc);
			return fc;
		} catch (Exception e) {
			log.error("FornecedorCustoBO.atualizaManutencaoCusto", e);
			return null;
		}
	}

	public List<FornecedorCusto> selecionaComFiltro(String fornecedor, 
													String empresa, 
													String tipo, 
													String codigo, 
													String descricao){
		try {
			FornecedorCusto fc = new FornecedorCusto();
			Produto prodt = new Produto();

			prodt.setDesProduto(toLike(emptyToNull(descricao)));
			
			codigo = codigo.trim();
			
			if(!codigo.trim().equals("")){
				if(tipo.equalsIgnoreCase("1"))
					prodt.setIdProduto(Long.parseLong(codigo));
				else
					prodt.setEanDunProduto(codigo);			
			}
			
			fc.setProduto(prodt);
			fc.setIdRepresentante(getRepresentanteFornecedorCusto(fornecedorBO.obterPorId(Long.parseLong(fornecedor))));
			fc.setIdEmpresa(Integer.parseInt(empresa));
			
			return this.fornecedorCustoMapper.obterComFiltro(fc);
		} catch (Exception e) {
			log.error("FornecedorCustoBO.selecionaComFiltro", e);
			return null;
		}
	}

	public List<FornecedorCusto> obterQtdPorSituacao(Long idUsuario) {
		try {
			return this.fornecedorCustoMapper.qtdPorSituacao(idUsuario);
		} catch (Exception e) {
			log.error("FornecedorCustoBO.obterQtdPorSituacao", e);
			return null;
		}
	}

	private Long getRepresentanteFornecedorCusto(Fornecedor fornecedor){
		try {
			List<FornecedorCusto> lstFornecCusto = new ArrayList<FornecedorCusto>();
			for(Representante rep: fornecedor.getRepresentantes()){
				lstFornecCusto = fornecedorBO.obterCustoAprovacaoPorRepresentante(rep.getIdRepresentante());
				for(FornecedorCusto fornecCusto: lstFornecCusto){
					if(fornecCusto.getFornecedor().getIdFornecedor().equals(fornecedor.getIdFornecedor())
							&& rep.getIdRepresentante().equals(fornecCusto.getIdRepresentante()))
						return fornecCusto.getIdRepresentante();
				}
			}
			
			return null;
		} catch (Exception e) {
			log.error("FornecedorCustoBO.getRepresentanteFornecedorCusto", e);
			return null;
		}
	}
	public List<FornecedorCusto> obterParaAprovacao(FornecedorCusto fornecedorCusto, String codigo, String tipo, HttpSession session) {
		try {
			fornecedorCusto.setUsuAprovacao(this.usuarioBO.getUserSession(session));
			if(fornecedorCusto.getProduto() != null)
				fornecedorCusto.getProduto().setDesProduto(Utils.toLike(fornecedorCusto.getProduto().getDesProduto()));
			fornecedorCusto.setSituacao(SituacaoIntegracaoEnum.getSigla(fornecedorCusto.getSituacao()));
			if(!codigo.trim().equals("")){
				if(tipo.equalsIgnoreCase("1"))
					fornecedorCusto.getProduto().setIdProduto(Long.parseLong(codigo));
				else
					fornecedorCusto.getProduto().setEanDunProduto(codigo);			
			}
			
			
			int count = fornecedorCustoMapper.countFornecedorCustoAprovacao(fornecedorCusto);
			
			if(count == 0)
				return new ArrayList<FornecedorCusto>();
			
			if(count > ConfigEnum.LIMITE_COUNT.getValorInt()){
				List<FornecedorCusto> ret = new ArrayList<FornecedorCusto>();
				ret.add(new FornecedorCusto(Mensagem.REFINE_SUA_PESQUISA));
				return ret;
			}
			
			List<FornecedorCusto> lista = fornecedorCustoMapper.obterFornecedorCustoAprovacao(fornecedorCusto);
			
			for(FornecedorCusto fc: lista)
				fc.setSituacao(SituacaoIntegracaoEnum.getDescricao(fc.getSituacao()));
				
			return lista;
		} catch (Exception e) {
			log.error("FornecedorCustoBO.obterParaAprovacao", e);
			return null;
		}
	}

	public void alterarSituacaoFornecedorCusto(Long[] idFornecedorCusto, SituacaoIntegracaoEnum situacaoEnum, Usuario usuarioAtualizacao) {
		try {
			Date hoje = new Date();
			Long idUsuario = usuarioAtualizacao.getIdUsuario();
			for(Long id : idFornecedorCusto)
				this.fornecedorCustoMapper.updateStatusFornecedorCusto(situacaoEnum.getSigla(), id, idUsuario, hoje);
		} catch (Exception e) {
			log.error("FornecedorCustoBO.alterarSituacaoFornecedorCusto", e);
		}
	}
	
//	public AprovaDTO isCompradorAprovaCusto(Long[] diferenca,Long[] idFornecedorCusto, HttpSession session){
//		Usuario usuario = this.usuarioBO.getUserSession(session);
//		AprovaDTO ap = new AprovaDTO();
//		List<Long> lstLong = new ArrayList<Long>();
//		for(Long dif: diferenca){
//			for(Long fornCusto: idFornecedorCusto){
//				if(usuario.getAlcada() >= dif){
//					lstLong.add(fornCusto);
//					break;
//				}
//			}
//		}
//		ap.setIdFornecedorCustoAprova(lstLong);
//		ap.setValor(usuario.getAlcada());
//		return ap;
//	}
	
}
