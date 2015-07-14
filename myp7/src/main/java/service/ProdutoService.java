package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.plataforma.myp7.bo.ProdutoBO;
import com.plataforma.myp7.data.Produto;

@Controller
@RequestMapping("/produto")
public class ProdutoService {
	
	@Autowired
	private ProdutoBO produtoBO;
	
	@RequestMapping("/consultaProduto")
	public List<Produto> consultaProduto(@RequestParam(value="id", required=false, defaultValue="0")Long id
										,@RequestParam(value="descricao", required=false, defaultValue="") String desc){
		try {
			Produto produto = new Produto();
			produto.setIdProduto(id);
			produto.setDesProduto(desc);
			return produtoBO.consultaProdutoService(produto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
