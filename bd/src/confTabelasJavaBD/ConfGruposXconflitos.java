package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import geral.Conexao;
import tabelasDoBD.GruposXconflitos;
import tabelasDoBD.OrganizacaoM;

public class ConfGruposXconflitos {
	Conexao conex;
	public  ConfGruposXconflitos() {
		conex = new Conexao();
	}
	
	public void salvar(GruposXconflitos c) {
		try {	
			
			conex.conexao();
			PreparedStatement pst = conex.getConnection().prepareStatement(
					  "insert into GRUPOS_x_CONFLITOS "
					+ "(COD_CONFLITO, COD_GRUPO)"
					+ "values(?,?);");
			pst.setInt(1, c.getCod_conflito());
			pst.setInt(2, c.getCod_grupo());

			pst.execute();
			
			conex.desconexao();
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "ATENCAO", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro nos campos", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		

	}
}
