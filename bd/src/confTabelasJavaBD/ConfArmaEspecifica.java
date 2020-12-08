package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import geral.Conexao;
import tabelasDoBD.Fornece;

//ii.	Listar os traficantes e os grupos armados
//(Nome) para os quais os traficantes fornecem armas “Barret M82” ou “M200 intervention”.

public class ConfArmaEspecifica {

    Conexao con = new Conexao();

    public void ConfGrupo_Fornece() {
		con = new Conexao();
	}
	
    public List<Fornece> getListaII(){
        
        List<Fornece> grupos = new ArrayList<>();
        String sql = "SELECT COD_GRUPO, NOME_TRAFICANTE FROM FORNECE" 
                    + "WHERE NOME_ARMA = “Barret M82” OR NOME_ARMA = “M200 intervention”";

        try{
            con.conexao();
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Fornece f = new Fornece();
                f.setCod_grupo(rs.getInt("cod_grupo"));
                f.setNome_traficante(rs.getString("nome_traficante"));
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
