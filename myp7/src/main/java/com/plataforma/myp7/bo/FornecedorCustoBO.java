package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.toLike;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.FornecedorCusto;
import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.mapper.FornecedorCustoMapper;
import com.plataforma.myp7.util.Utils;

@Service
public class FornecedorCustoBO {

	@Autowired
	private FornecedorCustoMapper fornecedorCustoMapper;
	
	public List<FornecedorCusto> seleciona() {
		return this.fornecedorCustoMapper.obterTodos();
	}

	public void atualizaManutencaoCusto(String id, String novoValor) {
		try{
			FornecedorCusto fc = new FornecedorCusto();
			
			fc.setIdTabCustoFornecedor(Integer.parseInt(id));
			fc.setValor(new BigDecimal(novoValor.replace(',', '.')));
			
			this.fornecedorCustoMapper.atualizarFornecedorCusto(fc);
		}catch(Exception e){ 
			e.printStackTrace();
		}
	}

	public List<FornecedorCusto> selecionaComFiltro(String fornecedor, 
													String empresa, 
													String tipo, 
													String codigo, 
													String descricao){
		FornecedorCusto fc = new FornecedorCusto();
		Produto prodt = new Produto();

		prodt.setDesProduto(toLike(Utils.emptyToNull(descricao)));
		
		codigo = codigo.trim();
		
		if(!codigo.equals("")){
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

}
