package tabelasDoBD;

import java.util.Calendar;

import confTabelasJavaBD.ConfConflitos;
import confTabelasJavaBD.ConfOrganizacaoM;

public class De_media {

    private int cod_organizacao;
    private int cod_conflito;
    private String de_media;
    
    private String nome_org;
    private String nome_conflito;

    public String getNome_org() {
        return nome_org;
    }

    public void setNome_org(String cod_grupo) {
        this.nome_org = cod_grupo;
        identificaOrg();
        
    }
    
    public String getNome_conflito() {
        return nome_conflito;
    }

    public void setNome_conflito(String cod_grupo) {
        this.nome_conflito = cod_grupo;
        identificaConflito();
        
    }
    

    public int getCod_organizacao() {
        return cod_organizacao;
    }

    public void setCod_organizacao(int cod_organizacao) {
        this.cod_organizacao = cod_organizacao;
    }

    public int getCod_conflito() {
        return cod_conflito;
    }

    public void setCod_conflito(int cod_conflito) {
        this.cod_conflito = cod_conflito;
    }

    public String getDe_media() {
        return de_media;
    }

    public void setDe_media(String de_media) {
        this.de_media = de_media;
    }
    
    
    private void identificaOrg() {
    	ConfOrganizacaoM config = new ConfOrganizacaoM();
    	OrganizacaoM org = config.buscaUnica(getNome_org());
    	setCod_organizacao(org.getCod_organizacao());
	}
    
    private void identificaConflito() {
    	ConfConflitos config = new ConfConflitos();
    	Conflito conf = config.buscaUnica(getNome_conflito());
    	setCod_conflito(conf.getCod_conflito());
	}
    
}
