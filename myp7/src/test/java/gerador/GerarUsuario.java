package gerador;

import com.plataforma.myp7.bo.CriptografarBO;

public class GerarUsuario {
	private static String usuario = "'brunohbr123@gmail.com'";
	private static String senha = "123";
	private static String tipoUsuario = "T";
	
	public static void main(String[] args) {

		StringBuilder out = new StringBuilder();
		out.append("INSERT INTO USU_USUARIO VALUES (");
		out.append("1, 'teste', ");
		out.append(usuario);
		out.append(", 123123, 'A','");
		out.append(CriptografarBO.criptografar(senha));
		out.append("', null, '"+tipoUsuario+"');");
		
		System.out.println(out.toString());
	}
}
