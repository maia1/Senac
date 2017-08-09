package jdbc;


import excecoes.ProdutoNaoEncontrado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Saidas;

public class CRUDSaida {
    private Conectar conectar;
    private Connection connection;
    private PreparedStatement st;//Padrão
    private ResultSet result;//Padrão
    private String sql, sql2, resultado;
    private ArrayList<String> produtos;
    private ArrayList<Saidas> saidas;
    private ArrayList<String> pedidos;
   
     
    public CRUDSaida(){
        conectar = new Conectar();
        connection = conectar.getConnection();
        saidas = new ArrayList<Saidas>();
        pedidos = new ArrayList<String>();
        produtos = new ArrayList<String>();
  
     }
    
    public void cadastrar(ArrayList<Saidas> saidas) throws ProdutoNaoEncontrado, SQLException{
        for (Saidas saida : saidas){
        
        java.util.Date data1 = saida.getDataSaida();
        java.sql.Date dataSaidaSQL = new java.sql.Date(data1.getTime());
        
        try{
        sql = "insert into tb_saidas(sai_dt) VALUES (?);";
        st = connection.prepareStatement(sql);
        st.setDate(1, dataSaidaSQL);
        st.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro na Saída!");
        }
        try{
        sql2 = "insert into tb_it_sai(it_sai_qtd, it_sai_pro_cod, it_sai_sai_cod) VALUES (?,?,?);";
        st = connection.prepareStatement(sql2);
        st.setInt(1,saida.getQuantidadeSai());
        st.setInt(2, 1);
        st.setInt(3, 1);
        st.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro na Saída!");
        }
        //JOptionPane.showMessageDialog(,null,dataSaidaSQL);
        }
    }

    public Saidas buscar(String nome){
        Saidas sai = new Saidas();
        return sai;
    }
       public void excluir(String nome){}
    
    private int buscarProdutos(String nome){
        try{
            int codigo = 0;
            sql ="SELECT prod_codigo FROM tb_produtos WHERE prod_nome = ?;";
            st = connection.prepareStatement(sql);
            st.setString(1, nome);
            result = st.executeQuery();//Está atualizando a tabela do banco
            while (result.next()){
                codigo = result.getInt(1);//Esse 1 refere a coluna 1 do banco de dados
            }
            return codigo;
        }catch(SQLException e){
            resultado = "Erro!";
            JOptionPane.showMessageDialog(null, "ERRO");
            return 0;
        }
    }

    public ArrayList<String> listarProdutos(){
            
            produtos.clear();    
        try{ 
            sql = "SELECT * FROM tb_produtos WHERE pro_est_cod = ?";
            
            st = connection.prepareStatement(sql);
            st.setInt(1, 2);
            
            result = st.executeQuery();
            while(result.next()){
                produtos.add(result.getString(2));          
            }
            return produtos;
        }catch(SQLException e){
            resultado = "Erro!";
            return null;
        }
    }
       
}
