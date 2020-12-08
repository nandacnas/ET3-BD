package geral;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class Pesquisar {
private JComboBox<String> combo;
	
	public Pesquisar() {
		iniciaCombo();
	}

	private void iniciaCombo() {
		combo = new JComboBox<String>();
		combo.setPreferredSize(new Dimension(620, 30));
		combo.addItem("Escolha o que deseja..."); //0
		combo.addItem("Histograma por tipo de conflito e número de conflitos");//1
		combo.addItem("Listar Traficantes e os grupos armados por armas “Barret M82” ou “M200 intervention”");//2
		combo.addItem("Listar os 5 maiores conflitos em número de mortos");//3
		combo.addItem("Listar as 5 maiores organizações em número de mediações");//4
		combo.addItem("Listar os 5 maiores grupos armados com maior número de armas fornecidos");//5
		combo.addItem("Listar o país e número de conflitos com maior número de conflitos religiosos");//6

		
	}
	
	public JComboBox<String> getCombo() {
		return combo;
	}
	
	public JPanel escolha(int item) {
		JPanel cadastro = null;
		
		switch (item) {
		case 1:
			cadastro = hisograma();
			break;
		case 2:
			cadastro = listarTraficantes();
			break;
		case 3:
			cadastro = maioresConflitos();
			break;
		case 4:
			cadastro = maioresOrganizações();
			break;
		case 5:
			cadastro = maioresGruposArmados();
			break;
		case 6:
			cadastro = paísConflitosReligiosos();
			break;
		default:
			cadastro = null;
			break;

		}
		return cadastro;
	}

	private JPanel hisograma() {
		// TODO Auto-generated method stub
		return null;
	}

	private JPanel listarTraficantes() {
		// TODO Auto-generated method stub
		return null;
	}

	private JPanel maioresConflitos() {
		// TODO Auto-generated method stub
		return null;
	}

	private JPanel maioresOrganizações() {
		// TODO Auto-generated method stub
		return null;
	}

	private JPanel maioresGruposArmados() {
		// TODO Auto-generated method stub
		return null;
	}

	private JPanel paísConflitosReligiosos() {
		// TODO Auto-generated method stub
		return null;
	}



}
