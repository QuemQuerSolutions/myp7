package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.emptyToNull;
import static com.plataforma.myp7.util.Utils.toLike;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.FornecedorCusto;
import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.enums.Mensagem;
import com.plataforma.myp7.enums.SituacaoEnum;
import com.plataforma.myp7.mapper.FornecedorCustoMapper;
import com.plataforma.myp7.util.Utils;

@Service
public class FornecedorCustoBO {

	@Autowired
	private FornecedorCustoMapper fornecedorCustoMapper;
	
	public List<FornecedorCusto> seleciona() {
		return this.fornecedorCustoMapper.obterTodos();
	}

	public FornecedorCusto atualizaManutencaoCusto(String id, String novoValor, String valorAtual) throws Exception {
		FornecedorCusto fc = new FornecedorCusto();
		
		fc.setIdTabCustoFornecedor(Integer.parseInt(id));
		fc.setValor(new BigDecimal(novoValor.replace(',', '.')));
		valorAtual = valorAtual.replace("\t", "");
		fc.setValorAnterior(new BigDecimal(valorAtual.replace(',', '.')));
		
		this.fornecedorCustoMapper.atualizarFornecedorCusto(fc);
		
		return fc;
	}

	public List<FornecedorCusto> selecionaComFiltro(String fornecedor, 
													String empresa, 
													String tipo, 
													String codigo, 
													String descricao){
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
		fc.setIdRepresentante(Integer.parseInt(fornecedor));
		fc.setIdEmpresa(Integer.parseInt(empresa));
		
		return this.fornecedorCustoMapper.obterComFiltro(fc);
	}

	public List<FornecedorCusto> obterQtdPorSituacao(Long idUsuario) {
		return this.fornecedorCustoMapper.qtdPorSituacao(idUsuario);
	}

	public List<FornecedorCusto> obterParaAprovacao(FornecedorCusto fornecedorCusto, String codigo, String tipo) {
		if(fornecedorCusto.getProduto() != null)
			fornecedorCusto.getProduto().setDesProduto(Utils.toLike(fornecedorCusto.getProduto().getDesProduto()));
		
		fornecedorCusto.setSituacao(SituacaoEnum.getSigla(fornecedorCusto.getSituacao()));
		
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
			fc.setSituacao(SituacaoEnum.getDescricao(fc.getSituacao()));
			
		return lista;
	}

	public void alterarSituacaoFornecedorCusto(Long[] idFornecedorCusto, SituacaoEnum situacaoEnum, Usuario usuarioAtualizacao) {
		Date hoje = new Date();
		Long idUsuario = usuarioAtualizacao.getIdUsuario();
		for(Long id : idFornecedorCusto)
			this.fornecedorCustoMapper.updateStatusFornecedorCusto(situacaoEnum.getSigla(), id, idUsuario, hoje);
	}
}
