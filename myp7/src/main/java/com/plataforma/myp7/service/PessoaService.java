package com.plataforma.myp7.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.plataforma.myp7.bo.PessoaBO;
import com.plataforma.myp7.data.Pessoa;
import com.plataforma.myp7.util.Utils;

@Controller
@RequestMapping("/wspessoa")
public class PessoaService {

	private Gson gson;
	
	@Autowired
	private PessoaBO pessoaBO;
	
	public PessoaService() {
		this.gson = new Gson();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/consultaPessoa",produces="application/json")
	@ResponseBody
	public String consultaProduto(@RequestParam(value="codigoPessoa", required=false, defaultValue="0") Long codPessoa,
								  @RequestParam(value="nomePessoa", required=false, defaultValue="") String nomePessoa){
		List<Pessoa> lstPessoa = this.pessoaBO.obterPessoaCodNome(codPessoa, nomePessoa);
		return this.gson.toJson(lstPessoa.size()==0? Utils.formataMsgem(1):lstPessoa);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/inserirPessoa",produces="application/json")
	@ResponseBody
	public String inserirPessoa(@RequestParam(value="razaoSocial", required=true)String razao,
								@RequestParam(value="fantasia", required=true)String fantasia,
								@RequestParam(value="fisicaJuridica", required=true)String fisicaJuridica,
								@RequestParam(value="sexo", required=true)String sexo,
								@RequestParam(value="cep", required=true)String cep,
								@RequestParam(value="cidade", required=true)String cidade,
								@RequestParam(value="uf", required=true)String uf,
								@RequestParam(value="bairro", required=true)String bairro,
								@RequestParam(value="logradouro", required=true)String logradouro,
								@RequestParam(value="logradouroNro", required=true)String logradouroNro,
								@RequestParam(value="logradrouroCompl", required=false, defaultValue="")String logradrouroCompl,
								@RequestParam(value="fone1", required=true)String fone1,
								@RequestParam(value="fone2", required=false, defaultValue="")String fone2,
								@RequestParam(value="fone3", required=false, defaultValue="")String fone3,
								@RequestParam(value="nroCpfCnpj", required=true)BigDecimal nroCpfCnpj,
								@RequestParam(value="digCpfCnpj", required=true)Integer digCpfCnpj,
								@RequestParam(value="nroRgInscrEstadual", required=true)String nroRgInscrEstadual){
		
		
		Pessoa pessoa = new Pessoa();
		pessoa.setRazao(razao);
		pessoa.setFantasia(fantasia);
		pessoa.setFisicaJuridica(fisicaJuridica);
		pessoa.setSexo(sexo);
		pessoa.setCep(cep);
		pessoa.setCidade(cidade);
		pessoa.setUf(uf);
		pessoa.setBairro(bairro);
		pessoa.setLogradouro(logradouroNro);
		pessoa.setLogradouroNro(logradouroNro);
		pessoa.setLogradrouroCompl(logradrouroCompl);
		pessoa.setFone1(fone1);
		pessoa.setFone2(fone2);
		pessoa.setFone3(fone3);
		pessoa.setNroCpfCnpj(nroCpfCnpj);
		pessoa.setDigCpfCnpj(digCpfCnpj);
		pessoa.setNroRgInscrEstadual(nroRgInscrEstadual);
		
		try {
			this.pessoaBO.inserirPessoa(pessoa);
			return this.gson.toJson(Utils.formataMsgem(7));
		} catch (Exception e) {
			return this.gson.toJson(Utils.formataMsgem(6));
		}
	}
}

