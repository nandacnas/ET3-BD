package tabelasDoBD;

import java.util.Calendar;

public class Ds_media {

    private int cod_organizacao;
    private int cod_conflito;
    private Calendar ds_media;

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

    public Calendar getDs_media() {
        return ds_media;
    }

    public void setDs_media(Calendar ds_media) {
        this.ds_media = ds_media;
    }
    
}
