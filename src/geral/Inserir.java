package geral;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import confTabelasJavaBD.ConfChefeMilitar;
import confTabelasJavaBD.ConfConflitoEconomico;
import confTabelasJavaBD.ConfConflitoRacial;
import confTabelasJavaBD.ConfConflitoReligiao;
import confTabelasJavaBD.ConfConflitoTerritorial;
import confTabelasJavaBD.ConfConflitos;
import confTabelasJavaBD.ConfDialoga;
import confTabelasJavaBD.ConfDivisao;
import confTabelasJavaBD.ConfFornece;
import confTabelasJavaBD.ConfGrupoArmado;
import confTabelasJavaBD.ConfGruposXconflitos;
import confTabelasJavaBD.ConfLiderPolitico;
import confTabelasJavaBD.ConfMedia;
import confTabelasJavaBD.ConfOrganizacaoM;
import confTabelasJavaBD.ConfPais;
import confTabelasJavaBD.ConfTipoArma;
import tabelasDoBD.ChefeMilitar;
import tabelasDoBD.Conflito;
import tabelasDoBD.ConflitoEconomico;
import tabelasDoBD.ConflitoRacial;
import tabelasDoBD.ConflitoReligioso;
import tabelasDoBD.ConflitoTerritorial;
import tabelasDoBD.De_media;
import tabelasDoBD.Dialoga;
import tabelasDoBD.Divisao;
import tabelasDoBD.Fornece;
import tabelasDoBD.GrupoArmado;
import tabelasDoBD.GruposXconflitos;
import tabelasDoBD.LiderPolitico;
import tabelasDoBD.OrganizacaoM;
import tabelasDoBD.Pais;
import tabelasDoBD.TipoArma;


public class Inserir {
	
	private JComboBox<String> combo;
	
	public Inserir() {
		iniciaCombo();
	}

	private void iniciaCombo() {
		combo = new JComboBox<String>();
		combo.setPreferredSize(new Dimension(620, 30));
		combo.addItem("Escolha o que deseja..."); //0
		combo.addItem("Cadastrar divisões dentro de um grupo militar"); //1
		combo.addItem("Cadastrar conflitos bélicos");//2
		combo.addItem("Cadastrar grupos militares");//3
		combo.addItem("Cadastrar líderes políticos");//4
		combo.addItem("Cadastrar chefes militares");//5
		combo.addItem("Cadastrar organizacao");//6
		combo.addItem("Cadastrar dialogo organizacao / líder político");//7
		combo.addItem("Cadastrar mediacao organizacao / conflito");//8
		combo.addItem("Cadastrar participacao de grupo armado em conflito");//9
		combo.addItem("Cadastrar paises em conflito");//10
		combo.addItem("Cadastrar Armas");//11
		combo.addItem("Cadastrar fornecimendo das armas aos grupos armados");//12
		combo.addItem("Cadastrar motivo do conflito");//13

		
	}
	
	public JComboBox<String> getCombo() {
		return combo;
	}
	
	public JPanel escolha(int item) {
		JPanel cadastro = null;
		
		switch (item) {
		case 1:
			cadastro = divisoes();
			break;
		case 2:
			cadastro = conflitos();
			break;
		case 3:
			cadastro = gruposMilitares();
			break;
		case 4:
			cadastro = liderPolitico();
			break;
		case 5:
			cadastro = chefeMilitar();
			break;
		case 6:
			cadastro = organizacao();
			break;
		case 7:
			cadastro = dialogo();
			break;
		case 8:
			cadastro = mediacao();
			break;
		case 9:
			cadastro = participacao();
			break;
		case 10:
			cadastro = paises();
			break;
		case 11:
			cadastro = arma();
			break;
		case 12:
			cadastro = fornece();
			break;
		case 13:
			cadastro = motivoConflito();
			break;
		default:
			cadastro = null;
			break;

		}
		return cadastro;
	}








