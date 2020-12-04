package geral;

import java.sql.*;

import javax.swing.JOptionPane;

public class Conexao{
	private ResultSet rs;
	private Statement stm;
    private String usuario;
    private String senha;
    private String url;
    private Connection con;
    
    public Conexao(){
        url = "jdbc:postgresql://localhost:5432/EP3.4";
        usuario = "userPostgres";
        senha = "ramed";

    }

    public void conexao(){
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, usuario, senha);

        } catch(Exception e ){
        	System.out.println("nao deu");
            e.printStackTrace();
        }
    }
    
    public void desconexao() {
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public Connection getConnection(){
        return this.con;
    }

    public void setConnection(Connection novo){
        this.con = novo;
    }
    
    
    public Statement getStatement(){
        return this.stm;
    }

    public void setStatement(Statement novo){
        this.stm = novo;
    }
    
    
    public ResultSet getResultSet(){
        return this.rs;
    }

    public void setResultSet(ResultSet novo){
        this.rs = novo;
    }
    
    public boolean testaConexao() {
    	 try{
             Class.forName("org.postgresql.Driver");
             con = DriverManager.getConnection(url, usuario, senha);
             desconexao();
             return true;

         } catch(Exception e ){
             e.printStackTrace();
             return false;
         }
    }
    
    public void executaSQL(String sql) {
    	try {
			stm = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao executar SQL", "ATENCAO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
    	
    	
    }
    
    
}