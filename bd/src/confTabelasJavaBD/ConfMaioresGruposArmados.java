package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import geral.Conexao;
import tabelasDoBD.Fornece;

//v.	Listar os 5 maiores grupos armados com maior número de armas fornecidos.

public class ConfMaioresGruposArmados {
    Conexao con = new Conexao();

    public void ConfGrupo_Fornece() {
		con = new Conexao();
	}
	

    public List<Fornece> getListaV(){
        
        List<Fornece> grupos = new ArrayList<>();
        String sql = "SELECT COD_GRUPO FROM FORNECE" 
                    + "ORDER BY NUM_ARMAS"
                    + "DESC LIMIT 5";

        try{
            con.conexao();
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Fornece f = new Fornece();
                f.setCod_grupo(rs.getInt("cod_grupo"));
                f.setNum_armas(rs.getInt("num_armas"));
                grupos.add(f);
            }

        stmt.close();
        rs.close();
        con.desconexao();

        } catch (SQLException e) {
            System.out.println("Pesquisa nao realizada");
            return null;
		}

        return grupos;
    }
}
