package lojaVirtualRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory conexao = new ConnectionFactory();
		Connection conn = conexao.recuperarConexao();

		PreparedStatement stm = conn.prepareStatement("SELECT id, nome,descricao FROM produto");
		stm.execute();

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
