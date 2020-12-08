package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import geral.Conexao;
import tabelasDoBD.Divisao;
import tabelasDoBD.GrupoArmado;
import tabelasDoBD.LiderPolitico;

public class ConfDivisao{
	Conexao conex;
	
	public  ConfDivisao() {
		conex = new Conexao();
	}
	
	public void salvar(Divisao d) {
		try {	
			conex.conexao();
			PreparedStatement pst = conex.getConnection().prepareStatement(
					  "insert into DIVISAO "
					+ "(COD_GRUPO, NUM_BAIXAS_DIVISAO, HOMENS, BARCOS, AVIOES, TANQUES)"
					+ "values(?,?,?,?,?,?);");
			pst.setInt(1, d.getCod_grupo());
			pst.setInt(2, d.getNum_baixas_divisao());
			pst.setInt(3, d.getHomens());
			pst.setInt(4, d.getBarcos());
			pst.setInt(5, d.getAvioes());
			pst.setInt(6, d.getTanques());
			pst.execute();
			
			conex.desconexao();
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "ATENCAO", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro nos campos", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		

	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<Divisao> lista() {
		ArrayList<Divisao> divisoes = new ArrayList<>();
		
		conex.conexao();
		conex.executaSQL("SELECT * FROM DIVISAO");
		
		try {
			conex.getResultSet().first();
			do {
				Divisao d = new Divisao();
				
				d.setCod_grupo(conex.getResultSet().getInt("COD_GRUPO"));
				d.setNum_divisao(conex.getResultSet().getInt("NUM_DIVISAO"));
				d.setNum_baixas_divisao(conex.getResultSet().getInt("NUM_BAIXAS_DIVISAO"));

				ConfGrupoArmado config = new ConfGrupoArmado();
		    	GrupoArmado grupo = config.buscaUnica(d.getCod_grupo());
		    	d.setNome_grupo(grupo.getNome_grupo());
				
		    	divisoes.add(d);
				
			} while (conex.getResultSet().next());
			
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar lideres", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		
		return divisoes;
	}

	
	/*
	public Divisao buscaUnica(int codDivisao, int codGrupo) {
		Divisao divisao = new Divisao();
		
		conex.conexao();
		conex.executaSQL("SELECT * FROM DIVISAO WHERE NOME_LIDER ILIKE '%"+ nome +"%';");
		
		try {
			conex.getResultSet().first();

			d.setCod_grupo(conex.getResultSet().getInt("COD_GRUPO"));
			d.setNum_divisao(conex.getResultSet().getInt("NUM_DIVISAO"));
			d.setNum_baixas_divisao(conex.getResultSet().getInt("NUM_BAIXAS_DIVISAO"));

			ConfGrupoArmado config = new ConfGrupoArmado();
	    	GrupoArmado grupo = config.buscaUnica(l.getCod_grupo());
	    	d.setNome_grupo(grupo.getNome_grupo());
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar lider", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return lider;
	}
	
	
	
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
