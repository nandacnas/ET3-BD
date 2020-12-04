package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import geral.Conexao;
import tabelasDoBD.Divisao;

public class ConfDivisao {
	Conexao conex = new Conexao();
	
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
}
