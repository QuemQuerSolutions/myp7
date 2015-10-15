package com.plataforma.myp7.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.plataforma.myp7.data.Representante;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.enums.Mensagem;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.exception.ManterEntidadeException;
import com.plataforma.myp7.mapper.RepresentanteMapper;

import static com.plataforma.myp7.util.Utils.*;

@Service
public class RepresentanteBO {
	
	@Autowired
	private RepresentanteMapper representanteMapper;
	
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
		
		if(count > ConfigEnum.LIMITE_COUNT.getValorInt()){
			List<Representante> ret = new ArrayList<Representante>();
			ret.add(new Representante(Mensagem.REFINE_SUA_PESQUISA));
			return ret;
		}
		
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
			return this.representanteMapper.obterPorId(idRepresentante);
		}catch(Exception e){
			return null;
		}
	}

	public void salvarRepresentante(Representante representante) throws Exception{
		if(Objects.isNull(representanteMapper.obterPorId(representante.getIdRepresentante()))){
			this.representanteMapper.insertRepresentante(representante);
		}else{
			this.representanteMapper.updateRepresentante(representante);
//			this.representanteFornecedorMapper.deletePorFornecedor(fornecedor.getIdFornecedor());
		}
	}
	
}
