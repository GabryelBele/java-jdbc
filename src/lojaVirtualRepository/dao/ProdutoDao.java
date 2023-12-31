package lojaVirtualRepository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lojaVirtualRepository.models.Categoria;
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

	public List<Produto> listar() throws SQLException {

		List<Produto> produtos = new ArrayList<Produto>();

		String sql = "SELECT id, nome,descricao FROM produto";

		try (PreparedStatement stm = connection.prepareStatement(sql);) {
			stm.execute();

			ResultSet rst = stm.getResultSet();

			while (rst.next()) {
				Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
				produtos.add(produto);

			}
		}

		return produtos;
	}

	public List<Produto> buscar(Categoria result) throws SQLException {
		List<Produto> produtos = new ArrayList<Produto>();

		String sql = "SELECT id, nome,descricao FROM produto WHERE categoria_id = ? ";

		try (PreparedStatement stm = connection.prepareStatement(sql);) {
			stm.setInt(1, result.getId());
			stm.execute();

			ResultSet rst = stm.getResultSet();

			while (rst.next()) {
				Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
				produtos.add(produto);

			}
		}

		return produtos;
	}

}
