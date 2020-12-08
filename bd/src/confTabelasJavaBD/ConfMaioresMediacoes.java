package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import geral.Conexao;
import tabelasDoBD.De_media;
import tabelasDoBD.OrganizacaoM;

//iv.	Listar as 5 maiores organizações em número de mediações.

public class ConfMaioresMediacoes {
    Conexao con = new Conexao();

    public void ConfOrganizacoes() {
		con = new Conexao();
	}
	

    public List<OrganizacaoM> getListaIV(){
        
        List<OrganizacaoM> org = new ArrayList<>();
        String sql = "SELECT COD_ORGANIZACAO, COUNT(COD_ORGANIZACAO) FROM ORGANIZACAO_M" 
                    + "GROUP BY COD_ORGANIZACAO WHERE COD_ORGANIZACAO = DE_MEDIA.COD_ORGANIZACAO"
                    + "DESC LIMIT 5";

        try{
            con.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                OrganizacaoM o = new OrganizacaoM();
                o.setCod_organizacao(rs.getInt("cod_organizacao"));
                org.add(o);
            }

        stmt.close();
        rs.close();
        con.desconexao();

        } catch (SQLException e) {
            System.out.println("Pesquisa nao realizada");
            return null;
		}

        return org;
    }
    
}
