package com.plataforma.myp7.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.Empresa;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.exception.ManterEntidadeException;
import com.plataforma.myp7.mapper.EmpresaMapper;

@Service
public class EmpresaBO {
	
	@Autowired
	private EmpresaMapper empresaMapper;
	
	public List<Empresa> selecionaTodos(){
		return this.empresaMapper.obterTodasEmpresas();
	}
	
	public List<Empresa> selecionaPorUF(String uf){
		return this.empresaMapper.obterEmpresasPorUF(uf);
	}
	
	public Empresa selecionaPorId(Long id){
		return this.empresaMapper.obterEmpresaPorId(id);
	}

	public void insert(Empresa empresa) throws ManterEntidadeException {
		try{
			this.empresaMapper.inserirEmpresa(empresa);
		}catch(Exception e){
			throw new ManterEntidadeException(MensagemWS.INSERT_EMPRESA_ERRO);
		}
		
	}

	public void update(Empresa empresa) throws ManterEntidadeException {
		try{
			this.empresaMapper.updateEmpresa(empresa);
		}catch(Exception e){
			throw new ManterEntidadeException(MensagemWS.ATUALIZA_EMPRESA_ERRO);
		}
	}	
}
