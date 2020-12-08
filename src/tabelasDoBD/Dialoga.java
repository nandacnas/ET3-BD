package tabelasDoBD;

import confTabelasJavaBD.ConfGrupoArmado;
import confTabelasJavaBD.ConfOrganizacaoM;

public class Dialoga {

    private int cod_grupo;
    private String nome_lider;
    private int cod_organizacao;
    
    private String nome_org;
    private String nome_grupo;

    public String getNome_org() {
        return nome_org;
    }

    public void setNome_org(String cod_grupo) {
        this.nome_org = cod_grupo;
        identificaOrg();
        
    }
    
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

    public String getNome_lider() {
        return nome_lider;
    }

    public void setNome_lider(String nome_lider) {
        this.nome_lider = nome_lider;
    }

    public int getCod_organizacao() {
        return cod_organizacao;
    }

    public void setCod_organizacao(int cod_organizacao) {
        this.cod_organizacao = cod_organizacao;
    }

    
    
    
    private void identificaOrg() {
    	ConfOrganizacaoM config = new ConfOrganizacaoM();
    	OrganizacaoM org = config.buscaUnica(getNome_org());
    	setCod_organizacao(org.getCod_organizacao());
	}
    
    
    private void identificaGrupo() {
    	ConfGrupoArmado config = new ConfGrupoArmado();
    	GrupoArmado grupo = config.buscaUnica(getNome_grupo());
    	setCod_grupo(grupo.getCod_grupo());
	}
    
}
