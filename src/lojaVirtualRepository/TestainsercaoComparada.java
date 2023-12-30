package lojaVirtualRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestainsercaoComparada {

    public static void main(String[] args) throws SQLException {
        String nome = "mouse'";
        String descricao = "mouse sem o(culos";

        ConnectionFactory conexao = new ConnectionFactory();

        try (Connection conn = conexao.recuperarConexao()) {
            conn.setAutoCommit(false);

            try (PreparedStatement result = conn.prepareStatement("INSERT INTO produto (nome, descricao) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {

                adicionarVariavel(nome, descricao, result);
                adicionarVariavel(nome, descricao, result);

                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
                conn.rollback();
            }
        }
    }

    private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
        stm.setString(1, nome);
        stm.setString(2, descricao);

        stm.execute();

        try (ResultSet rst = stm.getGeneratedKeys()) {
            while (rst.next()) {
                Integer id = rst.getInt(1);
                System.out.println("O id inserido foi: " + id);
            }
        }
    }
}
