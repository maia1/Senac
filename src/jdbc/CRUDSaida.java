package jdbc;


import excecoes.ProdutoNaoEncontrado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Produtos;
import model.Saidas;

public class CRUDSaida {
    private Conectar conectar;
    private Connection connection;
    private PreparedStatement st;//Padrão
    private ResultSet result;//Padrão
    private String sql, sql2,sql3, sql4, resultado;
    private ArrayList<String> produtos;
    private ArrayList<Saidas> saidas;
    private ArrayList<String> pedidos;
    private ArrayList<String> categorias;
   
     
    public CRUDSaida(){
        conectar = new Conectar();
        connection = conectar.getConnection();
        saidas = new ArrayList<Saidas>();
        pedidos = new ArrayList<String>();
        produtos = new ArrayList<String>();
        categorias = new ArrayList<String>();
  
     }
    
    public void cadastrar(ArrayList<Saidas> saidas){
        for (Saidas saida : saidas){
        
        java.util.Date data1 = saida.getDataSaida();
        java.sql.Date dataSaidaSQL = new java.sql.Date(data1.getTime());
        
        try{
        sql = "insert into tb_saidas(sai_dt) VALUES (?);";
        st = connection.prepareStatement(sql);
        st.setDate(1, dataSaidaSQL);
        st.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro na Data Saída!");

        }  
        //Produtos prod = saida.getProdutos();
            
        //int codPro = buscarCodPRo(prod.getNome());
       
        int codSaid = codSai();
        int codProd = codPRo();
        try{
            
        sql2 = "insert into tb_it_sai(it_sai_qtd, it_sai_pro_cod, it_sai_sai_cod) VALUES (?,?,?);";
        st = connection.prepareStatement(sql2);
        st.setInt(1,saida.getQuantidadeSai());
        st.setInt(2, codProd);
        st.setInt(3, codSaid);
        st.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro nos Itens Saída!");
        }
        //JOptionPane.showMessageDialog(,null,dataSaidaSQL);
        
    }
    
        try{
            
        sql3 = "insert into tb_estoques(est_arm) Values (?);";
        st = connection.prepareStatement(sql3);
        st.setString(1, "Nutrição");
        st.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro no Armazem Saída!");
        }
        //JOptionPane.showMessageDialog(,null,dataSaidaSQL);
        }
    
    public ArrayList<String> listarProdutos(){
            
            produtos.clear();    
        try{ 
            sql = "SELECT * FROM tb_produtos\n" +
                    "INNER JOIN tb_estoques\n" +
                    "ON tb_produtos.pro_est_cod = tb_estoques.est_cod\n" +
                    "WHERE tb_estoques.est_arm = 'Nutrição';";
            
            st = connection.prepareStatement(sql);
            //st.setInt(1, 2);
            
            
            result = st.executeQuery();
            while(result.next()){
                produtos.add(result.getString(2));//Refere-se à segunda coluna buscada pelo sql          
            }
            return produtos;
        }catch(SQLException e){
            resultado = "Erro!";
            return null;
        }
    }
    
    public int codSai(){
        int cod = 0;
        try{
            //sql = "select top 1 ent_cod from tb_entradas order by ent_cod desc";
            sql = "select sai_cod from tb_saidas order by sai_cod desc LIMIT 1";
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
    public int codPRo(String nome) {
        int cod = 0;
        try{
            sql = "Select pro_cod From tb_produtos where pro_nome = ?";
            st = connection.prepareStatement(sql);
            st.setString(1, produtos.get(cod));
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
    
    private int buscarProEstoque(String nome){
        try{
            int codigo = 0;
           
            sql ="SELECT tb_produtos.pro_cod, tb_produtos.pro_nome,tb_estoques.est_qtd,tb_it_sai.it_sai_qtd \n" +
"from tb_produtos\n" +
"INNER JOIN tb_estoques\n" +
"on tb_produtos.pro_est_cod = tb_estoques.est_cod\n" +
"INNER JOIN tb_it_sai\n" +
"on tb_it_sai.it_sai_pro_cod = tb_produtos.pro_cod;";
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
    
    public Saidas buscar(String nome){
        Saidas sai = new Saidas();
        return sai;
    }
       public void excluir(String nome){}
    
    

    
    // Maia
    
    
    
    
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
    //    private int buscarCodProduto() {
//        try{
//            int codProduto =0;
//            sql = "SELECT prod_codigo FROM tb_produto WHERE prod_nome = ?;";
//            st = connection.prepareStatement(sql);
//            st.setString(1,buscarCodProduto());
//            result = st.executeQuery();
//            while(result.next()){
//                codProduto = result.getInt(1);
//            }
//            return codProduto;
//        }catch(SQLException e){
//            resultado = "Erro!";
//            return 0;
//        }
//    }

//    private int buscarProduto(String nome){
//            try{
//                int codigo = 0;
//                sql ="select pro_cod from tb_produtos where pro_nome  = ?;";
//                st = connection.prepareStatement(sql);
//                st.setString(1, nome);
//                result = st.executeQuery();//Está atualizando a tabela do banco
//                while (result.next()){
//                    codigo = result.getInt(1);//Esse 1 refere a coluna 1 do banco de dados
//                }
//                return codigo;
//            }catch(SQLException e){
//                resultado = "Erro!";
//                JOptionPane.showMessageDialog(null, "ERRO");
//                return 0;
//            }
//    }
       
}
