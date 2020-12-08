package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import geral.Conexao;
import tabelasDoBD.GrupoArmado;
import tabelasDoBD.OrganizacaoM;

public class ConfOrganizacaoM {
	Conexao conex;
	public  ConfOrganizacaoM() {
		conex = new Conexao();
	}
	
	public void salvar(OrganizacaoM c) {
		try {	
			
			conex.conexao();
			PreparedStatement pst = conex.getConnection().prepareStatement(
					  "insert into ORGANIZACAO_M "
					+ "(NOME_ORG, TIPO_ORG, TIPO_AJUDA, NUM_PESSOAS)"
					+ "values(?,?,?,?);");
			pst.setString(1, c.getNome_org());
			pst.setString(2, c.getTipo_org());
			pst.setString(3, c.getTipo_ajuda());
			pst.setInt(4, c.getNum_pessoas());

			pst.execute();
			
			conex.desconexao();
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "ATENCAO", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro nos campos", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		

	}
	
	
	
	
	
	
	
	
	
	public ArrayList<OrganizacaoM> lista() {
		ArrayList<OrganizacaoM> orgs = new ArrayList<>();
		
		conex.conexao();
		conex.executaSQL("SELECT * FROM ORGANIZACAO_M");
		
		try {
			conex.getResultSet().first();
			do {
				OrganizacaoM g = new OrganizacaoM();
				
				g.setCod_organizacao(conex.getResultSet().getInt("COD_ORGANIZACAO"));
				g.setNome_org(conex.getResultSet().getString("NOME_ORG"));
				g.setTipo_org(conex.getResultSet().getString("TIPO_ORG"));
				g.setTipo_ajuda(conex.getResultSet().getString("TIPO_AJUDA"));
				g.setNum_pessoas(conex.getResultSet().getInt("NUM_PESSOAS"));
				
				
				orgs.add(g);
				
			} while (conex.getResultSet().next());
			
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar grupos", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		

		
		return orgs;
	}

	public OrganizacaoM buscaUnica(String nome) {
		OrganizacaoM org = new OrganizacaoM();
		
		conex.conexao();
		conex.executaSQL("SELECT * FROM ORGANIZACAO_M WHERE NOME_ORG ILIKE '%"+ nome +"%';");
		
		try {
			conex.getResultSet().first();

			org.setCod_organizacao(conex.getResultSet().getInt("COD_ORGANIZACAO"));
			org.setNome_org(conex.getResultSet().getString("NOME_ORG"));
			org.setTipo_org(conex.getResultSet().getString("TIPO_ORG"));
			org.setTipo_ajuda(conex.getResultSet().getString("TIPO_AJUDA"));
			org.setNum_pessoas(conex.getResultSet().getInt("NUM_PESSOAS"));

			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar organizacao!", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return org;
	}
	
	
	
	
}
