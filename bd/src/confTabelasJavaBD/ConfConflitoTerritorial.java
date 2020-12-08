package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import geral.Conexao;
import tabelasDoBD.Conflito;
import tabelasDoBD.ConflitoTerritorial;

public class ConfConflitoTerritorial {
	Conexao conex;
	public  ConfConflitoTerritorial() {
		conex = new Conexao();
	}
	
	public void salvar(ConflitoTerritorial c) {
		try {	
			
			conex.conexao();
			PreparedStatement pst = conex.getConnection().prepareStatement(
					  "insert into CONFLITO_TERRITORIAL "
					+ "(COD_CONFLITO, REGIAO)"
					+ "values(?,?);");
			pst.setInt(1, c.getCod_conflito());
			pst.setString(2, c.getRegiao());
			pst.execute();
			
			conex.desconexao();
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "ATENCAO", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro nos campos", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		

	}
	
	
	
	
	
	
}
