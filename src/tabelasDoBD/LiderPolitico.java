package tabelasDoBD;

import confTabelasJavaBD.ConfGrupoArmado;

public class LiderPolitico {

    private String nome_grupo;
    private String nome_lider;
    private int cod_grupo;
    private String apoios;
    
    
    public String getNome_grupo(){
        return this.nome_grupo;
    }

    public void setNome_grupo(String novo){
        this.nome_grupo = novo;
        identificaGrupo();
    }
    
    public String getNome_lider(){
        return this.nome_lider;
    }

    public void setNome_lider(String novo){
        this.nome_lider = novo;
    }

    public int getCod_grupo(){
        return this.cod_grupo;
    }

    public void setCod_grupo(int novo){
        this.cod_grupo = novo;
    }

    public String getApoios(){
        return this.apoios;
    }

    public void setApoios(String novo){
        this.apoios = novo;
    }
    
    
    private void identificaGrupo() {
    	ConfGrupoArmado config = new ConfGrupoArmado();
    	GrupoArmado grupo = config.buscaUnica(getNome_grupo());
    	setCod_grupo(grupo.getCod_grupo());
	}
}
