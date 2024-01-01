package lojaVirtualRepository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lojaVirtualRepository.models.Categoria;

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
					Categoria categoria = new Categoria(rst.getInt(1) ,rst.getString(2));
					categorias.add(categoria);

				}
			}
		}

		return categorias;
	}
}
