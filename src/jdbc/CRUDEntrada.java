package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Entradas;

public class CRUDEntrada {
    private Conectar conectar;
    private Connection connection;
    private PreparedStatement st;
    private ResultSet result;
    private String sql, resultado;
   
    public CRUDEntrada(){
        conectar = new Conectar();
        connection = conectar.getConnection();
    }
    
    public void cadastrar(ArrayList<Entradas> entradas){
        Entradas ent = new Entradas();
        for (Entradas entrada : entradas){
            ent = entrada;
        } 
    try{
        sql = "Insert Into tb_entradas (ent_produto)";
        st = connection.prepareStatement(sql);
        st.setString(1, ent.getProduto());
    }catch(SQLException se){
        resultado = "Erro!";
    }
    
    }
    public void buscar(){}
    public void editar(){}
    public void excluir(){}
}
