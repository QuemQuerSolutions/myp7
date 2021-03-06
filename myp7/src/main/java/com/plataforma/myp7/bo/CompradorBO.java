package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.setRetorno;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.plataforma.myp7.data.Comprador;
import com.plataforma.myp7.data.Empresa;
import com.plataforma.myp7.data.Representante;
import com.plataforma.myp7.data.RepresentanteComprador;
import com.plataforma.myp7.enums.Mensagem;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.exception.ManterEntidadeException;
import com.plataforma.myp7.mapper.CompradorMapper;
import com.plataforma.myp7.mapper.EmpresaMapper;
import com.plataforma.myp7.mapper.RepresentanteCompradorMapper;
import com.plataforma.myp7.mapper.RepresentanteMapper;

@Service
public class CompradorBO {
	
	private final static Logger log = Logger.getLogger(CompradorBO.class);
	
	@Autowired
	private CompradorMapper compradorMapper;
	
	@Autowired
	private EmpresaMapper empresaMapper;
	
	@Autowired
	private RepresentanteMapper representanteMapper;
	
	@Autowired
	private RepresentanteCompradorMapper representanteCompradorMapper;
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	public Comprador obterPorId(Integer id){
		try {
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
		} catch (Exception e) {
			log.error("CompradorBO.obterPorId", e);
			return null;
		}
	}
	
	public List<Comprador> obterPorParametro(Model model, Comprador comp){
		try {
			final Comprador comprador = new Comprador(comp);
			
			int count = compradorMapper.count(comprador);
			
			if(count == 0){
				setRetorno(model, Mensagem.NENHUM_REGISTRO_LOCALIZADO);
				return null;
			}
			
			return compradorMapper.obterPorParametro(comprador);
		} catch (Exception e) {
			log.error("CompradorBO.obterPorParametro", e);
			return null;
		}
	}
	
	public void salvar(Comprador comprador){
		try {
			final Integer id = comprador.getId();
			comprador.setEdiCodigo(Objects.isNull(comprador.getEdiCodigo()) ? 0 : comprador.getEdiCodigo());
			
			if(Objects.isNull(id)){
				comprador.setId((int) comprador.getIdPessoa().longValue());
				this.inserir(comprador);
			}else{
				this.update(comprador);
				empresaMapper.deleteCompradorAlcada(id);
				representanteCompradorMapper.deletePorComprador(id);
			}
			
			this.usuarioBO.inactivateUsuario(comprador.getUsuario(), comprador.getStatus());
			
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
		} catch (Exception e) {
			log.error("CompradorBO.salvar", e);
		}
	}
	
	public void inserir(Comprador comprador) throws ManterEntidadeException{
		try{
			this.compradorMapper.inserirComprador(comprador);
		}catch(Exception e){
			log.error("CompradorBO.inserir", e);
			throw new ManterEntidadeException(MensagemWS.INSERT_COMPRADOR_ERRO);
		}
	}

	public void update(Comprador comprador) throws ManterEntidadeException{
		try{
			this.compradorMapper.updateComprador(comprador);
		}catch(Exception e){
			log.error("CompradorBO.update", e);
			throw new ManterEntidadeException(MensagemWS.ATUALIZA_COMPRADOR_ERRO);
		}
	}

	public Comprador obterPorIdUsuario(Long idUsuario) {
		try{
			return compradorMapper.obterPorIdUsuario(idUsuario);
		}catch(Exception e){
			log.error("CompradorBO.obterPorIdUsuario", e);
			return null;
		}
	}

	public List<Comprador> obterPorIdRepresentante(Representante repr) {
		try{
			List<RepresentanteComprador> listRc = representanteCompradorMapper.obterPorRepresentante(repr.getIdRepresentante());
			
			List<Comprador> listCompr = new ArrayList<>();
			
			for(RepresentanteComprador rc : listRc){
				listCompr.add(rc.getComprador());
			}
			
			return listCompr;
			
		}catch(Exception e){
			log.error("CompradorBO.obterPorIdRepresentante", e);
			return null;
		}
	}
}
