package com.plataforma.myp7.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.Empresa;
import com.plataforma.myp7.mapper.EmpresaMapper;

@Service
public class EmpresaBO {
	
	@Autowired
	private EmpresaMapper empresaMapper;
	
	public List<Empresa> selecionaTodos(){
		return this.empresaMapper.obterTodasEmpresas();
	}
}
