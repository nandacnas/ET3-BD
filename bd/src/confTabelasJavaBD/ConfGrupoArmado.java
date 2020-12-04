package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import geral.Conexao;
import tabelasDoBD.GrupoArmado;

public class ConfGrupoArmado {
	Conexao conex = new Conexao();
	
	public void salvar(GrupoArmado grupo) {
		try {
			conex.conexao();
			PreparedStatement guarda = conex.getConnection().prepareStatement(
					  "insert into GRUPO_ARMADO "
					+ "(NOME_GRUPO, NUM_BAIXAS_GRUPO)"
					+ "values(?,0);");
			guarda.setString(1, grupo.getNome_grupo());
			guarda.execute();
			
			conex.desconexao();
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "ATENCAO", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro nos campos", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		

	}

	public ArrayList<GrupoArmado> lista() {
		ArrayList<GrupoArmado> grupos = new ArrayList<>();
		
		conex.conexao();
		conex.executaSQL("SELECT * FROM GRUPO_ARMADO");
		
		try {
			conex.getResultSet().first();
			do {
				GrupoArmado g = new GrupoArmado();
				
				g.setCod_grupo(conex.getResultSet().getInt("COD_GRUPO"));
				g.setNome_grupo(conex.getResultSet().getString("NOME_GRUPO"));
				g.setNum_baixas_grupo(conex.getResultSet().getInt("NUM_BAIXAS_GRUPO"));
				
				grupos.add(g);
				
			} while (conex.getResultSet().next());
			
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar grupos", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		
		
		
		
		return grupos;
	}

	public GrupoArmado buscaUnica(String nome) {
		GrupoArmado grupo = new GrupoArmado();
		
		conex.conexao();
		conex.executaSQL("SELECT * FROM GRUPO_ARMADO WHERE NOME_GRUPO ILIKE '%"+ nome +"%';");
		
		try {
			conex.getResultSet().first();

			grupo.setCod_grupo(conex.getResultSet().getInt("COD_GRUPO"));
			grupo.setNome_grupo(conex.getResultSet().getString("NOME_GRUPO"));
			grupo.setNum_baixas_grupo(conex.getResultSet().getInt("NUM_BAIXAS_GRUPO"));

			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar grupo", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return grupo;
	}
}
