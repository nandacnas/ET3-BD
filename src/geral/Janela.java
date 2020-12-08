package geral;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Janela {
	
	private JButton inserir;
	private JButton listas;
	private JPanel pp;
	private JFrame f;
	

	public void iniciar() {
		f = new JFrame("Conflitos Belicos");  
		pp = new JPanel(new BorderLayout());
		pp.add("North", criandoImagemSoldado());
	    pp.add("South",criandBotoes());
	    f.add(pp);
	    f.setSize(900,600);  
	    f.setVisible(true);  
	    f.setResizable(false);
	    eventoBotoes();	
	    
	}


	private Component criandBotoes() {
		JPanel p = new JPanel(new FlowLayout());
	    inserir = new JButton("Inserir nova relacao");  
	    listas = new JButton("Gerar listas");  
	    inserir.setPreferredSize(new Dimension(300, 30));
	    listas.setPreferredSize(new Dimension(300, 30));
	    p.add(inserir);
	    p.add(listas);
	    return p;
	}


	private Component criandoImagemSoldado() {
		JPanel p = new JPanel(new FlowLayout());
		ImageIcon i = new ImageIcon(getClass().getResource("a2.png"));
		JLabel lbl = new JLabel(i);
		p.add(lbl); 		
		return p;
	}

	

	private void eventoBotoes() {
		inserir.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				pp.setVisible(false); 
				Inserir inseridor = new Inserir(); 
				
				JButton voltar = new JButton("Voltar");  
				JButton ir = new JButton("Ir");  
				ir.setPreferredSize(new Dimension(50, 30));
				voltar.setPreferredSize(new Dimension(100, 30));
				JComboBox<String> combo = inseridor.getCombo();
				
				
				JPanel pVolta = new JPanel(new BorderLayout());
				pVolta.add("West", voltar);
				
				JPanel pSeleciona = new JPanel(new FlowLayout());
				pSeleciona.add(combo);
				pSeleciona.add(ir);
				
				JPanel pBotoes = new JPanel(new GridLayout(2, 1));
				pBotoes.add(pVolta);
				pBotoes.add(pSeleciona);
				
				JPanel pTudo = new JPanel(new BorderLayout());
				pTudo.add("North", pBotoes);
				
	    		
				f.add(pTudo);

			    
			    voltar.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e) {
						f.remove(pTudo);
			    		pp.setVisible(true); 
					}
		    	});	
				
			    ir.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e) {

			    		pTudo.removeAll();
			    		pTudo.add("North", pBotoes);	 
			    		JPanel opcao = inseridor.escolha(combo.getSelectedIndex());
			    		if(opcao != null) {
			    			pTudo.add("Center", opcao);
			    		}else {
			    			JOptionPane.showMessageDialog(null, "Selecione um cadastro na Combobox!", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			    		}
			    		
			    		f.validate();
			    		
			    		
					}
		    	});	
				
				    
			}
    	});	
		
		listas.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		pp.setVisible(false); 
	    		
				
				JButton voltar = new JButton("Voltar");  
				JButton ir = new JButton("Ir");  
				ir.setPreferredSize(new Dimension(50, 30));
				voltar.setPreferredSize(new Dimension(100, 30));
				JComboBox<String> combo = new JComboBox<String>();
				combo.addItem("Escolha o que deseja...");
				combo.addItem("Histograma por tipo de conflito e número de conflitos");
				combo.addItem("Listar Traficantes e os grupos armados por armas “Barret M82” ou “M200 intervention”");
				combo.addItem("Listar os 5 maiores conflitos em número de mortos");
				combo.addItem("Listar as 5 maiores organizações em número de mediações");
				combo.addItem("Listar os 5 maiores grupos armados com maior número de armas fornecidos");
				combo.addItem("Listar o país e número de conflitos com maior número de conflitos religiosos");
				combo.setPreferredSize(new Dimension(620, 30));
				
				JPanel pVolta = new JPanel(new BorderLayout());
				pVolta.add("West", voltar);
				
				JPanel pSeleciona = new JPanel(new FlowLayout());
				pSeleciona.add(combo);
				pSeleciona.add(ir);
				
				JPanel pBotoes = new JPanel(new GridLayout(2, 1));
				pBotoes.add(pVolta);
				pBotoes.add(pSeleciona);
				
				JPanel pTudo = new JPanel(new BorderLayout());
				pTudo.add("North", pBotoes);

				f.add(pTudo);

			    
			    voltar.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e) {
						f.remove(pTudo);
			    		pp.setVisible(true); 
					}
		    	});	
			    
			}
    	});	
	}
	


	
}
