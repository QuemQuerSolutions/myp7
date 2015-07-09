package com.plataforma.myp7.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.NCM;
import com.plataforma.myp7.mapper.NcmMapper;

@Service
public class NcmBO {

	@Autowired
	private NcmMapper ncmMapper;
	
	public NCM selecionaPorCodigo(NCM ncmProduto){
		return ncmMapper.obterNcmPorCodigo(ncmProduto);
	}
}
