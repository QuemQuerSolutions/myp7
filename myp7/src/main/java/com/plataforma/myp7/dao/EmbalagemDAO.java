package com.plataforma.myp7.dao;

import static com.plataforma.myp7.config.Conexao.getConexao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.plataforma.myp7.data.Embalagem;
import com.plataforma.myp7.util.Utils;

public class EmbalagemDAO {
	private SqlSession session;
	
	public EmbalagemDAO(){
		this.session = getConexao();
	}
	
	private void setLike(Embalagem embalagem){
		embalagem.setNomeEmbalagem(Utils.toLike(embalagem.getNomeEmbalagem()));
		embalagem.setSiglaEmbalagem(Utils.toLike(embalagem.getSiglaEmbalagem()));
	}
	
	private void cleanLike(Embalagem embalagem) {
		embalagem.setSiglaEmbalagem(Utils.cleanLike(embalagem.getSiglaEmbalagem()));
		embalagem.setNomeEmbalagem(Utils.cleanLike(embalagem.getNomeEmbalagem()));
	}
	
	public List<Embalagem> selecionaPorParametros(Embalagem embalagem){
		this.setLike(embalagem);
		List <Embalagem> lista = this.session.selectList("obterEmbalagens", embalagem);
		this.cleanLike(embalagem);
		return lista;
	}
	
	public List<Embalagem> selecionaTodos(){
		return this.session.selectList("obterTodasEmbalagens");
	}

	public Integer count(Embalagem embalagem) {
		this.setLike(embalagem);
		Integer count = (Integer) this.session.selectOne("countEmbalagem", embalagem);
		this.cleanLike(embalagem);
		return count;
	}

	public void update(final Embalagem embalagem){
		this.session.update("salvarEmbalagem", embalagem);
		this.session.commit();
	}
	
	public void insert(final Embalagem embalagem){
		this.session.insert("atualizarEmbalagem", embalagem);
		this.session.commit();
	}
}
