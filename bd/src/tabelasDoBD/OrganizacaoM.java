package tabelasDoBD;

public class OrganizacaoM {

    private int cod_organizacao;
    private int num_pessoas;
    private int depende_id;
    private String nome_org;
    private String tipo_org;
    private String tipo_ajuda;

    public int getCod_organizacao(){
        return this.cod_organizacao;
    }

    public void setCod_organizacao(int novo){
        this.cod_organizacao = novo;
    }

    public int getNum_pessoas(){
        return this.num_pessoas;
    }

    public void setNum_pessoas(int novo){
        this.num_pessoas = novo;
    }

    public int getDepende_id(){
        return this.depende_id;
    }

    public void setDepende_id(int novo){
        this.depende_id = novo;
    }

    public String getNome_org(){
        return this.nome_org;
    }

    public void setNome_org(String novo){
        this.nome_org = novo;
    }

    public String getTipo_org(){
        return this.tipo_org;
    }

    public void setTipo_org(String novo){
        this.tipo_org = novo;
    }

    public String getTipo_ajuda(){
        return this.tipo_ajuda;
    }

    public void setTipo_ajuda(String novo){
        this.tipo_ajuda = novo;
    }

    
}
