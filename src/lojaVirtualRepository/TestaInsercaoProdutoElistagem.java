package lojaVirtualRepository;

import java.sql.Connection;
import java.util.List;

import lojaVirtualRepository.dao.ProdutoDao;
import lojaVirtualRepository.models.Produto;

public class TestaInsercaoProdutoElistagem {

	public static void main(String[] args) {

		Produto comoda = new Produto("Comoda", "Comada de Quarto");

		try (Connection conn = new ConnectionFactory().recuperarConexao()) {
			ProdutoDao daoProduto = new ProdutoDao(conn);
			daoProduto.salvar(comoda);
			List<Produto> list = daoProduto.listar();
			
			list.stream().forEach(lp -> System.out.println(lp));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
