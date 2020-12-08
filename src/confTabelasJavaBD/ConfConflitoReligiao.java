package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import geral.Conexao;
import tabelasDoBD.ConflitoReligioso;
import tabelasDoBD.ConflitoTerritorial;

public class ConfConflitoReligiao {
	Conexao conex;
	public  ConfConflitoReligiao() {
		conex = new Conexao();
	}
	
	public void salvar(ConflitoReligioso c) {
		try {	
			
			conex.conexao();
			PreparedStatement pst = conex.getConnection().prepareStatement(
					  "insert into CONFLITO_RELIGIOSO "
					+ "(COD_CONFLITO, RELIGIAO)"
					+ "values(?,?);");
			pst.setInt(1, c.getCod_conflito());
			pst.setString(2, c.getReligiao());
			pst.execute();
			
			conex.desconexao();
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "ATENCAO", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro nos campos", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		

	}
}
