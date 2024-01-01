package lojaVirtualRepository;

import java.sql.Connection;
import java.util.List;

import lojaVirtualRepository.dao.CategoryDao;
import lojaVirtualRepository.models.Categoria;

public class TestaListagemCategorias {

	public static void main(String[] args) {

		try(Connection conn = new ConnectionFactory().recuperarConexao()) {
			CategoryDao categoryDao = new CategoryDao(conn);
			
			List<Categoria> list = categoryDao.listar();
			list.stream().forEach(result -> System.out.println(result.getNome()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
