package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import geral.Conexao;
import tabelasDoBD.OrganizacaoM;
import tabelasDoBD.TipoArma;

public class ConfTipoArma {
	Conexao conex;
	public  ConfTipoArma() {
		conex = new Conexao();
	}
	
	public void salvar(TipoArma c) {
		try {	
			
			conex.conexao();
			PreparedStatement pst = conex.getConnection().prepareStatement(
					  "insert into TIPO_ARMA "
					+ "(NOME_ARMA, INDICADOR)"
					+ "values(?,?);");
			pst.setString(1, c.getNome_arma());
			pst.setInt(2, c.getIndicador());


			pst.execute();
			
			conex.desconexao();
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "ATENCAO", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro nos campos", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	public ArrayList<TipoArma> lista() {
		ArrayList<TipoArma> armas = new ArrayList<>();
		
		conex.conexao();
		conex.executaSQL("SELECT * FROM TIPO_ARMA");
		
		try {
			conex.getResultSet().first();
			do {
				TipoArma a = new TipoArma();
				
				a.setIndicador(conex.getResultSet().getInt("INDICADOR"));
				a.setNome_arma(conex.getResultSet().getString("NOME_ARMA"));

				
				armas.add(a);
				
			} while (conex.getResultSet().next());
			
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar armas", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		

		
		return armas;
	}

	public TipoArma buscaUnica(String nome) {
		TipoArma arma = new TipoArma();
		
		conex.conexao();
		conex.executaSQL("SELECT * FROM TIPO_ARMA WHERE NOME_ARMA ILIKE '%"+ nome +"%';");
		
		try {
			conex.getResultSet().first();

			arma.setIndicador(conex.getResultSet().getInt("INDICADOR"));
			arma.setNome_arma(conex.getResultSet().getString("NOME_ARMA"));

			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar arma!", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return arma;
	}
	
	
	
}
