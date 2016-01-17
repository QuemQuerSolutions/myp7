package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.emptyToNull;
import static com.plataforma.myp7.util.Utils.setCodRetorno;
import static com.plataforma.myp7.util.Utils.setMsgRetorno;
import static com.plataforma.myp7.util.Utils.toLike;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.plataforma.myp7.data.Fornecedor;
import com.plataforma.myp7.data.FornecedorCusto;
import com.plataforma.myp7.data.Representante;
import com.plataforma.myp7.data.RepresentanteFornecedor;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.enums.Mensagem;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.exception.ManterEntidadeException;
import com.plataforma.myp7.mapper.FornecedorCustoMapper;
import com.plataforma.myp7.mapper.FornecedorMapper;
import com.plataforma.myp7.mapper.RepresentanteFornecedorMapper;
import com.plataforma.myp7.util.Utils;

@Service
public class FornecedorBO {

	@Autowired
	private FornecedorMapper fornecedorMapper;
	
	@Autowired
	private RepresentanteFornecedorMapper representanteFornecedorMapper;
	
	@Autowired
	private FornecedorCustoMapper fornecedorCustoMapper;
	
	public List<Fornecedor> obterTodos(){
		return this.fornecedorMapper.obterTodos();
	}
	
	public List<Fornecedor> obterFornecedorPorParametro(Fornecedor fornecedor, Usuario usuario){
		
		if(!Objects.isNull(fornecedor.getParam()) && !Utils.isEmpty(fornecedor.getParam()))
			fornecedor.setUsuario(usuario);
		fornecedor.setRazao(toLike(fornecedor.getRazao()));
		fornecedor.setStatusTela("".equals(fornecedor.getStatusTela())?null: fornecedor.getStatusTela());
		return this.fornecedorMapper.obterFornecedorPorParametro(fornecedor);
	}
	
	public void inserir(Fornecedor fornecedor) throws ManterEntidadeException{
		try{
			this.fornecedorMapper.inserirFornecedor(fornecedor);
		}catch(Exception e){
			throw new ManterEntidadeException(MensagemWS.INSERT_FORNC_ERRO);
		}
	}

	public void update(Fornecedor fornecedor) throws ManterEntidadeException{
		try{
			this.fornecedorMapper.updateFornecedor(fornecedor);
		}catch(Exception e){
			throw new ManterEntidadeException(MensagemWS.ATUALIZA_FORNC_ERRO);
		}
	}
	
	public List<Fornecedor> obterFornecedorPorParametro(Long idFornecedor, String cnpjFornecedor, String razao, Model model){
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setIdFornecedor(idFornecedor);
		fornecedor.setRazao(emptyToNull(toLike(razao)));
		if (!"".equals(cnpjFornecedor) && !Objects.isNull(cnpjFornecedor)) 
			fornecedor = this.setNumDigitoDocumento(cnpjFornecedor, fornecedor);
		
		Integer countFornecedor = this.fornecedorMapper.countFornecedorPorParametro(fornecedor);
		
		if(countFornecedor == 0){
			setMsgRetorno(model, Mensagem.NENHUM_REGISTRO_LOCALIZADO.getMensagem());
			setCodRetorno(model, Mensagem.NENHUM_REGISTRO_LOCALIZADO.getCodigo());
			return new ArrayList<Fornecedor>();
		}
		return this.formataCNPJ(this.fornecedorMapper.obterFornecedorPorParametro(fornecedor));
	}
	
	private Fornecedor setNumDigitoDocumento(String nDocumento, Fornecedor fornecedor){
		fornecedor.setNroCpfCnpj(nDocumento.substring(0, nDocumento.length()-2));
		fornecedor.setDigCpfCnpj(Integer.parseInt(nDocumento.substring(nDocumento.length()-2, nDocumento.length())));
		return fornecedor;
	}
	
	private List<Fornecedor> formataCNPJ(List<Fornecedor> lstFornecedor){
		List<Fornecedor> lstFornecedorNovo = new ArrayList<Fornecedor>();
		for(Fornecedor f:lstFornecedor){
			f.setCnpjFormatado(Utils.format("##.###.###/####-##", f.getNroCpfCnpj().concat(String.valueOf(f.getDigCpfCnpj()))));
			lstFornecedorNovo.add(f);
		}
		return lstFornecedorNovo;
	}
	
	public Fornecedor obterPorId(Long idFornecedor){
		if(Objects.isNull(idFornecedor)) 
			return new Fornecedor();
		Fornecedor fornecedor = this.fornecedorMapper.obterFornecedorPorId(idFornecedor);
		List<RepresentanteFornecedor> lstRepresentanteFornecedors = this.representanteFornecedorMapper.obterPorFornecedor(idFornecedor);
		fornecedor.setRepresentantes(getListRepresentante(lstRepresentanteFornecedors));
		return fornecedor;
	}

	private List<Representante> getListRepresentante(List<RepresentanteFornecedor> lstRepresentanteFornecedor){
		List<Representante> lstRepresentante = new ArrayList<Representante>();
		for(RepresentanteFornecedor representante: lstRepresentanteFornecedor){
			lstRepresentante.add(representante.getRepresentante());
		}
		return lstRepresentante;
	}
	
	public void salvarFornecedor(Fornecedor fornecedor) throws Exception{
		if(Objects.isNull(fornecedorMapper.obterFornecedorPorId(fornecedor.getIdFornecedor())))
			this.fornecedorMapper.inserirFornecedor(fornecedor);
		else
			this.fornecedorMapper.updateFornecedor(fornecedor);
		
		this.associaRepresentante(fornecedor.getRepresentantes(), fornecedor.getIdFornecedor());
	}
	
	private void associaRepresentante(List<Representante> lstRepresentante, Long  id) throws Exception{
		
		List<RepresentanteFornecedor> lstRepresentanteFornecedores = this.representanteFornecedorMapper.obterPorFornecedor(id);
		
		//excluir os que sairam
		boolean isOut = true;
		for(RepresentanteFornecedor repF: lstRepresentanteFornecedores){
			isOut = true;
			for(Representante rep: lstRepresentante){
				if(rep.getIdRepresentante() == repF.getRepresentante().getIdRepresentante()){
					isOut = false;
					break;
				}
			}
			if(isOut)
				this.representanteFornecedorMapper.delete(repF);
		}
		
		
		//incluir os novos
		boolean isNew = true;
		for(Representante rep: lstRepresentante){
			isNew = true;
			for(RepresentanteFornecedor repF: lstRepresentanteFornecedores){
				if(rep.getIdRepresentante() == repF.getRepresentante().getIdRepresentante()){
					isNew = false;
					break;
				}
			}
			if(isNew)
				this.representanteFornecedorMapper.insert(new RepresentanteFornecedor(rep,id));
		}
	}
	
	public List<FornecedorCusto> obterCustoAprovacaoPorRepresentante(Long id){
		return this.fornecedorCustoMapper.obterRepresentanteCustoAprovacao(id);
		
	}
	
}
