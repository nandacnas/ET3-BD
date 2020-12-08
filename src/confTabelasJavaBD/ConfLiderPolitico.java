package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import geral.Conexao;
import tabelasDoBD.ConflitoRacial;
import tabelasDoBD.GrupoArmado;
import tabelasDoBD.LiderPolitico;

public class ConfLiderPolitico {
	Conexao conex;
	public  ConfLiderPolitico() {
		conex = new Conexao();
	}
	
	public void salvar(LiderPolitico c) {
		try {	
			
			conex.conexao();
			PreparedStatement pst = conex.getConnection().prepareStatement(
					  "insert into LIDER_POLITICO "
					+ "(NOME_LIDER, COD_GRUPO, APOIOS)"
					+ "values(?,?,?);");
			pst.setString(1, c.getNome_lider());
			pst.setInt(2, c.getCod_grupo());
			pst.setString(3, c.getApoios());
			pst.execute();
			
			conex.desconexao();
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "ATENCAO", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro nos campos", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
	
	public ArrayList<LiderPolitico> lista() {
		ArrayList<LiderPolitico> lideres = new ArrayList<>();
		
		conex.conexao();
		conex.executaSQL("SELECT * FROM LIDER_POLITICO");
		
		try {
			conex.getResultSet().first();
			do {
				LiderPolitico l = new LiderPolitico();
				
				l.setCod_grupo(conex.getResultSet().getInt("COD_GRUPO"));
				l.setNome_lider(conex.getResultSet().getString("NOME_LIDER"));
				
				ConfGrupoArmado config = new ConfGrupoArmado();
		    	GrupoArmado grupo = config.buscaUnica(l.getCod_grupo());
		    	l.setNome_grupo(grupo.getNome_grupo());
		
				lideres.add(l);
				
			} while (conex.getResultSet().next());
			
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar lideres", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		
		return lideres;
	}

	
	/*
	public LiderPolitico buscaUnica(String nome) {
		LiderPolitico lider = new LiderPolitico();
		
		conex.conexao();
		conex.executaSQL("SELECT * FROM LIDER_POLITICO WHERE NOME_LIDER ILIKE '%"+ nome +"%';");
		
		try {
			conex.getResultSet().first();

			lider.setCod_grupo(conex.getResultSet().getInt("COD_GRUPO"));
			lider.setNome_lider(conex.getResultSet().getString("NOME_LIDER"));
			
			ConfGrupoArmado config = new ConfGrupoArmado();
	    	GrupoArmado grupo = config.buscaUnica(lider.getCod_grupo());
	    	lider.setNome_grupo(grupo.getNome_grupo());
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar lider", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return lider;
	}
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
}
