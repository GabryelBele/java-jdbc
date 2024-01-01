package lojaVirtualRepository;

import java.sql.Connection;
import java.util.List;

import lojaVirtualRepository.dao.ProdutoDao;
import lojaVirtualRepository.models.Produto;

public class TestaInsercaoProdutoElistagem {

	public static void main(String[] args) {

		Produto notebook = new Produto("Notebook", "Notebook Samsung");
		Produto geladeira = new Produto("Geladeira", "Geladeira Azul");
		Produto movel = new Produto("Comoda", "Comoda Vertical");

		try (Connection conn = new ConnectionFactory().recuperarConexao()) {
			ProdutoDao daoProduto = new ProdutoDao(conn);
			daoProduto.salvar(notebook);
			daoProduto.salvar(geladeira);
			daoProduto.salvar(movel);
			List<Produto> list = daoProduto.listar();
			
			list.stream().forEach(lp -> System.out.println(lp));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
