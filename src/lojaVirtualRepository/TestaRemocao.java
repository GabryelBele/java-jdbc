package lojaVirtualRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory conexao = new ConnectionFactory();
		Connection conn = conexao.recuperarConexao();

		Statement stm = conn.createStatement();
		stm.execute("DELETE FROM produto WHERE id > 2");
		
		Integer linhasModificadas = stm.getUpdateCount();
		
		System.out.println("Quantidade de linhas foram excluidas: " + linhasModificadas);

		conn.close();

	}

}
