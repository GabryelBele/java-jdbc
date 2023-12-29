package lojaVirtualRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory conexao = new ConnectionFactory();
		Connection conn = conexao.recuperarConexao();

		PreparedStatement stm = conn.prepareStatement("DELETE FROM produto WHERE id > ?");
		stm.setInt(1, 2);
		stm.execute();

		Integer linhasModificadas = stm.getUpdateCount();

		System.out.println("Quantidade de linhas foram excluidas: " + linhasModificadas);

		conn.close();

	}

}
