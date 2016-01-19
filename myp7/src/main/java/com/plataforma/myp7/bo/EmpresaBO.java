package com.plataforma.myp7.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.Empresa;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.enums.Mensagem;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.exception.ManterEntidadeException;
import com.plataforma.myp7.mapper.EmpresaMapper;

import static com.plataforma.myp7.util.Utils.*;

@Service
public class EmpresaBO {
	
	private final static Logger log = Logger.getLogger(EmpresaBO.class);
	
	@Autowired
	private EmpresaMapper empresaMapper;
	
	public List<Empresa> selecionaTodos(){
		try {
			return this.empresaMapper.obterTodasEmpresas();
		} catch (Exception e) {
			log.error("EmpresaBO.selecionaTodos", e);
			return null;
		}
	}
	
	public List<Empresa> selecionaPorUF(String uf){
		try {
			return this.empresaMapper.obterEmpresasPorUF(uf);
		} catch (Exception e) {
			log.error("EmpresaBO.selecionaPorUF", e);
			return null;
		}
	}
	
	public Empresa selecionaPorId(Long id){
		try {
			return this.empresaMapper.obterEmpresaPorId(id);
		} catch (Exception e) {
			log.error("EmpresaBO.selecionaPorId", e);
			return null;
		}
	}
	
	public List<Empresa> obterPorParametro(Empresa empresa){
		try {
			empresa.setNomeReduzido(toLike(empresa.getNomeReduzido()));
			
			int count = empresaMapper.countPorParametro(empresa);
			
			if(count == 0)
				return new ArrayList<Empresa>();
			
			if(count > ConfigEnum.LIMITE_COUNT.getValorInt()){
				List<Empresa> ret = new ArrayList<Empresa>();
				ret.add(new Empresa(Mensagem.REFINE_SUA_PESQUISA));
				return ret;
			}
			
			return empresaMapper.obterPorParametro(empresa);
		} catch (Exception e) {
			log.error("EmpresaBO.obterPorParametro", e);
			return null;
		}
	}

	public void insert(Empresa empresa) throws ManterEntidadeException {
		try{
			this.empresaMapper.inserirEmpresa(empresa);
		}catch(Exception e){
			log.error("EmpresaBO.insert", e);
			throw new ManterEntidadeException(MensagemWS.INSERT_EMPRESA_ERRO);
		}
		
	}

	public void update(Empresa empresa) throws ManterEntidadeException {
		try{
			this.empresaMapper.updateEmpresa(empresa);
		}catch(Exception e){
			log.error("EmpresaBO.update", e);
			throw new ManterEntidadeException(MensagemWS.ATUALIZA_EMPRESA_ERRO);
		}
	}	
}
