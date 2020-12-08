package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import geral.Conexao;
import tabelasDoBD.Conflito;


//iii.	Listar os 5 maiores conflitos em número de mortos.


public class ConfMaioresConflitos {

    Conexao con;
    public void ConfConflitos() {
		con = new Conexao();
	}
	

    public List<Conflito> getListaIII(){
        
        List<Conflito> conflito = new ArrayList<>();
        String sql = "SELECT COD_CONFLITO FROM CONFLITO ORDER BY NUM_MORTOS DESC LIMIT 5";

        try{
            con.conexao();
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Conflito c = new Conflito();
                c.setCod_conflito(rs.getInt("cod_conflito"));
                c.setNum_mortos(rs.getInt("num_mortos"));
                conflito.add(c);
            }

        stmt.close();
        rs.close();
        con.desconexao();

        } catch (SQLException e) {
            System.out.println("Pesquisa nao realizada");
            return null;
		}

        return conflito;
    }
    
    
}
