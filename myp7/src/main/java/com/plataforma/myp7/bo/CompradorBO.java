package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.setRetorno;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.plataforma.myp7.data.Comprador;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.enums.Mensagem;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.exception.ManterEntidadeException;
import com.plataforma.myp7.mapper.CompradorMapper;

@Service
public class CompradorBO {
	
	@Autowired
	private CompradorMapper compradorMapper;
	
	public Comprador obterPorId(Integer id){
		if(Objects.isNull(id)) 
			return new Comprador();
		return compradorMapper.obterPorId(id);
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
