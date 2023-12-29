package lojaVirtualRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestainsercaoComparada {

	public static void main(String[] args) throws SQLException {
		String nome = "mouse'";
		String descricao = "mouse sem o(culos";
		
		ConnectionFactory conexao = new ConnectionFactory();
		Connection conn = conexao.recuperarConexao();

		PreparedStatement result = 
				conn.prepareStatement("INSERT INTO produto (nome, descricao) VALUES (?, ?)",
						Statement.RETURN_GENERATED_KEYS);
		
		result.setString(1, nome);
		result.setString(2, descricao);
		
		result.execute();		
		ResultSet rst = result.getGeneratedKeys();
		
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("O id inserido foi: " + id);
		}

	}
}
