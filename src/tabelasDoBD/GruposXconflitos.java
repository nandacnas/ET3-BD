package tabelasDoBD;

import confTabelasJavaBD.ConfConflitos;
import confTabelasJavaBD.ConfGrupoArmado;

public class GruposXconflitos {

    private int cod_conflito;
    private int cod_grupo;
    
    private String nome_grupo;
    private String nome_conflito;


    public String getNome_grupo(){
        return this.nome_grupo;
    }

    public void setNome_grupo(String novo){
        this.nome_grupo = novo;
        identificaGrupo();
    }
    
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

    public int getCod_grupo() {
        return cod_grupo;
    }

    public void setCod_grupo(int cod_grupo) {
        this.cod_grupo = cod_grupo;
    }
    
    private void identificaGrupo() {
    	ConfGrupoArmado config = new ConfGrupoArmado();
    	GrupoArmado grupo = config.buscaUnica(getNome_grupo());
    	setCod_grupo(grupo.getCod_grupo());
	}
    
    private void identificaConflito() {
    	ConfConflitos config = new ConfConflitos();
    	Conflito conf = config.buscaUnica(getNome_conflito());
    	setCod_conflito(conf.getCod_conflito());
	}

}
