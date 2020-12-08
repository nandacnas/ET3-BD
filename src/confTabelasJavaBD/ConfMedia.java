package confTabelasJavaBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import geral.Conexao;
import tabelasDoBD.Conflito;
import tabelasDoBD.De_media;

public class ConfMedia {
	Conexao conex;
	public  ConfMedia() {
		conex = new Conexao();
	}
	
	public void salvar(De_media c) {
		try {	
			
			conex.conexao();
			PreparedStatement pst = conex.getConnection().prepareStatement(
					  "insert into DE_MEDIA "
					+ "(COD_ORGANIZACAO, COD_CONFLITO, DE_MEDIA)"
					+ "values(?,?,?);");	
			pst.setInt(1, c.getCod_organizacao());
			pst.setInt(2, c.getCod_conflito());
			pst.setString(3, c.getDe_media());
			pst.execute();
			
			conex.desconexao();
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "ATENCAO", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro nos campos", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		

	}
}
