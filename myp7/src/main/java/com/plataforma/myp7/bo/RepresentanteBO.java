package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.isEmpty;
import static com.plataforma.myp7.util.Utils.setRetorno;
import static com.plataforma.myp7.util.Utils.toLike;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.plataforma.myp7.data.Fornecedor;
import com.plataforma.myp7.data.Representante;
import com.plataforma.myp7.data.RepresentanteFornecedor;
import com.plataforma.myp7.enums.Mensagem;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.exception.ManterEntidadeException;
import com.plataforma.myp7.mapper.RepresentanteFornecedorMapper;
import com.plataforma.myp7.mapper.RepresentanteMapper;

@Service
public class RepresentanteBO {
	
	@Autowired
	private RepresentanteMapper representanteMapper;
	
	@Autowired
	private RepresentanteFornecedorMapper representanteFornecedorMapper;
	
	public List<Representante> selecionaTodos(){
		return this.representanteMapper.obterTodosRepresentantes();
	}
	
	public List<Representante> obterPorParametro(Representante representante, Model model){
		representante.setApelido(toLike(representante.getApelido()));
		representante.setRazao(!isEmpty(representante.getRazao()) ? toLike(representante.getRazao()) : null );
		
		int count = isEmpty(representante.getRazao()) ? representanteMapper.countPorParametro(representante) : representanteMapper.countPorParametroMaisRazao(representante);
		
		if(count == 0){
			setRetorno(model, Mensagem.NENHUM_REGISTRO_LOCALIZADO);
			return new ArrayList<Representante>();
		}
		
//		if(count > ConfigEnum.LIMITE_COUNT.getValorInt()){
//			List<Representante> ret = new ArrayList<Representante>();
//			ret.add(new Representante(Mensagem.REFINE_SUA_PESQUISA));
//			return ret;
//		}
		
		if(isEmpty(representante.getRazao()))
			return representanteMapper.obterPorParametro(representante);
		return representanteMapper.obterPorParametroMaisRazao(representante);
	}

	public void update(Representante representante) throws ManterEntidadeException {
		try{
			this.representanteMapper.updateRepresentante(representante);
		}catch(Exception e){
			throw new ManterEntidadeException(MensagemWS.ATUALIZA_COMPRADOR_ERRO);
		}
	}

	public void insert(Representante representante) throws ManterEntidadeException {
		try{
			this.representanteMapper.insertRepresentante(representante);
		}catch(Exception e){
			throw new ManterEntidadeException(MensagemWS.INSERT_COMPRADOR_ERRO);
		}
	}

	public Representante selecionaPorId(Long idRepresentante) {
		try{
			if(Objects.isNull(idRepresentante))
				return new Representante();
			Representante representante = this.representanteMapper.obterPorId(idRepresentante);
			representante.setFornecedores(getListFornecedor(this.representanteFornecedorMapper.obterPorRepresentante(idRepresentante)));
			return representante;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void salvarRepresentante(Representante representante) throws Exception{
		if(Objects.isNull(representanteMapper.obterPorId(representante.getIdRepresentante()))){
			this.representanteMapper.insertRepresentante(representante);
		}else{
			this.representanteMapper.updateRepresentante(representante);
			this.representanteFornecedorMapper.deletePorRepresentante(representante.getIdRepresentante());
		}

		this.associaRepresentante(representante.getFornecedores(), representante.getIdRepresentante());
	}
	
	private void associaRepresentante(List<Fornecedor> lstFornecedor, Long  id) throws Exception{
		for(Fornecedor fn: lstFornecedor){
			RepresentanteFornecedor rpFornecedor = new RepresentanteFornecedor(fn,id);
			if(!Objects.isNull(rpFornecedor.getFornecedor().getIdFornecedor()))
				this.representanteFornecedorMapper.insertPorRepresentante(rpFornecedor);	
		}
	}
	
	private List<Fornecedor> getListFornecedor(List<RepresentanteFornecedor> lstRepresentanteFornecedor){
		List<Fornecedor> lstFornecedor= new ArrayList<Fornecedor>();
		for(RepresentanteFornecedor representante: lstRepresentanteFornecedor){
			lstFornecedor.add(representante.getFornecedor());
		}
		return lstFornecedor;
	}
}