	private JPanel divisoes() {
		JPanel cadastro = new JPanel(new GridLayout(4, 1));
		
		
		JPanel f1 = new JPanel(new GridBagLayout());
		JTextField homens = new JTextField(15);
		homens.setPreferredSize(new Dimension(100, 30));
		JLabel lblhomens = new JLabel("Numero de Homens:  ");
		lblhomens.setFont(new Font("Serif",Font.BOLD,15));
		JTextField barcos = new JTextField(15);
		barcos.setPreferredSize(new Dimension(100, 30));
		JLabel lblbarcos = new JLabel("              Numero de Barcos: ");
		lblbarcos.setFont(new Font("Serif",Font.BOLD,15));
		f1.add(lblhomens);
		f1.add(homens); 
		f1.add(lblbarcos);
		f1.add(barcos);
		
		JPanel f2 = new JPanel(new GridBagLayout());
		JTextField avioes = new JTextField(15);
		avioes.setPreferredSize(new Dimension(100, 30));
		JLabel lblavioes = new JLabel("Numero de Avioes:  ");
		lblavioes.setFont(new Font("Serif",Font.BOLD,15));
		
		JTextField tanques = new JTextField(15);
		tanques.setPreferredSize(new Dimension(100, 30));
		JLabel lbltanques = new JLabel("            Numero de Tanques: ");
		lbltanques.setFont(new Font("Serif",Font.BOLD,15));
		f2.add(lblavioes); 
		f2.add(avioes); 
		f2.add(lbltanques); 
		f2.add(tanques);
		

		JPanel f3 = new JPanel(new GridBagLayout());
		JTextField baixas = new JTextField(15);
		baixas.setPreferredSize(new Dimension(100, 30));
		JLabel lblbaixas = new JLabel("Numero de Baixas:  ");
		lblbaixas.setFont(new Font("Serif",Font.BOLD,15));
		JComboBox grupo = new JComboBox<String>();
		grupo.setPreferredSize(new Dimension(180, 30));
		JLabel lblgrupo = new JLabel("             Pertence ao grupo:  ");
		lblgrupo.setFont(new Font("Serif",Font.BOLD,15));
		
		
    	ArrayList<GrupoArmado> itens = null;
    	ConfGrupoArmado config = new ConfGrupoArmado();
    	itens = config.lista();
    	grupo.addItem("Selecione um grupo...");
    	for (GrupoArmado s : itens) 
			grupo.addItem(s.getNome_grupo());
			
		/*	
		grupo.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
				        	
            }
		});
		*/
		
		f3.add(lblbaixas);
		f3.add(baixas);
		f3.add(lblgrupo);
		f3.add(grupo);
		
		
		JPanel f4 = new JPanel(new GridBagLayout());
		JButton botao = new JButton("Cadastrar");  
		botao.setPreferredSize(new Dimension(180, 30));
		f4.add(botao);
		
		System.out.println("entrrou aqui");
		
		
		
		cadastro.add(f1);
		cadastro.add(f2);
		cadastro.add(f3);
		cadastro.add(f4);
		
		
		botao.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Divisao divisao = new Divisao();
	    		ConfDivisao config = new ConfDivisao();
	    		
	    		divisao.setNome_grupo((String)grupo.getSelectedItem());
	    		divisao.setNum_baixas_divisao(Integer.parseInt(baixas.getText()));
	    		divisao.setHomens(Integer.parseInt(homens.getText()));
	    		divisao.setBarcos(Integer.parseInt(barcos.getText()));
	    		divisao.setAvioes(Integer.parseInt(avioes.getText()));
	    		divisao.setTanques(Integer.parseInt(tanques.getText()));

	    		
	    		config.salvar(divisao);
	    		
	    		baixas.setText("");
	    		homens.setText("");
	    		barcos.setText("");
	    		avioes.setText("");
	    		tanques.setText("");
	    		grupo.setSelectedIndex(0);

	    	}
    	});
		
		
		
		
		// caixa combo com grupo
		return cadastro;
	}
	
	
	private JPanel conflitos() {
		JPanel cadastro = new JPanel(new GridLayout(4, 1));
		
		
		JPanel f1 = new JPanel(new GridBagLayout());
		JTextField nome = new JTextField(15);
		nome.setPreferredSize(new Dimension(100, 30));
		JLabel lblnome = new JLabel("Nome do conflito:  ");
		lblnome.setFont(new Font("Serif",Font.BOLD,15));
		
		JTextField feridos = new JTextField(15);
		feridos.setPreferredSize(new Dimension(100, 30));
		JLabel lblferidos = new JLabel("            Numero de feridos:  ");
		lblferidos.setFont(new Font("Serif",Font.BOLD,15));
		f1.add(lblnome);
		f1.add(nome); 
		f1.add(lblferidos);
		f1.add(feridos);
		
					
		JPanel f2 = new JPanel(new GridBagLayout());
		
		JComboBox tipo = new JComboBox<String>();
		tipo.setPreferredSize(new Dimension(220, 30));
		tipo.addItem("Escolha o que deseja...");
		tipo.addItem("territorial");
		tipo.addItem("religioso");
		tipo.addItem("racial");
		tipo.addItem("economico");
		JLabel lbltipo = new JLabel("Tipo do conflito:  ");
		lbltipo.setFont(new Font("Serif",Font.BOLD,15));
		
		
		/*
		JTextField especificacao = new JTextField(15);
		especificacao.setPreferredSize(new Dimension(100, 30));
		JLabel lblespecificacao = new JLabel("      Escolha o tipo do conflito*  ");
		lblespecificacao.setFont(new Font("Serif",Font.BOLD,15));
		especificacao.setEnabled(false);
		
		
		
		tipo.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                // 
            	switch (tipo.getSelectedIndex()) {
        		case 1:
        			lblespecificacao.setText("            Nome da Regiao:  ");
        			especificacao.setText("");
        			especificacao.setEnabled(true);
        			break;
        		case 2:
        			lblespecificacao.setText("            Nome da Religiao:  ");
        			especificacao.setText("");
        			especificacao.setEnabled(true);
        			break;
        		case 3:
        			lblespecificacao.setText("            Nome de Etinia:  ");
        			especificacao.setText("");
        			especificacao.setEnabled(true);
        			break;
        		case 4:
        			lblespecificacao.setText("         Nome de Materia Prima:  ");
        			especificacao.setText("");
        			especificacao.setEnabled(true);
        			break;
        		default:
        			lblespecificacao.setText("      Escolha o tipo do conflito*  ");
        			especificacao.setText("");
        			especificacao.setEnabled(false);
        			break;
        		}
            }
       	});
		*/
		
		
		
		
		
		
		
		f2.add(lbltipo);
		f2.add(tipo); 
		//f2.add(lblespecificacao);
		//f2.add(especificacao);
		
		

		
		
		//JPanel f3 = new JPanel(new GridBagLayout());
		JButton botao = new JButton("Cadastrar");  
		botao.setPreferredSize(new Dimension(180, 30));
		JLabel lblbotao = new JLabel("                              ");
		lblbotao.setFont(new Font("Serif",Font.BOLD,15));
		f2.add(lblbotao);
		f2.add(botao);
		
		
		
		
		
		
		
		
		botao.addActionListener(new ActionListener() {
		
	    	public void actionPerformed(ActionEvent e) {
	    		Conflito conflito = new Conflito();
	    		ConfConflitos config = new ConfConflitos();
	    		
	    		
	    		conflito.setNome(nome.getText());
	    		conflito.setNum_feridos(Integer.parseInt(feridos.getText()));
	    		conflito.setNum_mortos(0);
	    		conflito.setTipo((String)tipo.getSelectedItem());
	    		
	  	    		
	    		config.salvar(conflito);
	    		
	    		nome.setText("");
	    		feridos.setText("");
	    		tipo.setSelectedIndex(0);

	    	}

		});
		
		
		
		
		cadastro.add(f1);
		cadastro.add(f2);
		//cadastro.add(f3);

		//LEMBRE DO NUMERO DE MORTOS QUANDO FOR FAZER OS EVENTOS DO BOTAO
		
		return cadastro;
	}
	

	private JPanel gruposMilitares() {
		JPanel cadastro = new JPanel(new GridLayout(4, 1));
		
		
		JPanel f1 = new JPanel(new GridBagLayout());
		JTextField nome = new JTextField(15);
		nome.setPreferredSize(new Dimension(100, 30));
		JLabel lblnome= new JLabel("Nome do Grupo armado:  ");
		lblnome.setFont(new Font("Serif",Font.BOLD,15));
		JButton botao = new JButton("Cadastrar");  
		botao.setPreferredSize(new Dimension(180, 30));
		JLabel lblbotao = new JLabel("                                        ");
		lblbotao.setFont(new Font("Serif",Font.BOLD,15));
		f1.add(lblnome);
		f1.add(nome); 
		f1.add(lblbotao);
		f1.add(botao);
		
		
		cadastro.add(f1);

		
		botao.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		GrupoArmado grupo = new GrupoArmado();
	    		ConfGrupoArmado config = new ConfGrupoArmado();
	    		
	    		grupo.setNome_grupo(nome.getText());
	    		config.salvar(grupo);
	    		
	    		nome.setText("");
	    	}
    	});
		
		return cadastro;

	}

	
	private JPanel liderPolitico() {
		JPanel cadastro = new JPanel(new GridLayout(4, 1));
		
		
		JPanel f1 = new JPanel(new GridBagLayout());
		JTextField nome = new JTextField(15);
		nome.setPreferredSize(new Dimension(100, 30));
		JLabel lblnome = new JLabel("Nome do Lider Politico:  ");
		lblnome.setFont(new Font("Serif",Font.BOLD,15));
		
		JTextField apoiadores = new JTextField(15);
		apoiadores.setPreferredSize(new Dimension(100, 30));
		JLabel lblapoiadores = new JLabel("         Numero de apoiadores:  ");
		lblapoiadores.setFont(new Font("Serif",Font.BOLD,15));
		f1.add(lblnome);
		f1.add(nome); 
		f1.add(lblapoiadores);
		f1.add(apoiadores);
		
					
		JPanel f3 = new JPanel(new GridBagLayout());
		JComboBox grupo = new JComboBox<String>();
		grupo.setPreferredSize(new Dimension(180, 30));
		JLabel lblgrupo = new JLabel("Pertence ao grupo:  ");
		lblgrupo.setFont(new Font("Serif",Font.BOLD,15));
		
		JButton botao = new JButton("Cadastrar");
		botao.setPreferredSize(new Dimension(180, 30));
		JLabel lblbotao = new JLabel("                                           ");
		lblbotao.setFont(new Font("Serif",Font.BOLD,15));

		
		ArrayList<GrupoArmado> itens = null;
    	ConfGrupoArmado config = new ConfGrupoArmado();
    	itens = config.lista();
    	grupo.addItem("Selecione um grupo...");
    	for (GrupoArmado s : itens) 
			grupo.addItem(s.getNome_grupo());
		
		
		
		f3.add(lblgrupo);
		f3.add(grupo);
		f3.add(lblbotao);
		f3.add(botao);
		
		
		cadastro.add(f1);
		cadastro.add(f3);

		botao.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		LiderPolitico lider = new LiderPolitico();
	    		ConfLiderPolitico config = new ConfLiderPolitico();
	    		
	    		lider.setApoios(apoiadores.getText());
	    		lider.setNome_grupo((String)grupo.getSelectedItem());
	    		lider.setNome_lider(nome.getText());

	    		
	    		config.salvar(lider);
	    		
	    		apoiadores.setText("");
	    		nome.setText("");
	    		grupo.setSelectedIndex(0);

	    	}
    	});
		
		return cadastro;
	}


	private JPanel chefeMilitar() {
		JPanel cadastro = new JPanel(new GridLayout(4, 1));
		
		
		JPanel f1 = new JPanel(new GridBagLayout());
		JTextField faixa = new JTextField(19);
		faixa.setPreferredSize(new Dimension(100, 30));
		JLabel lblfaixa = new JLabel("Faixa do chefe:  ");
		lblfaixa.setFont(new Font("Serif",Font.BOLD,15));
		
		JComboBox lider = new JComboBox<String>();
		lider.setPreferredSize(new Dimension(300, 30));
		JLabel lbllider = new JLabel("     Obedece o lider politico:  ");
		lbllider.setFont(new Font("Serif",Font.BOLD,15));
		
		ArrayList<LiderPolitico> itens = null;
    	ConfLiderPolitico config = new ConfLiderPolitico();
    	itens = config.lista();
    	lider.addItem("Selecione um lider...");
    	for (LiderPolitico s : itens) {
			String itemDaCombo = "Grupo: '"+ s.getNome_grupo().trim() +"'    Nome: '"+ s.getNome_lider().trim() +"'";
    		lider.addItem(itemDaCombo);
    	}
    		
		
		
		
		
		f1.add(lblfaixa);
		f1.add(faixa); 
		f1.add(lbllider);
		f1.add(lider);
		
		
		
		
		JPanel f2 = new JPanel(new GridBagLayout());
		JComboBox divisao = new JComboBox<String>();
		divisao.setPreferredSize(new Dimension(300, 30));
		JLabel lbldivisao = new JLabel("Dirige a divisao:  ");
		lbldivisao.setFont(new Font("Serif",Font.BOLD,15));
		

		
		
		
		ArrayList<Divisao> itens2 = null;
    	ConfDivisao config2 = new ConfDivisao();
    	itens2 = config2.lista();
    	divisao.addItem("Selecione uma divisao...");
    	for (Divisao s : itens2) {
			String itemDaCombo = "Grupo: '"+ s.getNome_grupo().trim() +"'   Divisao Nº: '"+ s.getNum_divisao() +"'";
			divisao.addItem(itemDaCombo);
    	}
		
    	
    	
    	

		JButton botao = new JButton("Cadastrar");  
		botao.setPreferredSize(new Dimension(180, 30));
		JLabel lblbotao = new JLabel("                                           ");
		lblbotao.setFont(new Font("Serif",Font.BOLD,15));


		
		
		f2.add(lbldivisao);
		f2.add(divisao); 
		f2.add(lblbotao);
		f2.add(botao);
		
		

		
		cadastro.add(f1);
		cadastro.add(f2);


		botao.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		ChefeMilitar chefe = new ChefeMilitar();
	    		ConfChefeMilitar config = new ConfChefeMilitar();
	    		
	    		
	    		String converte = (String)lider.getSelectedItem();
	    		String[] infoPL = converte.split("'");
	    		
	    		converte = (String)divisao.getSelectedItem();
	    		String[] infoD = converte.split("'"); 
	    		
	    		//POSICAO grupo[1] e nome/numero[3]
	    		
	    		chefe.setFaixa(faixa.getText());
	    		chefe.setLP_nome_lider(infoPL[3]);
	    		chefe.setLP_nome_grupo(infoPL[1]);
	    		chefe.setD_nro_divisao(Integer.parseInt(infoD[3]));
	    		chefe.setD_nome_grupo(infoD[1]);
	    		
	    		config.salvar(chefe);
	    		
	    		faixa.setText("");
	    		lider.setSelectedIndex(0);
	    		divisao.setSelectedIndex(0);


	    	}
    	});
		
		return cadastro;
	}
	
	
	private JPanel organizacao() {
		
		JPanel cadastro = new JPanel(new GridLayout(4, 1));
		
		
		JPanel f1 = new JPanel(new GridBagLayout());
		JTextField nome = new JTextField(19);
		nome.setPreferredSize(new Dimension(100, 30));
		JLabel lblnome = new JLabel("Nome da organizacao:  ");
		lblnome.setFont(new Font("Serif",Font.BOLD,15));
		
		JTextField tipo = new JTextField(19);
		tipo.setPreferredSize(new Dimension(100, 30));
		JLabel lbltipo = new JLabel("          Tipo da organizacao:  ");
		lbltipo.setFont(new Font("Serif",Font.BOLD,15));
		
		f1.add(lblnome);
		f1.add(nome); 
		f1.add(lbltipo);
		f1.add(tipo);
		
		
		
		
		JPanel f2 = new JPanel(new GridBagLayout());
		JTextField tipoAjuda = new JTextField(19);
		tipoAjuda.setPreferredSize(new Dimension(100, 30));
		JLabel lbltipoAjuda = new JLabel("Tipo da ajuda:  ");
		lbltipoAjuda.setFont(new Font("Serif",Font.BOLD,15));
		
		JTextField numPessoas = new JTextField(19);
		numPessoas.setPreferredSize(new Dimension(100, 30));
		JLabel lblnumPessoas = new JLabel("               Numero de pessoas:  ");
		lblnumPessoas.setFont(new Font("Serif",Font.BOLD,15));
		
		f2.add(lbltipoAjuda);
		f2.add(tipoAjuda); 
		f2.add(lblnumPessoas);
		f2.add(numPessoas);


		JPanel f3 = new JPanel(new GridBagLayout());
		JButton botao = new JButton("Cadastrar");  
		botao.setPreferredSize(new Dimension(180, 30));
		f3.add(botao);
		
		
		cadastro.add(f1);
		cadastro.add(f2);
		cadastro.add(f3);

		
		botao.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		OrganizacaoM org = new OrganizacaoM();
	    		ConfOrganizacaoM config = new ConfOrganizacaoM();
	    		
	    		
	    		org.setNome_org(nome.getText());
	    		org.setTipo_org(tipo.getText());
	    		org.setTipo_ajuda(tipoAjuda.getText());
	    		org.setNum_pessoas(Integer.parseInt(numPessoas.getText()));


	    		config.salvar(org);
	    		
	    		nome.setText("");
	    		tipo.setText("");
	    		tipoAjuda.setText("");
	    		numPessoas.setText("");

	    	}
    	});
		
		
		return cadastro;
		
		

	}
	
	
	private JPanel dialogo() {
		JPanel cadastro = new JPanel(new GridLayout(4, 1));


		JPanel f2 = new JPanel(new GridBagLayout());
		
		JComboBox lider = new JComboBox<String>();
		lider.setPreferredSize(new Dimension(300, 30));
		JLabel lbllider = new JLabel("Lider politico:  ");
		lbllider.setFont(new Font("Serif",Font.BOLD,15));
		
		ArrayList<LiderPolitico> itens = null;
    	ConfLiderPolitico config = new ConfLiderPolitico();
    	itens = config.lista();
    	lider.addItem("Selecione um lider...");
    	for (LiderPolitico s : itens) {
			String itemDaCombo = "Grupo: '"+ s.getNome_grupo().trim() +"'    Nome: '"+ s.getNome_lider().trim() +"'";
    		lider.addItem(itemDaCombo);
    	}
    		
		
		
		
		JComboBox organizacao = new JComboBox<String>();
		organizacao.setPreferredSize(new Dimension(250, 30));
		JLabel lblorganizacao = new JLabel("       Organizacao:  ");
		lblorganizacao.setFont(new Font("Serif",Font.BOLD,15));
		
		
		ArrayList<OrganizacaoM> itens2 = null;
    	ConfOrganizacaoM config2 = new ConfOrganizacaoM();
    	itens2 = config2.lista();
    	organizacao.addItem("Selecione uma organizacao...");
    	for (OrganizacaoM s : itens2) 
    		organizacao.addItem(s.getNome_org());
		
		
		
		f2.add(lbllider);
		f2.add(lider); 
		f2.add(lblorganizacao);
		f2.add(organizacao);
		
		
		
		JPanel f3 = new JPanel(new GridBagLayout());

		JButton botao = new JButton("Cadastrar");  
		botao.setPreferredSize(new Dimension(180, 30));

		
		f3.add(botao);
		
		

		
		cadastro.add(f2);
		cadastro.add(f3);
		
		
		
		botao.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Dialoga dialoga = new Dialoga();
	    		ConfDialoga config = new ConfDialoga();
	    		
	    		String converte = (String)lider.getSelectedItem();
	    		String[] infoPL = converte.split("'");
	    		
	    		
	    		dialoga.setNome_grupo(infoPL[1]);
	    		dialoga.setNome_lider(infoPL[3]);
	    		dialoga.setNome_org((String)organizacao.getSelectedItem());

	    		config.salvar(dialoga);
	    		
	    		lider.setSelectedIndex(0);
	    		organizacao.setSelectedIndex(0);

	    	}
    	});
		
		
		return cadastro;
	}
	
	
	private JPanel mediacao() {
		JPanel cadastro = new JPanel(new GridLayout(4, 1));


		JPanel f1 = new JPanel(new GridBagLayout());
		
		JComboBox organizacao = new JComboBox<String>();
		organizacao.setPreferredSize(new Dimension(220, 30));
		JLabel lblorganizacao = new JLabel("Organizacao:  ");
		lblorganizacao.setFont(new Font("Serif",Font.BOLD,15));		
		
		ArrayList<OrganizacaoM> itens2 = null;
    	ConfOrganizacaoM config2 = new ConfOrganizacaoM();
    	itens2 = config2.lista();
    	organizacao.addItem("Selecione uma organizacao...");
    	for (OrganizacaoM s : itens2) 
    		organizacao.addItem(s.getNome_org());
		
		
		JComboBox conflito = new JComboBox<String>();
		conflito.setPreferredSize(new Dimension(220, 30));
		JLabel lblconflito = new JLabel("         Conflito:  ");
		lblconflito.setFont(new Font("Serif",Font.BOLD,15));
		
		ArrayList<Conflito> itens = null;
    	ConfConflitos config = new ConfConflitos();
    	itens = config.lista();
    	conflito.addItem("Selecione um conflito...");
    	for (Conflito s : itens) 
    		conflito.addItem(s.getNome());
		
		f1.add(lblorganizacao);
		f1.add(organizacao); 
		f1.add(lblconflito);
		f1.add(conflito);
		
		
		
		JPanel f2 = new JPanel(new GridBagLayout());
		
		
		JTextField data = new JTextField(18);
		data.setPreferredSize(new Dimension(100, 30));
		JLabel lbldata= new JLabel("Data entrada:  ");
		lbldata.setFont(new Font("Serif",Font.BOLD,15));

		JButton botao = new JButton("Cadastrar");  
		botao.setPreferredSize(new Dimension(180, 30));
		JLabel lblbotao = new JLabel("                               ");
		lblbotao.setFont(new Font("Serif",Font.BOLD,15));
		
		f2.add(lbldata);
		f2.add(data);
		f2.add(lblbotao);
		f2.add(botao);
		
		

		
		cadastro.add(f1);
		cadastro.add(f2);

		
		
		botao.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		De_media media = new De_media();
	    		ConfMedia config = new ConfMedia();
	    		
	    		
	    		media.setDe_media(data.getText());
	    		media.setNome_conflito((String)conflito.getSelectedItem());
	    		media.setNome_org((String)organizacao.getSelectedItem());

	    		config.salvar(media);
	    		
	    		data.setText("");
	    		conflito.setSelectedIndex(0);
	    		organizacao.setSelectedIndex(0);

	    	}
    	});
		
		return cadastro;
	}

	
	private JPanel participacao() {
		JPanel cadastro = new JPanel(new GridLayout(4, 1));


		JPanel f2 = new JPanel(new GridBagLayout());
		
		JComboBox conflito = new JComboBox<String>();
		conflito.setPreferredSize(new Dimension(220, 30));
		JLabel lblconflito = new JLabel("Conflito:  ");
		lblconflito.setFont(new Font("Serif",Font.BOLD,15));		
		
		ArrayList<Conflito> itens = null;
    	ConfConflitos config = new ConfConflitos();
    	itens = config.lista();
    	conflito.addItem("Selecione um conflito...");
    	for (Conflito s : itens) 
    		conflito.addItem(s.getNome());
		
		JComboBox grupo = new JComboBox<String>();
		grupo.setPreferredSize(new Dimension(220, 30));
		JLabel lblgrupo = new JLabel("           Grupo Armado:  ");
		lblgrupo.setFont(new Font("Serif",Font.BOLD,15));
		
		ArrayList<GrupoArmado> itens2 = null;
    	ConfGrupoArmado config2 = new ConfGrupoArmado();
    	itens2 = config2.lista();
    	grupo.addItem("Selecione um grupo...");
    	for (GrupoArmado s : itens2) 
			grupo.addItem(s.getNome_grupo());
		
		f2.add(lblconflito);
		f2.add(conflito); 
		f2.add(lblgrupo);
		f2.add(grupo);
		
		
		
		JPanel f3 = new JPanel(new GridBagLayout());
		
		JButton botao = new JButton("Cadastrar");  
		botao.setPreferredSize(new Dimension(180, 30));
		f3.add(botao);
		
		

		
		cadastro.add(f2);
		cadastro.add(f3);
		
		
		
		
		botao.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		GruposXconflitos x = new GruposXconflitos();
	    		ConfGruposXconflitos config = new ConfGruposXconflitos();
	    		
	    		x.setNome_conflito((String)conflito.getSelectedItem());
	    		x.setNome_grupo((String)grupo.getSelectedItem());

	    		config.salvar(x);
	    		
	    		conflito.setSelectedIndex(0);
	    		grupo.setSelectedIndex(0);

	    	}
    	});
		
		
		
		
		return cadastro;
	}
	
	
	private JPanel paises() {

		JPanel cadastro = new JPanel(new GridLayout(4, 1));
		
		
		JPanel f1 = new JPanel(new GridBagLayout());
		
		JTextField pais = new JTextField(15);
		pais.setPreferredSize(new Dimension(100, 30));
		JLabel lblpais= new JLabel("Nome do Pais:  ");
		lblpais.setFont(new Font("Serif",Font.BOLD,15));
		
		JComboBox conflito = new JComboBox<String>();
		conflito.setPreferredSize(new Dimension(220, 30));
		JLabel lblconflito = new JLabel("         Conflito que participa:  ");
		lblconflito.setFont(new Font("Serif",Font.BOLD,15));
		
		ArrayList<Conflito> itens = null;
    	ConfConflitos config = new ConfConflitos();
    	itens = config.lista();
    	conflito.addItem("Selecione um conflito...");
    	for (Conflito s : itens) 
    		conflito.addItem(s.getNome());
			
		f1.add(lblpais);
		f1.add(pais); 
		f1.add(lblconflito);
		f1.add(conflito);
		
		
		JPanel f2 = new JPanel(new GridBagLayout());
		
		JButton botao = new JButton("Cadastrar");  
		botao.setPreferredSize(new Dimension(180, 30));
		f2.add(botao);
		
		cadastro.add(f1);
		cadastro.add(f2);
		
		
		
		botao.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Pais p = new Pais();
	    		ConfPais config = new ConfPais();
	    		
	    		
	    		p.setPais(pais.getText());
	    		p.setNome_conflito((String)conflito.getSelectedItem());

	    		config.salvar(p);
	    		
	    		pais.setText("");
	    		conflito.setSelectedIndex(0);

	    	}
    	});
		return cadastro;
	}
	

	private JPanel arma() {
		JPanel cadastro = new JPanel(new GridLayout(4, 1));
		
		
		JPanel f1 = new JPanel(new GridBagLayout());
		
		JTextField arma = new JTextField(15);
		arma.setPreferredSize(new Dimension(100, 30));
		JLabel lblarma = new JLabel("Nome da Arma:  ");
		lblarma.setFont(new Font("Serif",Font.BOLD,15));
		
		JTextField indicador = new JTextField(15);
		indicador.setPreferredSize(new Dimension(100, 30));
		JLabel lblindicador = new JLabel("          Indicador da Arma:  ");
		lblindicador.setFont(new Font("Serif",Font.BOLD,15));
		
		f1.add(lblarma);
		f1.add(arma); 
		f1.add(lblindicador);
		f1.add(indicador);
		
		JPanel f2 = new JPanel(new GridBagLayout());
		
		JButton botao = new JButton("Cadastrar");  
		botao.setPreferredSize(new Dimension(180, 30));
		f2.add(botao);
		
		cadastro.add(f1);
		cadastro.add(f2);
		
		
		
		botao.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		TipoArma tipoArma = new TipoArma();
	    		ConfTipoArma config = new ConfTipoArma();
	    		
	    		tipoArma.setNome_arma(arma.getText());
	    		tipoArma.setIndicador(Integer.parseInt(indicador.getText()));
	    		

	    		config.salvar(tipoArma);
	    		
	    		arma.setText("");
	    		indicador.setText("");


	    	}
    	});
		
		
		return cadastro;
	}

	
	private JPanel fornece() {
		JPanel cadastro = new JPanel(new GridLayout(4, 1));
		
		
		JPanel f1 = new JPanel(new GridBagLayout());
		
		JComboBox grupo = new JComboBox<String>();
		grupo.setPreferredSize(new Dimension(220, 30));
		JLabel lblgrupo = new JLabel("Grupo:  ");
		lblgrupo.setFont(new Font("Serif",Font.BOLD,15));
		
		ArrayList<GrupoArmado> itens = null;
    	ConfGrupoArmado config = new ConfGrupoArmado();
    	itens = config.lista();
    	grupo.addItem("Selecione um grupo...");
    	for (GrupoArmado s : itens) 
			grupo.addItem(s.getNome_grupo());
			
		
		JComboBox arma = new JComboBox<String>();
		arma.setPreferredSize(new Dimension(220, 30));
		JLabel lblarma = new JLabel("               Arma:  ");
		lblarma.setFont(new Font("Serif",Font.BOLD,15));
		
		ArrayList<TipoArma> itens2 = null;
    	ConfTipoArma config2 = new ConfTipoArma();
    	itens2 = config2.lista();
    	arma.addItem("Selecione uma arma...");
    	for (TipoArma s : itens2) 
    		arma.addItem(s.getNome_arma());
		
			
		f1.add(lblgrupo);
		f1.add(grupo); 
		f1.add(lblarma);
		f1.add(arma);
		
		
		
		JPanel f2 = new JPanel(new GridBagLayout());

		JTextField traficante = new JTextField(15);
		traficante.setPreferredSize(new Dimension(100, 30));
		JLabel lbltraficante= new JLabel("Nome do Traficante:  ");
		lbltraficante.setFont(new Font("Serif",Font.BOLD,15));
		
		JTextField numeroArmas = new JTextField(15);
		numeroArmas.setPreferredSize(new Dimension(100, 30));
		JLabel lblnumeroArmas= new JLabel("                 Numero de armas:  ");
		lblnumeroArmas.setFont(new Font("Serif",Font.BOLD,15));
		
		f2.add(lbltraficante);
		f2.add(traficante); 
		f2.add(lblnumeroArmas);
		f2.add(numeroArmas);
		
		
		
		JPanel f3 = new JPanel(new GridBagLayout());
		
		JButton botao = new JButton("Cadastrar");  
		botao.setPreferredSize(new Dimension(180, 30));
		f3.add(botao);
		
		cadastro.add(f1);
		cadastro.add(f2);
		cadastro.add(f3);
		
		
		
		botao.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Fornece fornece = new Fornece();
	    		ConfFornece config = new ConfFornece();
	    		
	    		fornece.setNome_grupo((String)grupo.getSelectedItem());
	    		fornece.setNome_arma((String)arma.getSelectedItem());
	    		fornece.setNome_traficante(traficante.getText());
	    		fornece.setNum_armas(Integer.parseInt(numeroArmas.getText()));



	    		config.salvar(fornece);
	    		
	    		grupo.setSelectedIndex(0);
	    		arma.setSelectedIndex(0);
	    		traficante.setText("");
	    		numeroArmas.setText("");

	    	}
    	});
		
		
		return cadastro;
	}
	

	private JPanel motivoConflito() {
		JPanel cadastro = new JPanel(new GridLayout(4, 1));
		
		
		

		JPanel f1 = new JPanel(new GridBagLayout());
		JComboBox conlfito = new JComboBox<String>();
		conlfito.setPreferredSize(new Dimension(220, 30));
		JLabel lblconflito = new JLabel("Conflito:  ");
		lblconflito.setFont(new Font("Serif",Font.BOLD,15));
		
		JTextField especificacao = new JTextField(15);
		especificacao.setPreferredSize(new Dimension(100, 30));
		JLabel lblespecificacao = new JLabel("           Escolha o conflito*  ");
		lblespecificacao.setFont(new Font("Serif",Font.BOLD,15));
		especificacao.setEnabled(false);
		
		
		ArrayList<Conflito> itens = null;
    	ConfConflitos config = new ConfConflitos();
    	itens = config.lista();
		conlfito.addItem("Escolha conflito...");
    	for (Conflito s : itens) 
    		conlfito.addItem(s.getNome());
		
		
		
		
		conlfito.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            	if(conlfito.getSelectedIndex() == 0) {
            		lblespecificacao.setText("           Escolha o conflito*  ");
        			especificacao.setText("");
        			especificacao.setEnabled(false);
            		return;
            	}
            	
            	
            	Conflito conflitoEscolhido = config.buscaUnica((String)conlfito.getSelectedItem());
            	
            	
            	//System.out.println("oq saiu: "+ conflitoEscolhido.getTipo());
            	String tipoSemEspaco = conflitoEscolhido.getTipo().trim();
            	
            	if(tipoSemEspaco.equals("territorial")) {
            		lblespecificacao.setText("            Nome da Regiao:  ");
        			especificacao.setText("");
        			especificacao.setEnabled(true);
            	}else if(tipoSemEspaco.equals("religioso")) {
            		lblespecificacao.setText("            Nome da Religiao:  ");
        			especificacao.setText("");
        			especificacao.setEnabled(true);
            	}else if(tipoSemEspaco.equals("racial")) {
            		lblespecificacao.setText("            Nome de Etinia:  ");
        			especificacao.setText("");
        			especificacao.setEnabled(true);
            	}else if(tipoSemEspaco.equals("economico")) {
            		lblespecificacao.setText("         Nome de Materia Prima:  ");
        			especificacao.setText("");
        			especificacao.setEnabled(true);
            	}else {
            		lblespecificacao.setText("           Escolha o conflito*  ");
        			especificacao.setText("");
        			especificacao.setEnabled(false);
            	}
                    	
            }
       	});
       	
       	
		
		f1.add(lblconflito);
		f1.add(conlfito); 
		f1.add(lblespecificacao);
		f1.add(especificacao);
		
		

		
		
		JPanel f2 = new JPanel(new GridBagLayout());
		JButton botao = new JButton("Cadastrar");  
		botao.setPreferredSize(new Dimension(180, 30));
		JLabel lblbotao = new JLabel("                             ");
		lblbotao.setFont(new Font("Serif",Font.BOLD,15));
		f2.add(lblbotao);
		f2.add(botao);
		
		
		
		
		
		
		
		
		botao.addActionListener(new ActionListener() {
		
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		Conflito conflitoEscolhido = config.buscaUnica((String)conlfito.getSelectedItem());
	    		
	    		String tipoSemEspaco = conflitoEscolhido.getTipo().trim();
            	
            	if(tipoSemEspaco.equals("territorial")) {
            		
            		ConflitoTerritorial conflito = new ConflitoTerritorial();
    	    		ConfConflitoTerritorial config = new ConfConflitoTerritorial();
    	    		
    	    		
    	    		conflito.setRegiao(especificacao.getText());
    	    		conflito.setCod_conflito(conflitoEscolhido.getCod_conflito());
    	  	    		
    	    		config.salvar(conflito);

            	}else if(tipoSemEspaco.equals("religioso")) {
            		ConflitoReligioso conflito = new ConflitoReligioso();
    	    		ConfConflitoReligiao config = new ConfConflitoReligiao();
    	    		
    	    		
    	    		conflito.setReligiao(especificacao.getText());
    	    		conflito.setCod_conflito(conflitoEscolhido.getCod_conflito());
    	  	    		
    	    		config.salvar(conflito);
            	}else if(tipoSemEspaco.equals("racial")) {
            		ConflitoRacial conflito = new ConflitoRacial();
    	    		ConfConflitoRacial config = new ConfConflitoRacial();
    	    		
    	    		
    	    		conflito.setEtnia(especificacao.getText());
    	    		conflito.setCod_conflito(conflitoEscolhido.getCod_conflito());
    	  	    		
    	    		config.salvar(conflito);
            	}else if(tipoSemEspaco.equals("economico")) {
            		ConflitoEconomico conflito = new ConflitoEconomico();
    	    		ConfConflitoEconomico config = new ConfConflitoEconomico();
    	    		
    	    		
    	    		conflito.setMat_prima(especificacao.getText());
    	    		conflito.setCod_conflito(conflitoEscolhido.getCod_conflito());
    	  	    		
    	    		config.salvar(conflito);
            	}else {

            	}
	    		
	    		lblespecificacao.setText("           Escolha o conflito*  ");
    			especificacao.setText("");
    			especificacao.setEnabled(false);
    			conlfito.setSelectedIndex(0);

	    	}

		});
		
		
		
		
		cadastro.add(f1);
		cadastro.add(f2);
		//cadastro.add(f3);

		
		return cadastro;
	}
}
