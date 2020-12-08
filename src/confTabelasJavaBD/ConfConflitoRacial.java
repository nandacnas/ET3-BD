package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import geral.Conexao;
import tabelasDoBD.ConflitoRacial;

public class ConfConflitoRacial {
	Conexao conex;
	public  ConfConflitoRacial() {
		conex = new Conexao();
	}
	
	public void salvar(ConflitoRacial c) {
		try {	
			
			conex.conexao();
			PreparedStatement pst = conex.getConnection().prepareStatement(
					  "insert into CONFLITO_RACIAL "
					+ "(COD_CONFLITO, ETNIA)"
					+ "values(?,?);");
			pst.setInt(1, c.getCod_conflito());
			pst.setString(2, c.getEtnia());
			pst.execute();
			
			conex.desconexao();
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "ATENCAO", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro nos campos", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		

	}
}
