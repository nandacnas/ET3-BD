package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import geral.Conexao;
import tabelasDoBD.Dialoga;
import tabelasDoBD.OrganizacaoM;

public class ConfDialoga {
	Conexao conex;
	public  ConfDialoga() {
		conex = new Conexao();
	}
	
	public void salvar(Dialoga c) {
		try {	
			
			conex.conexao();
			PreparedStatement pst = conex.getConnection().prepareStatement(
					  "insert into DIALOGA "
					+ "(NOME_LIDER, COD_GRUPO, COD_ORGANIZACAO)"
					+ "values(?,?,?);");
			pst.setString(1, c.getNome_lider());
			pst.setInt(2, c.getCod_grupo());
			pst.setInt(3, c.getCod_organizacao());

			pst.execute();
			
			conex.desconexao();
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "ATENCAO", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro nos campos", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		

	}
}
