package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import geral.Conexao;
import tabelasDoBD.ConflitoTerritorial;
import tabelasDoBD.Fornece;

public class ConfFornece {
	Conexao conex;
	public  ConfFornece() {
		conex = new Conexao();
	}
	
	public void salvar(Fornece c) {
		try {	
			
			conex.conexao();
			PreparedStatement pst = conex.getConnection().prepareStatement(
					  "insert into FORNECE "
					+ "(COD_GRUPO, NOME_ARMA, NOME_TRAFICANTE, NUM_ARMAS)"
					+ "values(?,?,?,?);");
			pst.setInt(1, c.getCod_grupo());
			pst.setString(2, c.getNome_arma());
			pst.setString(3, c.getNome_traficante());
			pst.setInt(4, c.getNum_armas());

			pst.execute();
			
			conex.desconexao();
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "ATENCAO", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro nos campos", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		

	}
}
