package lojaVirtualRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/loja_virtual?useTimezone=true&servertimezone=UTC",
		"root", "root");
		
		System.out.println("Fechando conexão");
		
		Statement stm =  conn.createStatement();
		stm.execute("SELECT id, nome,descricao FROM produto");
		
		stm.getResultSet();
		
		ResultSet rst = stm.getResultSet();
				
		while(rst.next()) {
			Integer id = rst.getInt("id");
			System.out.println(id);
			String nome = rst.getString("Nome");
			System.out.println(nome);
			String descricao = rst.getString("Descricao");
			System.out.println(descricao);
			
		}
		
		conn.close();

	}

}
