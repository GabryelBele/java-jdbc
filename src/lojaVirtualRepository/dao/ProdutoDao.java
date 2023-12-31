package lojaVirtualRepository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import lojaVirtualRepository.models.Produto;

public class ProdutoDao {

	private Connection connection;

	public ProdutoDao(Connection connection) {
		this.connection = connection;
	}

	public void salvar(Produto produto) {
		
			String sql = "INSERT INTO produto (nome, descricao) VALUES (?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, produto.getNome());
				pstm.setString(2, produto.getDescricao());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {

					while (rst.next()) {
						produto.setId(rst.getInt(1));
					}
				}
				System.out.println(produto);
			} catch (Exception e) {
					e.printStackTrace();
				}
	}
}
