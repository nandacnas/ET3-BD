package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import geral.Conexao;
import tabelasDoBD.ChefeMilitar;
import tabelasDoBD.ConflitoEconomico;

public class ConfChefeMilitar {
	Conexao conex;
	public  ConfChefeMilitar() {
		conex = new Conexao();
	}
	
	public void salvar(ChefeMilitar c) {
		try {	
			
			conex.conexao();
			PreparedStatement pst = conex.getConnection().prepareStatement(
					  "insert into CHEFE_MILITAR "
					+ "(FAIXA, NOME_LIDER, LP_COD_GRUPO, NUM_DIVISAO, D_COD_GRUPO)"
					+ "values(?,?,?,?,?);");
			pst.setString(1, c.getFaixa());
			pst.setString(2, c.getLP_nome_lider());
			pst.setInt(3, c.getLP_cod_grupo());
			pst.setInt(4, c.getD_nro_divisao());
			pst.setInt(5, c.getD_cod_grupo());

			pst.execute();
			
			conex.desconexao();
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "ATENCAO", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro nos campos", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		

	}
}
