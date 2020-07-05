package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {

    private static String base = "fazenda";
    private static Connection conexao;
    String usuario = "Igor";
    String pass = "igor";

    public conexao() {
        System.out.println("conexao ok");
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;database=" + base + ";" + "user=" + usuario + ";password=" + pass;
            conexao = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException erro) {
            System.out.println(erro);
        }
    }

    public Connection getConexao() {
        return conexao;
    }

}
