package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import geral.Conexao;
import tabelasDoBD.Conflito;
import tabelasDoBD.GrupoArmado;

public class ConfConflitos{
	Conexao conex;
	public  ConfConflitos() {
		conex = new Conexao();
	}
	
	public void salvar(Conflito c) {
		try {	
			
			conex.conexao();
			PreparedStatement pst = conex.getConnection().prepareStatement(
					  "insert into CONFLITO "
					+ "(NOME, TIPO, NUM_FERIDOS, NUM_MORTOS)"
					+ "values(?,?,?,?);");
			pst.setString(1, c.getNome());
			pst.setString(2, c.getTipo());
			pst.setInt(3, c.getNum_feridos());
			pst.setInt(4, c.getNum_mortos());
			pst.execute();
			
			conex.desconexao();
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "ATENCAO", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro nos campos", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		

	}
	
	public ArrayList<Conflito> lista() {
		ArrayList<Conflito> conflitos = new ArrayList<>();
		
		conex.conexao();
		conex.executaSQL("SELECT * FROM CONFLITO");
		
		try {
			conex.getResultSet().first();
			do {
				Conflito c = new Conflito();
				
				c.setNome(conex.getResultSet().getString("NOME"));
				c.setCod_conflito(conex.getResultSet().getInt("COD_CONFLITO"));
				
				conflitos.add(c);
				
			} while (conex.getResultSet().next());
			
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar grupos", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		
		return conflitos;
	}

	public Conflito buscaUnica(String nome) {
		Conflito conflito = new Conflito();
		
		conex.conexao();
		conex.executaSQL("SELECT * FROM CONFLITO WHERE NOME ILIKE '%"+ nome +"%';");
		
		try {
			conex.getResultSet().first();
			conflito.setCod_conflito(conex.getResultSet().getInt("COD_CONFLITO"));
			conflito.setTipo(conex.getResultSet().getString("TIPO"));
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar conflito", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		return conflito;
	}
}
