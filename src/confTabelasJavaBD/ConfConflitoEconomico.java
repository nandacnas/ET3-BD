package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import geral.Conexao;
import tabelasDoBD.ConflitoEconomico;

public class ConfConflitoEconomico {
	Conexao conex;
	public  ConfConflitoEconomico() {
		conex = new Conexao();
	}
	
	public void salvar(ConflitoEconomico c) {
		try {	
			
			conex.conexao();
			PreparedStatement pst = conex.getConnection().prepareStatement(
					  "insert into CONFLITO_ECONOMICO "
					+ "(COD_CONFLITO, MAT_PRIMA)"
					+ "values(?,?);");
			pst.setInt(1, c.getCod_conflito());
			pst.setString(2, c.getMat_prima());
			pst.execute();
			
			conex.desconexao();
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "ATENCAO", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro nos campos", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		

	}
}
