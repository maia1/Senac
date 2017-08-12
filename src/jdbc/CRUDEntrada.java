package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import jdbc.Conectar;
import model.Categorias;
import model.Entradas;
import model.Produtos;

public class CRUDEntrada {
    private Conectar conectar;
    private Connection connection;
    private PreparedStatement st;
    private ResultSet result;
    private String sql, resultado;
    private ArrayList<String> categorias;
    private int contador;int cont = 1;

   
    public CRUDEntrada(){
        categorias = new ArrayList<String>();
        conectar = new Conectar();
        connection = conectar.getConnection();
    }
    
    public void cadastrarProduto(Produtos produto, Date dtEnt, ArrayList<Entradas> entradas){
            Categorias cat = produto.getCategoria();
            
            int cod = buscarCodPRo(produto.getNome());
            int codCat = buscarCodigoCat(cat.getCategoria());
            String descricao = produto.getDescricao();
            String nome = produto.getNome();    
    
            if(cod == 0){
                
                try{
                    sql = "INSERT INTO tb_produtos(pro_nome, pro_cat_cod, pro_desc, pro_est_cod) VALUES (?,?,?,?)";
                    
                    st = connection.prepareStatement(sql);
                    
                    st.setString(1, nome);
                    
                    st.setInt(2, codCat);
                   
                    st.setString(3, descricao);
                                       
                    st.setInt(4, cont++);
                    
                    st.executeUpdate();
                
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Erro no cadastro!");
                }
                    cadastrarDataEnt(dtEnt, entradas);
                }else{
                    cadastrarDataEnt(dtEnt, entradas); 
                } 
    }   
   
    
    public void cadastrar(ArrayList<Entradas> entradas, Date dtEnt){
        contador = 0;
        for (Entradas entrada : entradas){
            cadastrarProduto(entrada.getProduto(), dtEnt, entradas);
        }
    }    
    
    public void cadastrarDataEnt(Date dtEnt,ArrayList<Entradas> entradas){
        java.util.Date data = dtEnt;
        java.sql.Date dataSql = new java.sql.Date(data.getTime());
        
        if (contador == 0){
            try{
                sql = "Insert Into tb_entradas (ent_dt) values(?)";
                st = connection.prepareStatement(sql);
                st.setDate(1, dataSql);
                st.executeUpdate();
            }catch(SQLException se){
                resultado = "Erro!";
            }
            contador = contador+1;
        }else{
            
        }
        cadastrarItensentrada(entradas);
    }
        
    public void cadastrarItensentrada(ArrayList<Entradas> entradas){
        for (Entradas entrada : entradas){
            
            java.util.Date data = entrada.getDataVal();
            java.sql.Date dataSql = new java.sql.Date(data.getTime());
            
            Produtos prod = entrada.getProduto();
            
            int codPro = buscarCodPRo(prod.getNome());
            int codEntr = codEnt();
            int quantidade = entrada.getQuantidade();

            try{
                    sql = "Insert Into tb_it_ent (it_ent_pro_cod, it_ent_ent_cod, it_ent_qtd, it_ent_dt_val) values(?,?,?,?)";
                    st = connection.prepareStatement(sql);
                    st.setInt(1, codPro);
                    st.setInt(2, codEntr);
                    st.setInt(3, quantidade);
                    st.setDate(4, dataSql);
                    st.executeUpdate();
                }catch(SQLException e){
                    resultado = "Erro";
                }
        } 
        int codEntH = codEnt();
        JOptionPane.showMessageDialog(null,"Esse é o Código da sua entrada" + "\n" +codEntH );
    }
    
    public int buscarCodPRo(String nome) {
        int cod = 0;
        try{
            sql = "Select pro_cod From tb_produtos where pro_nome = ?";
            st = connection.prepareStatement(sql);
            st.setString(1, nome);
            result = st.executeQuery();
            while (result.next()){
                cod = result.getInt(1);
            }   
            return cod; 
        } catch(SQLException e){
                resultado = "Erro" ;
                return 0;    
        }
        
    }
    
    public int codEnt(){
        int cod = 0;
        try{
            sql = "select top 1 ent_cod from tb_entradas order by ent_cod desc";
            st = connection.prepareStatement(sql);
            result = st.executeQuery();
            while(result.next()){
                cod = result.getInt(1);
            }
            return cod;
        }catch(SQLException e ){
                resultado = "Erro";
                return 0;
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
    
    public int buscarCodigoCat (String categoria){
        int codCat = 0;
        try{
            sql = "Select cat_cod from tb_categorias  where cat_nome = ?";
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
  
//    public void buscar(){}
//    public void editar(){}
//    public void excluir(){}
}
