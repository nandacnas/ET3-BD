package tabelasDoBD;

import confTabelasJavaBD.ConfGrupoArmado;

public class Fornece {

    private int cod_grupo;
    private String nome_arma;
    private String nome_traficante;
    private int num_armas;

    private String nome_grupo;
    

    public String getNome_grupo(){
        return this.nome_grupo;
    }

    public void setNome_grupo(String novo){
        this.nome_grupo = novo;
        identificaGrupo();
    }
    
    
    
    public int getCod_grupo() {
        return cod_grupo;
    }

    public void setCod_grupo(int cod_grupo) {
        this.cod_grupo = cod_grupo;
    }

    public String getNome_arma() {
        return nome_arma;
    }

    public void setNome_arma(String nome_arma) {
        this.nome_arma = nome_arma;
    }

    public String getNome_traficante() {
        return nome_traficante;
    }

    public void setNome_traficante(String nome_traficante) {
        this.nome_traficante = nome_traficante;
    }

    public int getNum_armas() {
        return num_armas;
    }

    public void setNum_armas(int num_armas) {
        this.num_armas = num_armas;
    }
    
    
    
    
    
    
    
    
    
    private void identificaGrupo() {
    	ConfGrupoArmado config = new ConfGrupoArmado();
    	GrupoArmado grupo = config.buscaUnica(getNome_grupo());
    	setCod_grupo(grupo.getCod_grupo());
	}
    

}
