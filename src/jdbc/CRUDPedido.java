package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jdbc.Conectar;
import model.Pedidos;
import model.Produtos;

public class CRUDPedido {
    private Conectar conectar;
    private Connection connection;
    private PreparedStatement st;
    private ResultSet result;
    private String sql, sql1, sql2,sql3, resultado;
    private SimpleDateFormat sdf;
    private ArrayList<Pedidos> pedido;
    private ArrayList<String> produtos;
    
    public CRUDPedido(){
        conectar = new Conectar();
        connection = conectar.getConnection();
        pedido = new ArrayList<Pedidos>();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        produtos = new ArrayList<String>();
    }

    public void cadastrar(ArrayList<Pedidos> pedidos){
        int contador = 0;
        for(Pedidos pedido: pedidos){
            java.util.Date data = pedido.getDtPedido();
            java.sql.Date dataSQL = new java.sql.Date(data.getTime());
            
            
            JOptionPane.showMessageDialog(null, dataSQL);
           
            int codEst = 0;
            int qtdEst = 30;
            
            //Aba: Inserir data pedido
            if(contador == 0){
                try{
                    sql1 = "INSERT INTO tb_pedidos (ped_dt_pedido, ped_atendido) VALUES ( ?, ?);";
                    st = connection.prepareStatement(sql1);
                    st.setDate(1, dataSQL);
                    st.setInt(2 , 1);
                    st.executeUpdate();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Erro no pedido");
                }
                contador = contador + 1;
            }
//            int resultado1 = 0;
//                if(pedido.getQuantidade() > qtdEst){
//                    JOptionPane.showMessageDialog(null, "Quantidado insuficiente");
//                }else{
                    
                    //resultado1 = qtdEst - pedido.getQuantidade();
                    //moviEsto(codEst,resultado1);
                    
                    //Aba: buscar
                    int codProd = codPro(pedido.getNomePro());
                    int pedQTD = pedido.getQuantidade();
                    int codPedido = codPed();
                    
                    //Aba: Inserir o iten pedido

                    try{
                        sql2 = "INSERT INTO tb_it_ped (it_ped_qtd, it_ped_pro_cod, it_ped_ped_cod) VALUES (?,?,?)";
                        st = connection.prepareStatement(sql2);
                        JOptionPane.showMessageDialog(null, pedQTD);
                        st.setInt(1, pedQTD);
                        st.setInt(2, codProd);
                        st.setInt(3, codPedido);
                        st.executeUpdate();
                    }catch(SQLException e){
                        JOptionPane.showMessageDialog(null, "Erro nos itens do pedido");
                    }
                    
//                    if(resultado1 == 0){
//                        try{
//                            sql3 = "DELETE FROM tb_estoque WHERE tb_estoques.est_cod = ?";
//                            st = connection.prepareStatement(sql3);
//                            st.setInt(1, codEst);
//                            st.executeUpdate();
//                        }catch(SQLException e){
//                            JOptionPane.showMessageDialog(null, "Erro no itens do pedido");
//                        }
//                    }
 
                //}
        }
    }
    
    //Aba: lista produtos
    public ArrayList<String> listarProdutos(){
        produtos.clear();
        String produto = "";
        try{
            sql = "SELECT tb_produtos.pro_nome FROM tb_produtos\n" +
"                   INNER JOIN\n" +
"                   tb_estoques\n" +
"                   on\n" +
"                   tb_produtos.pro_cod = tb_estoques.est_pro_cod\n" +
"                   WHERE\n" +
"                   tb_estoques.est_arm = 'Geral'";
            st = connection.prepareStatement(sql);
            result = st.executeQuery();
            while(result.next()){
                produto = result.getString(1);
                produtos.add(produto);
            }
            return produtos;
        }catch(SQLException e){
            resultado = "ERRO";
            return null;
        }
    }
    
    //Aba: Buscar o produto
    public int codPro(String nome) {
        int cod = 0;
        try{
            sql = "Select pro_cod From tb_produtos where pro_nome = ?";
            st = connection.prepareStatement(sql);
            st.setString(1, nome);
            result = st.executeQuery();
            while(result.next()){
                cod = result.getInt(1);
            }
            return cod;
        }catch(SQLException e){
            resultado ="ERRO";
            return 0;
        }
    }
    
    //Aba: Movimentar o estoque
    private void moviEsto(int codEst, int resultado1) {
        try{
            sql = "UPDATE tb_estoque SET est_qtd = ? WHERE tb_estoques.est_cod = ?;";
            st = connection.prepareStatement(sql);
            st.setInt(1, resultado1);
            st.setInt(2, codEst);
            st.executeUpdate();
        }catch(SQLException e){
            resultado = "ERRO";
            JOptionPane.showMessageDialog(null, "ERRO");
        }
    }
    
    //Aba: buscar Pedido
    public Pedidos buscar(String nome){
        Pedidos ped = new Pedidos();
        return ped;
    }
    
    public void excluir(String nome){}
    
    //Aba: buscar codigo do pedido
    private int codPed() {
        int cod = 0;
        try{
            sql = "select ped_cod from tb_pedidos order by ped_cod desc LIMIT 1";
            st = connection.prepareStatement(sql);
            result = st.executeQuery();
            while(result.next()){
                cod = result.getInt(1);
            }
            return cod;
        }catch(SQLException e){
            resultado = "ERRO";
            return 0;
        }
    }
    
    public ArrayList<String> codigosPedidos(){
        ArrayList<String> pedidos = new ArrayList<String>();
        String cod = "";
        
        try{
            sql = "SELECT tb_pedidos.ped_cod FROM tb_pedidos where tb_pedidos.ped_atendido = 1";
            st = connection.prepareStatement(sql);
            result = st.executeQuery();
            while(result.next()){
                cod = result.getString(1);
                pedidos.add(cod);
            }
            return pedidos;
        }catch(SQLException e){
            return null;
        }
    }
    
     public ArrayList<Pedidos> buscarProdutosCod(int cod){
            
            ArrayList<Pedidos> pedidos = new ArrayList<Pedidos>();

            try{
                sql = "SELECT tb_produtos.pro_nome as 'Produto',\n" +
                    "tb_it_ped.it_ped_qtd as 'Quantidade' \n" +
                    "from tb_produtos\n" +
                    "inner join tb_it_ped\n" +
                    "on tb_it_ped.it_ped_pro_cod= tb_produtos.pro_cod\n" +
                    "inner join tb_pedidos\n" +
                    "on tb_pedidos.ped_cod = tb_it_ped.it_ped_ped_cod\n" +
                    "where tb_pedidos.ped_cod = ?";
                st = connection.prepareStatement(sql);
                st.setInt(1, cod);
                result = st.executeQuery();
               
                while(result.next()){
                    Pedidos pedido = new Pedidos();
                    pedido.setNomePro(result.getString(1));
                    pedido.setQuantidade(result.getInt(2));
                    pedidos.add(pedido);
                }
                return pedidos;
            }catch(SQLException se){
                resultado = "Erro!";
                return null;
        }
    }
}
