package lojaVirtualRepository;

import java.sql.Connection;

import lojaVirtualRepository.dao.ProdutoDao;
import lojaVirtualRepository.models.Produto;

public class TestaInsercaoProduto {

	public static void main(String[] args) {

		Produto comoda = new Produto("Comoda", "Comada de Quarto");

		try (Connection conn = new ConnectionFactory().recuperarConexao()) {
			ProdutoDao daoProduto = new ProdutoDao(conn);
			daoProduto.salvar(comoda);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
