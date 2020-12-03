import java.sql.*;

public class conexao{
    private String usuario;
    private String senha;
    private String url;
    private Connection con;

    conexao(){
        url = "jdbc:postgresql://localhost:5432/EP3.3";
        usuario = "postgres";
        senha = "ramed";

        try{

            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexao realizada");

        } catch(Exception e ){
            e.printStackTrace();
        }
    }
}