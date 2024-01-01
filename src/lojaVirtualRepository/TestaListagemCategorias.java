package lojaVirtualRepository;

import java.sql.Connection;
import java.util.List;

import lojaVirtualRepository.dao.CategoryDao;
import lojaVirtualRepository.dao.ProdutoDao;
import lojaVirtualRepository.models.Categoria;
import lojaVirtualRepository.models.Produto;

public class TestaListagemCategorias {

	public static void main(String[] args) {

		try (Connection conn = new ConnectionFactory().recuperarConexao()) {
			
			CategoryDao categoryDao = new CategoryDao(conn);
			
			List<Categoria> list = categoryDao.listar();
			list.stream().forEach(result -> {
				System.out.println(result.getNome());

				try {
					for (Produto produto : new ProdutoDao(conn).buscar(result)) {
						System.out.println(result.getNome() + " - " + produto.getNome());
					}
				} catch (Exception e) {
					e.getMessage();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
