package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.isEmpty;
import static com.plataforma.myp7.util.Utils.setCodRetorno;
import static com.plataforma.myp7.util.Utils.setMsgRetorno;
import static com.plataforma.myp7.util.Utils.setRetorno;
import static com.plataforma.myp7.util.Utils.toLike;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.plataforma.myp7.data.Comprador;
import com.plataforma.myp7.data.Fornecedor;
import com.plataforma.myp7.data.FornecedorCusto;
import com.plataforma.myp7.data.Representante;
import com.plataforma.myp7.data.RepresentanteComprador;
import com.plataforma.myp7.data.RepresentanteFornecedor;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.enums.Mensagem;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.exception.ManterEntidadeException;
import com.plataforma.myp7.mapper.FornecedorCustoMapper;
import com.plataforma.myp7.mapper.FornecedorMapper;
import com.plataforma.myp7.mapper.RepresentanteCompradorMapper;
import com.plataforma.myp7.mapper.RepresentanteFornecedorMapper;
import com.plataforma.myp7.mapper.RepresentanteMapper;

@Service
public class RepresentanteBO {
	
	private final static Logger log = Logger.getLogger(RepresentanteBO.class);
			
	@Autowired
	private RepresentanteMapper representanteMapper;
	
	@Autowired
	private RepresentanteFornecedorMapper representanteFornecedorMapper;
	
	@Autowired
	private RepresentanteCompradorMapper representanteCompradorMapper;
	
	@Autowired
	private FornecedorCustoMapper fornecedorCustoMapper;
	
	@Autowired
	private FornecedorMapper fornecedorMapper;
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	public List<Representante> selecionaTodos(){
		return this.representanteMapper.obterTodosRepresentantes();
	}
	
	public Set<Representante> obterPorParametro(Representante representante, Model model){
		try {
			
			
			List<Usuario> listaRepresentantesSubordinado = this.usuarioBO.getSubordinado(representante.getUsuario());
			
			List<Long> ids = new ArrayList<Long>();
			for(Usuario usu: listaRepresentantesSubordinado){
				ids.add(usu.getIdUsuario());
			}
			
			representante.getUsuario().setLstIdUsuariosSubordinado(ids);
			
			representante.setApelido(toLike(representante.getApelido()));
			representante.setRazao(!isEmpty(representante.getRazao()) ? toLike(representante.getRazao()) : null );
			
			List<Representante> lstRepresentante = isEmpty(representante.getRazao())?representanteMapper.obterPorParametro(representante) :representanteMapper.obterPorParametroMaisRazao(representante);
			
			if(lstRepresentante.size() == 0){
				setRetorno(model, Mensagem.NENHUM_REGISTRO_LOCALIZADO);
				return new HashSet<Representante>();
			}
			
			Set<Representante> setRepresentante= new HashSet<Representante>();
			setRepresentante.addAll(lstRepresentante);
			
			return setRepresentante;
			
		} catch (Exception e) {
			setMsgRetorno(model, "Falha na Opera��o");
			setCodRetorno(model, -1);
			log.error("RepresentanteBO.obterPorParametro", e);
			return null;
		}
	}

	public void update(Representante representante) throws ManterEntidadeException {
		try{
			this.representanteMapper.updateRepresentante(representante);
		}catch(Exception e){
			log.error("RepresentanteBO.update", e);
			throw new ManterEntidadeException(MensagemWS.ATUALIZA_COMPRADOR_ERRO);
		}
	}

	public void insert(Representante representante) throws ManterEntidadeException {
		try{
			this.representanteMapper.insertRepresentante(representante);
		}catch(Exception e){
			log.error("RepresentanteBO.insert", e);
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
			log.error("RepresentanteBO.selecionaPorId", e);
			return null;
		}
	}
	
	public void salvarRepresentante(Representante representante){
		
		try {
			if(Objects.isNull(representanteMapper.obterPorId(representante.getIdRepresentante()))){
				this.representanteMapper.insertRepresentante(representante);
			}else{
				
				this.representanteMapper.updateRepresentante(representante);
			}
			this.usuarioBO.inactivateUsuario(representante.getUsuario(), representante.getStatus());
			this.associaRepresentante(representante.getFornecedores(), representante.getIdRepresentante());
		} catch (Exception e) {
			log.error("RepresentanteBO.salvarRepresentante", e);
		}
	}
	
	private void associaRepresentante(List<Fornecedor> lstFornecedor, Long  id) throws Exception{
		try {
			List<RepresentanteFornecedor> lstRepresentanteFornecedor = this.representanteFornecedorMapper.obterPorRepresentante(id);
			
			//excluir os fornecedores que sairam da lista
			boolean isOut;
			for(RepresentanteFornecedor representanteFornecedor : lstRepresentanteFornecedor){
				isOut=true;
				for(Fornecedor fn: lstFornecedor){
					if(fn.getIdFornecedor() == representanteFornecedor.getFornecedor().getIdFornecedor()){
						isOut = false;
						break;
					}
				}
				if(isOut)
					this.representanteFornecedorMapper.deleteIdRepresentante(representanteFornecedor);
			}
			
			//inclui os faltantes
			boolean isNew;
			for(Fornecedor fn: lstFornecedor){
				isNew=true;
				for(RepresentanteFornecedor representanteFornecedor : lstRepresentanteFornecedor){
					if(fn.getIdFornecedor() == representanteFornecedor.getFornecedor().getIdFornecedor()){
						isNew = false;
						break;
					}
				}
				if(isNew)
					this.representanteFornecedorMapper.insertPorRepresentante(new RepresentanteFornecedor(fn, id));
			}
		} catch (Exception e) {
			log.error("RepresentanteBO.associaRepresentante", e);
		}
	}
	
	private List<Fornecedor> getListFornecedor(List<RepresentanteFornecedor> lstRepresentanteFornecedor){
		List<Fornecedor> lstFornecedor= new ArrayList<Fornecedor>();
		for(RepresentanteFornecedor representante: lstRepresentanteFornecedor){
			lstFornecedor.add(representante.getFornecedor());
		}
		return lstFornecedor;
	}

	public Representante selecionaPorIdUsuario(Long idUsuario) {
		try{
			return representanteMapper.obterPorIdUsuario(idUsuario);
		}catch(Exception e){
			log.error("RepresentanteBO.selecionaPorIdUsuario", e);
			return null;
		}
	}

	public List<Representante> obterPorComprador(Comprador comp) {
		try{
			List<RepresentanteComprador> listRc = representanteCompradorMapper.obterPorComprador(comp.getId());
			
			List<Representante> listRepr = new ArrayList<>();
			
			for(RepresentanteComprador rc : listRc){
				listRepr.add(rc.getRepresentante());
			}
			
			return listRepr;
		}catch(Exception e){
			log.error("RepresentanteBO.obterPorComprador", e);
			return null;
		}
	}
	
	public List<FornecedorCusto> obterCustoAprovacaoPorFornecedor(Long id){
		try {
			FornecedorCusto fornCusto = new FornecedorCusto();
			fornCusto.setFornecedor(fornecedorMapper.obterFornecedorPorId(id));
			return this.fornecedorCustoMapper.obterFornecedorCustoAprovacao(fornCusto);
		} catch (Exception e) {
			log.error("RepresentanteBO.obterCustoAprovacaoPorFornecedor", e);
			return null;
		}
	}
}
