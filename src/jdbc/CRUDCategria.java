package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.spi.DirStateFactory;
import javax.swing.JOptionPane;

public class CRUDCategria {
    private String sql, resultado;
    private Connection connection;
    private Conectar conectar;
    private ResultSet result;
    private PreparedStatement st;
    private ArrayList<String> categorias;
    
    public CRUDCategria() {
        categorias = new ArrayList<String>();
        conectar = new Conectar();
        connection = conectar.getConnection();
    }
    
    public void cadastrar(String nome){
        try{
            sql = "INSERT INTO tb_categorias (cat_nome) VALUES(?);";
            st = connection.prepareStatement(sql);
            st.setString(1, nome);
            st.executeUpdate();
        }catch(SQLException e){
            resultado = "Erro";
            JOptionPane.showMessageDialog(null, "Erro no cadastro!");
        }
    }
    
    public void excluir (String nome){
        try{
            sql = "DELETE FROM tb_categorias WHERE tb_categorias.cat_nome = ?";
            st = connection.prepareStatement(sql);
            st.setString(1, nome);
            st.executeUpdate();
        }catch(SQLException e){
            resultado = "Erro";
            JOptionPane.showMessageDialog(null, "Erro na Exclusão");
        }
    }
    
    public void editar(String nome , String novoNome){
        try{
            sql = "UPDATE tb_categorias SET cat_nome = ? WHERE cat_nome = ?;";
            st = connection.prepareStatement(sql);
            st.setString(1, novoNome);
            st.setString(2, nome);
            st.executeUpdate();
        }catch(SQLException e){
            resultado = "Erro";
        }
    }
 
    public ArrayList<String> categorias(){
        categorias.clear();
        try{
            sql = "SELECT * From tb_categorias";
            st = connection.prepareStatement(sql);
            result = st.executeQuery();
            while(result.next()){
                categorias.add(result.getString(2));
            }
            return categorias;
        }catch(SQLException e){
            resultado = "Erro";
            return null;
        }
    }

}
