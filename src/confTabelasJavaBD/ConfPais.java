package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import geral.Conexao;
import tabelasDoBD.OrganizacaoM;
import tabelasDoBD.Pais;

public class ConfPais {
	Conexao conex;
	public  ConfPais() {
		conex = new Conexao();
	}
	
	public void salvar(Pais c) {
		try {	
			
			conex.conexao();
			PreparedStatement pst = conex.getConnection().prepareStatement(
					  "insert into PAIS "
					+ "(COD_CONFLITO, PAIS)"
					+ "values(?,?);");
			pst.setInt(1, c.getCod_conflito());
			pst.setString(2, c.getPais());

			

			pst.execute();
			
			conex.desconexao();
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "ATENCAO", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro nos campos", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		

	}
}
