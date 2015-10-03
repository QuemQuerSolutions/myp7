package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.plataforma.myp7.data.Comprador;
import com.plataforma.myp7.data.Empresa;
import com.plataforma.myp7.data.Representante;
import com.plataforma.myp7.data.RepresentanteComprador;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.enums.Mensagem;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.exception.ManterEntidadeException;
import com.plataforma.myp7.mapper.CompradorMapper;
import com.plataforma.myp7.mapper.EmpresaMapper;
import com.plataforma.myp7.mapper.RepresentanteCompradorMapper;
import com.plataforma.myp7.mapper.RepresentanteMapper;

@Service
public class CompradorBO {
	
	@Autowired
	private CompradorMapper compradorMapper;
	
	@Autowired
	private EmpresaMapper empresaMapper;
	
	@Autowired
	private RepresentanteMapper representanteMapper;
	
	@Autowired
	private RepresentanteCompradorMapper representanteCompradorMapper;
	
	public Comprador obterPorId(Integer id){
		if(Objects.isNull(id)) 
			return new Comprador();
		
		Comprador comprador = compradorMapper.obterPorId(id);
		comprador.setEmpresa(empresaMapper.obterPorComprador(id));
		List<Representante> lstRepresentante = new ArrayList<Representante>();
		List<RepresentanteComprador> lstRepresentanteComprador = representanteCompradorMapper.obterPorComprador(id);
		
		for(RepresentanteComprador rc: lstRepresentanteComprador)
			lstRepresentante.add(rc.getRepresentante());
		
		comprador.setRepresentantes(lstRepresentante);
		
		return comprador;
	}
	
	public List<Comprador> obterPorParametro(Model model, Comprador comp){
		final Comprador comprador = new Comprador(comp);
		
		int count = compradorMapper.count(comprador);
		
		if(count == 0){
			setRetorno(model, Mensagem.NENHUM_REGISTRO_LOCALIZADO);
			return null;
		}
		
		if(count > ConfigEnum.LIMITE_COUNT.getValorInt()){
			setRetorno(model, Mensagem.REFINE_SUA_PESQUISA);
			return null;
		}
		
		return compradorMapper.obterPorParametro(comprador);
	}
	
	public void salvar(Comprador comprador){
		final Integer id = comprador.getId();
		
		if(Objects.isNull(id)){
			this.inserir(comprador);
		}else{
			this.update(comprador);
			empresaMapper.deleteCompradorAlcada(id);
			representanteCompradorMapper.deletePorComprador(id);
		}
		
		//Associa as empresas ao comprador
		for(Empresa empresa: comprador.getEmpresa()){
			empresa.setIdCompradorAlcada(id);
			empresaMapper.inserCompradorAlcada(empresa);
		}
		//Associa os representantes ao comprador
		for(Representante representante: comprador.getRepresentantes()){
			RepresentanteComprador rc = new RepresentanteComprador(representante, id);
			representanteCompradorMapper.insert(rc);
		}
	}
	
	public void inserir(Comprador comprador) throws ManterEntidadeException{
		try{
			this.compradorMapper.inserirComprador(comprador);
		}catch(Exception e){
			throw new ManterEntidadeException(MensagemWS.INSERT_COMPRADOR_ERRO);
		}
	}

	public void update(Comprador comprador) throws ManterEntidadeException{
		try{
			this.compradorMapper.updateComprador(comprador);
		}catch(Exception e){
			throw new ManterEntidadeException(MensagemWS.ATUALIZA_COMPRADOR_ERRO);
		}
	}
}
