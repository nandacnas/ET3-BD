package tabelasDoBD;

public class Conflito {

    private String nome;
    private int cod_conflito;
    private String tipo;
    private int num_feridos;
    private int num_mortos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCod_conflito() {
        return cod_conflito;
    }

    public void setCod_conflito(int cod_conflito) {
        this.cod_conflito = cod_conflito;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNum_feridos() {
        return num_feridos;
    }

    public void setNum_feridos(int num_feridos) {
        this.num_feridos = num_feridos;
    }

    public int getNum_mortos() {
        return num_mortos;
    }

    public void setNum_mortos(int num_mortos) {
        this.num_mortos = num_mortos;
    }


}
