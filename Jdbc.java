import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {
	public static void conexao() throws ClassNotFoundException, SQLException{
		//Registrando o Driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Fazendo a Conexao
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Dinheirama","root","123456");
		System.out.println("Conexão feita com sucesso");
		
	}
}
