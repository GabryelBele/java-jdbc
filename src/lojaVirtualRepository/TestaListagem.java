package lojaVirtualRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory conexao = new ConnectionFactory();
		Connection conn = conexao.recuperarConexao();

		Statement stm = conn.createStatement();
		stm.execute("SELECT id, nome,descricao FROM produto");

		stm.getResultSet();

		ResultSet rst = stm.getResultSet();

		while (rst.next()) {
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
