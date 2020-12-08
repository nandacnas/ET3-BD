package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import geral.Conexao;

//i.	Gerar um gráfico, histograma, por tipo de conflito e número de conflitos.

public class ConfHistograma {

    Conexao con = new Conexao();

    public void ConfGrupo_Fornece() {
		con = new Conexao();
	}

    public void geraHistograma(){

        String sql = "SELECT fun_conflito_tipo_histograma('*')";

        try{
            con.conexao();
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                System.out.println(rs);
            }

        stmt.close();
        rs.close();
        con.desconexao();

        } catch (SQLException e) {
            System.out.println("histograma nao foi gerado");
		}
    }
}
