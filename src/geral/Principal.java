package geral;

import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) {
		Janela j = new Janela();
		Conexao conex = new Conexao();
		if(conex.testaConexao())
			j.iniciar();
		else
			JOptionPane.showMessageDialog(null, "Erro ao conectar com o Banco de Dados", "ATENCAO", JOptionPane.ERROR_MESSAGE);
		
	}

}
