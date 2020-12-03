public class divisao {

    private int num_divisao;
    private int cod_grupo;
    private int num_baixas_divisao;
    private int homens;
    private int barcos;
    private int avioes;
    private int tanques;

    public int getNum_divisao(){
        return this.num_divisao;
    }

    public void setNum_divisao(int novo){
        this.num_divisao = novo;
    }
    
    public int getCod_grupo(){
        return this.cod_grupo;
    }

    public void setCod_grupo(int novo){
        this.cod_grupo = novo;
    }

    public int getNum_baixas_divisao(){
        return this.num_baixas_divisao;
    }

    public void setNum_baixas_divisao(int novo){
        this.num_baixas_divisao = novo;
    }

    public int getHomens(){
        return this.homens;
    }

    public void setHomens(int novo){
        this.homens = novo;
    }

    public int getAvioes(){
        return this.avioes;
    }

    public void setAvioes(int novo){
        this.avioes = novo;
    }

    public int getBarcos(){
        return this.barcos;
    }

    public void setBarcos(int novo){
        this.barcos = novo;
    }

    public int getTanques(){
        return this.tanques;
    }

    public void setTanques(int novo){
        this.tanques = novo;
    }
}
