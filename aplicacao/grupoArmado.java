public class grupoArmado {

    private int cod_grupo;
    private String nome_grupo;
    private int num_baixas_grupo;

    public int getCod_grupo(){
        return this.cod_grupo;
    }

    public void setCod_grupo(int novo){
        this.cod_grupo = novo;
    }

    public String getNome_grupo(){
        return this.nome_grupo;
    }

    public void setCod_grupo(String novo){
        this.nome_grupo = novo;
    }

    public int getNum_baixas_grupo(){
        return this.num_baixas_grupo;
    }

    public void setNum_baixas_grupo(int novo){
        this.num_baixas_grupo = novo;
    }
}
