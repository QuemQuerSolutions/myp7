package com.plataforma.myp7.config;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.plataforma.myp7.data.Usuario;

public class TesteConexao {
    
    private SqlSessionFactory sqlSessionFactory; 
     
    public TesteConexao(){
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }
 
    /**
     * Returns the list of all Contact instances from the database.
     * @return the list of all Contact instances from the database.
     */
    public List<Usuario> obterTodos(){
 
        SqlSession session = sqlSessionFactory.openSession();
         
        try {
            List<Usuario> list = session.selectList("obterTodos");
            
            return list;
        } finally {
            session.close();
        }
    }
 
    
    public static void main(String[] args) {
    	TesteConexao t = new TesteConexao();
    	
    	 List<Usuario> list = t.obterTodos();
    	 System.out.println(list.get(0).getEmail());
	}
    
}