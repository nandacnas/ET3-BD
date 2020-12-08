package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import geral.Conexao;
import tabelasDoBD.ConflitoReligioso;
import tabelasDoBD.Pais;


//vi.	Listar o país e número de conflitos com maior número de conflitos religiosos.

public class ConfMaisReligiosos {
    Conexao con = new Conexao();

    public void ConfMaisReligioso() {
		con = new Conexao();
	}
	

    public Pais getListaVI(){
        
        Pais p = new Pais();
        String sql = "SELECT CONFLITO_RELIGIOSO.COD_CONFLITO, PAIS.PAIS" 
                    + "FROM CONFLITO_RELIGIOSO, PAIS"
                    + "WHERE CONFLITO_RELIGIOSO.COD_CONFLITO = PAIS.COD_CONFLITO AND MAX(COUNT(CONFLITO_RELIGIOSO.COD_CONFLITO))";

        try{
            con.conexao();
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                p.setCod_conflito(rs.getInt("cod_conflito"));
                p.setPais(rs.getString("pais"));
            }

        stmt.close();
        rs.close();
        con.desconexao();

        } catch (SQLException e) {
            System.out.println("Pesquisa nao realizada");
            return null;
		}

        return p;
    }
    
}
