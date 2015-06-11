package com.plataforma.myp7.dao;

import static com.plataforma.myp7.config.Conexao.getConexao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.plataforma.myp7.data.Embalagem;
import com.plataforma.myp7.util.Utils;

public class EmbalagemDAO {
	private SqlSession session;
	
	private void setLike(Embalagem embalagem){
		embalagem.setNomeEmbalagem(Utils.toLike(embalagem.getNomeEmbalagem()));
		embalagem.setSiglaEmbalagem(Utils.toLike(embalagem.getSiglaEmbalagem()));
	}
	
	private void cleanLike(Embalagem embalagem) {
		embalagem.setSiglaEmbalagem(Utils.cleanLike(embalagem.getSiglaEmbalagem()));
		embalagem.setNomeEmbalagem(Utils.cleanLike(embalagem.getNomeEmbalagem()));
	}
	
	public List<Embalagem> selecionaPorParametros(Embalagem embalagem){
		this.session = getConexao();
		this.setLike(embalagem);
		List <Embalagem> lista = this.session.selectList("obterEmbalagens", embalagem);
		this.cleanLike(embalagem);
		this.session.commit(true);
		this.session.close();
		return lista;
	}
	
	public List<Embalagem> selecionaTodos(){
		this.session = getConexao();
		List<Embalagem> lista = this.session.selectList("obterTodasEmbalagens");
		this.session.commit(true);
		this.session.close();
		return lista;
	}

	public Integer count(Embalagem embalagem) {
		this.session = getConexao();
		this.setLike(embalagem);
		Integer count = (Integer) this.session.selectOne("countEmbalagem", embalagem);
		this.cleanLike(embalagem);
		this.session.commit(true);
		this.session.close();
		return count;
	}

	public void update(final Embalagem embalagem){
		this.session = getConexao();
		this.session.update("atualizarEmbalagem", embalagem);
		this.session.commit(true);
		this.session.close();
	}
	
	public void insert(final Embalagem embalagem){
		this.session = getConexao();
		this.session.insert("salvarEmbalagem", embalagem);
		this.session.commit(true);
		this.session.close();
	}

	public Embalagem selecionaPorId(Long id) {
		this.session = getConexao();
		Embalagem embalagem = this.session.selectOne("obterEmbalagemPorId", id);
		this.session.commit(true);
		this.session.close();
		return embalagem;
	}
}
