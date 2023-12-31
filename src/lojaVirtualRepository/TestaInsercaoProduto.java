package lojaVirtualRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import lojaVirtualRepository.models.Produto;

public class TestaInsercaoProduto {

	public static void main(String[] args) {

		ConnectionFactory conexao = new ConnectionFactory();

		Produto comoda = new Produto("Comoda", "Comada de Quarto");

		try (Connection conn = conexao.recuperarConexao()) {

			String sql = "INSERT INTO produto (nome, descricao) VALUES (?, ?)";

			try (PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, comoda.getNome());
				pstm.setString(2, comoda.getDescricao());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {

					while (rst.next()) {
						comoda.setId(rst.getInt(1));
					}
				}

			}

			System.out.println(comoda);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
