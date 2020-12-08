package tabelasDoBD;

import confTabelasJavaBD.ConfGrupoArmado;

public class ChefeMilitar {

    private int cod_chefe;
    private String faixa;
    private String LP_nome_lider;
    private int LP_cod_grupo;
    private int d_nro_divisao;
    private int d_cod_grupo;
    private String LP_nome_grupo;
    private String d_nome_grupo;

    
    
    
    public String getLP_nome_grupo(){
        return this.LP_nome_grupo;
    }

    public void setLP_nome_grupo(String novo){
        this.LP_nome_grupo = novo;
        identificaGrupoLP();
    }
    
    public String getD_nome_grupo(){
        return this.d_nome_grupo;
    }

    public void setD_nome_grupo(String novo){
        this.d_nome_grupo = novo;
        identificaGrupoD();
        
    }
    
    
    
    public int getCod_chefe(){
        return this.cod_chefe;
    }

    public void setCod_chefe(int novo){
        this.cod_chefe = novo;
    }

    public String getFaixa(){
        return this.faixa;
    }

    public void setFaixa(String novo){
        this.faixa = novo;
    }

    public String getLP_nome_lider(){
        return this.LP_nome_lider;
    }

    public void setLP_nome_lider(String novo){
        this.LP_nome_lider = novo;
    }

    public int getLP_cod_grupo(){
        return this.LP_cod_grupo;
    }

    public void setLP_cod_grupo(int novo){
        this.LP_cod_grupo = novo;
    }

    public int getD_nro_divisao(){
        return this.d_nro_divisao;
    }

    public void setD_nro_divisao(int novo){
        this.d_nro_divisao = novo;
    }

    public int getD_cod_grupo(){
        return this.d_cod_grupo;
    }

    public void setD_cod_grupo(int novo){
        this.d_cod_grupo = novo;
    }
    
    
    private void identificaGrupoLP() {
    	ConfGrupoArmado config = new ConfGrupoArmado();
    	GrupoArmado grupo = config.buscaUnica(getLP_nome_grupo());
    	setLP_cod_grupo(grupo.getCod_grupo());
	}
    
    private void identificaGrupoD() {
    	ConfGrupoArmado config = new ConfGrupoArmado();
    	GrupoArmado grupo = config.buscaUnica(getD_nome_grupo());
    	setD_cod_grupo(grupo.getCod_grupo());
	}
    
    
    
    
    
    
    
}
