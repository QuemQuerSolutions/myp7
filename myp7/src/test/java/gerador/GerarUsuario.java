package gerador;

import com.plataforma.myp7.bo.CriptografarBO;

public class GerarUsuario {
	private static String usuario = "'robson.pierno@gmail.com'";
	private static String senha = "123";
	private static String tipoUsuario = "R";
	
	public static void main(String[] args) {

		StringBuilder out = new StringBuilder();
		out.append("INSERT INTO USU_USUARIO VALUES (");
		out.append("2, 'teste', ");
		out.append(usuario);
		out.append(", 123123, 'A','");
		out.append(CriptografarBO.criptografar(senha));
		out.append("', null, '"+tipoUsuario+"');");
		
		System.out.println(out.toString());
	}
}
