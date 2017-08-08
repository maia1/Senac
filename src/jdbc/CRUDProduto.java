
package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Categorias;
import model.Produtos;


public class CRUDProduto {
    private Conectar conectar;
    private Connection connection;
    private PreparedStatement st;
    private ResultSet result;
    private String sql, resultado;
    private SimpleDateFormat sdf;
    private ArrayList<String> categorias;
    
    public CRUDProduto(){
        conectar = new Conectar();
        connection = conectar.getConnection();
        categorias = new ArrayList<String>();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    public void cadastrar(Produtos produto, String categoria){
        
        java.util.Date data1 = produto.getDataFabricacao();
        java.sql.Date dataFabSQL = new java.sql.Date(data1.getTime());
        
        java.util.Date data2 = produto.getDataValidade();
        java.sql.Date dataValSQL = new java.sql.Date(data2.getTime());
        int codigo = buscarCodigoCat(categoria);
        try{
            sql = "INSERT INTO tb_produto(pro_nome, pro_dt_val, pro_dt_fab, pro_cat_cod, pro_arm_cod) VALUES (?,?,?,?,?);";
            st = connection.prepareStatement(sql);
            st.setString(1, produto.getNome());
            st.setDate(2, dataValSQL);
            st.setDate(3, dataFabSQL);
            st.setInt(4,codigo);
            st.setInt(5, 2);
            st.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro no cadastaro!");
        }
    }
    
    public Produtos buscar(String nome ){
        
        Produtos pro = null;
        Categorias categoria = new Categorias();
        int i = 0;
        try{
                sql = "SELECT * FROM tb_produto as pro inner join tb_categoria as cat "
                        + "on pro.pro_cat_cod = cat.cat_cod where pro_nome = ? order by pro_dt_val ";
                
                st = connection.prepareStatement(sql);
                st.setString(1, nome);
                result = st.executeQuery();
                while(result.next()){
                    if(i == 0){
                        pro = new Produtos();
                        categoria.setCategoria(result.getString(8));
                        pro.setCodigo(result.getInt(1));
                        pro.setNome(result.getString(2));
                        pro.setCategoria(categoria);
                        pro.setDataFabricacao(result.getDate(3));
                        pro.setDataValidade(result.getDate(4));
                        i = i +1;
                    }
            }
            return pro;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Deu Ruim");
            resultado = "Erro!";
            return null;
        }
    }
    
    
    public void editar(Produtos produto, String categoria){
        java.util.Date data1 = produto.getDataFabricacao();
        java.sql.Date dataFabSQL = new java.sql.Date(data1.getTime());
        
        java.util.Date data2 = produto.getDataValidade();
        java.sql.Date dataValSQL = new java.sql.Date(data2.getTime());
        
        int codigo = buscarCodigoCat(categoria);
        
        try{
            sql = "UPDATE tb_produto SET pro_nome = ? , pro_dt_val = ?, pro_dt_fab = ? , pro_cat_cod = ? where pro_cod = ?;";
            st = connection.prepareStatement(sql);
            st.setString(1, produto.getNome());
            st.setDate(2, dataValSQL);
            st.setDate(3, dataFabSQL);
            st.setInt(4, codigo);
            st.setInt(5, produto.getCodigo());

            st.executeUpdate();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro na Edição!");
        }
    }
    
    public void excluir(int cod ){
        try{
            sql = "delete from tb_produto where pro_cod = ?;";
            st = connection.prepareStatement(sql);
            st.setInt(1, cod);
            result = st.executeQuery();
        }catch(SQLException e ){
           resultado = "Erro";
       }
    }
    
  
    public ArrayList<String> categorias(){
        categorias.clear();
        try{
            sql = "SELECT * From tb_categoria";
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
    
    public int buscarCodigoCat (String categoria){
        int codCat = 0;
        try{
            sql = "Select cat_cod from tb_categoria  where cat_nome = ?";
            st = connection.prepareStatement(sql);
            st.setString(1, categoria);
            result = st.executeQuery();
            while(result.next()){
                codCat = result.getInt(1);
            } 
        return codCat;
        }catch(SQLException e){
            resultado = "Erro";
            return 0;
        }
    }
}
