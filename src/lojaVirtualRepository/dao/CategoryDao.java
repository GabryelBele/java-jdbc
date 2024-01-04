package lojaVirtualRepository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lojaVirtualRepository.models.Categoria;
import lojaVirtualRepository.models.Produto;

//category
public class CategoryDao {

	private Connection connection;

	public CategoryDao(Connection connection) {
		this.connection = connection;
	}

	public List<Categoria> listar() throws SQLException {

		List<Categoria> categorias = new ArrayList<Categoria>();

		String sql = "SELECT id,nome FROM  categoria";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
					categorias.add(categoria);

				}
			}
		}

		return categorias;
	}

	public List<Categoria> listarComProdutos() throws SQLException {
	    Categoria ultima = null;
	    List<Categoria> categorias = new ArrayList<>();

	    String sql = "SELECT c.id, c.nome, p.id, p.nome, p.descricao " +
	                 "FROM categoria c " +
	                 "INNER JOIN produto p ON p.categoria_id = c.id;";

	    try (PreparedStatement pstm = connection.prepareStatement(sql)) {
	        try (ResultSet rst = pstm.executeQuery()) {
	            while (rst.next()) {
	                if (ultima == null || !ultima.getNome().equals(rst.getString(2))) {
	                    Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
	                    categorias.add(categoria);
	                    ultima = categoria;
	                }

	                Produto produto = new Produto(rst.getInt(3), rst.getString(4), rst.getString(5));
	                ultima.adicionar(produto);
	            }
	        }
	    }
	    return categorias;
	}

}
