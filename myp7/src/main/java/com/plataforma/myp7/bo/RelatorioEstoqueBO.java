package com.plataforma.myp7.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.data.RelatorioEstoque;
import com.plataforma.myp7.interfaces.ComboPessoa;
import com.plataforma.myp7.mapper.RelatorioEstoqueMapper;

@Service
public class RelatorioEstoqueBO {

	@Autowired
	private RelatorioEstoqueMapper relatorioEstoqueMapper;
	
	public List<RelatorioEstoque> obterPorParametros(Produto produto, ComboPessoa pessoa){
		try{
			/* MOCK */
			List<RelatorioEstoque> lista = new ArrayList<>();
			RelatorioEstoque re = new RelatorioEstoque();
			re.setDiasEstoque		(15);
			re.setDiasUltimaEntrada	(12);
			re.setEmpresa			("Empresa do Robson");
			re.setMediaVendaDia		(32);
			re.setProduto			("Produto legal");
			re.setQtdEstoque		(221);
			re.setQtdEstoqueTroca	(35);
			re.setQtdPendenteCompras(12);
			re.setQtdPendenteExpedir(16);
			re.setQtdTransito		(9);
			lista.add(re);
			
			return lista;
			/* MOCK */
			
//			return this.relatorioEstoqueMapper.obterPorParametros(produto, pessoa);
		}catch(Exception e){
			return null;
		}
	}
}
