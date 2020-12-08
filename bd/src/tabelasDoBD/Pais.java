package tabelasDoBD;

import confTabelasJavaBD.ConfConflitos;

public class Pais {

    private int cod_conflito;
    private String pais;
    private String nome_conflito;

    
    public String getNome_conflito() {
        return nome_conflito;
    }

    public void setNome_conflito(String cod_grupo) {
        this.nome_conflito = cod_grupo;
        identificaConflito();
        
    }
    

    public int getCod_conflito() {
        return cod_conflito;
    }

    public void setCod_conflito(int cod_conflito) {
        this.cod_conflito = cod_conflito;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
  
    
    
    private void identificaConflito() {
    	ConfConflitos config = new ConfConflitos();
    	Conflito conf = config.buscaUnica(getNome_conflito());
    	setCod_conflito(conf.getCod_conflito());
	}
    
}
