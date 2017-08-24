package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Estoques;
import model.Pedidos;

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

                    //Aba: buscar
                    int codProd = codPro(pedido.getNomePro());
                    int pedQTD = pedido.getQuantidade();
                    int codPedido = codPed();
                    
                    //Aba: Inserir o item pedido

                    try{
                        sql2 = "INSERT INTO tb_it_ped (it_ped_qtd, it_ped_pro_cod, it_ped_ped_cod) VALUES (?,?,?)";
                        st = connection.prepareStatement(sql2);
                        st.setInt(1, pedQTD);
                        st.setInt(2, codProd);
                        st.setInt(3, codPedido);
                        st.executeUpdate();
                    }catch(SQLException e){
                        JOptionPane.showMessageDialog(null, "Erro nos itens do pedido");
                    }  
        }
    }
    
    //Aba: lista produtos
    public ArrayList<String> listarProdutos(){
        produtos.clear();
        String produto = "";
        try{
            sql = "SELECT " +
                  "tb_produtos.pro_nome FROM tb_produtos " +
                  "INNER JOIN " +
                  "tb_estoques " +
                  "on " +
                  "tb_produtos.pro_cod = tb_estoques.est_pro_cod " +
                  "WHERE " +
                  "tb_estoques.est_arm = 'Geral'";
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
            sql = "UPDATE tb_estoques SET est_qtd = ? WHERE tb_estoques.est_cod = ?;";
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
    
     public ArrayList<Pedidos> buscar(Date dataPedidoSQL){
        ArrayList<Pedidos> ped = new ArrayList<Pedidos>();
       
        try{
            sql = "select tb_produtos.pro_nome,\n" +
                    "       tb_it_ped.it_ped_qtd,\n" +
                    "       tb_pedidos.ped_dt_pedido,\n" +
                    "       tb_pedidos.ped_atendido\n" +
                    "from   tb_pedidos\n" +
                    "inner join tb_it_ped\n" +
                    "on tb_pedidos.ped_cod = tb_it_ped.it_ped_ped_cod\n" +
                    "inner join tb_produtos\n" +
                    "on tb_it_ped.it_ped_pro_cod = tb_produtos.pro_cod\n" +
                    "where tb_pedidos.ped_dt_pedido = ?\n" +
                    "and tb_pedidos.ped_atendido = 2";
            st = connection.prepareStatement(sql);
            st.setDate(1, dataPedidoSQL);
            result = st.executeQuery();
            
            while(result.next()){
                Pedidos pedido = new Pedidos();
                pedido.setNomePro(result.getString(1));
                pedido.setQuantidade(result.getInt(2));
                ped.add(pedido);
            }
            
            return ped;
        }catch(SQLException e){
            resultado = "Erro na Busca do pedido";
            return null;
        }
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

    public void darBaixa(int codPedido) {
        ArrayList<Estoques> itens = pegarItens(codPedido);
        for(Estoques est: itens){
            alterarEstoqueGeral(est);
            addEstoqueNutricao(est);
            MovPed(codPedido);
            JOptionPane.showMessageDialog(null, "Pedido baixado com sucesso");
        }
    }
    
    private void MovPed(int codPedido){
        try{
            sql = "UPDATE tb_pedidos SET ped_atendido = 2 WHERE tb_pedidos.ped_cod = ?";
            st = connection.prepareStatement(sql);
            st.setInt(1, codPedido);
            st.executeUpdate();
        }catch(SQLException e){
            resultado = "ERRO";
        }
    }

    private void alterarEstoqueGeral(Estoques est) {
        int novaQtd = est.getQuantidadeAtual()-est.getQuantidade();
        try{
            sql = "UPDATE tb_estoques SET tb_estoques.est_qtd = ? WHERE tb_estoques.est_pro_cod = ?;";
            st = connection.prepareStatement(sql);
            st.setInt(1, novaQtd);
            st.setInt(2, est.getCodigo());
            st.executeUpdate();
        }catch(SQLException e){
            resultado = "ERRO";
        }
    }
    
    
    private void addEstoqueNutricao(Estoques est) {
        try{
            sql2 = "INSERT INTO tb_estoques (est_qtd, est_dt_val, est_arm, est_pro_cod) VALUES (?,?,?,?);";
            st = connection.prepareStatement(sql2);
            st.setInt(1, est.getQuantidade());
            java.sql.Date data = (java.sql.Date) est.getDataValidade();
            st.setDate(2, data);
            st.setString(3, est.getArmazem());
            st.setInt(4, est.getCodigo());
            st.executeUpdate();
        }catch(SQLException e){
            resultado = "ERRO";
        }
    }

    private ArrayList<Estoques> pegarItens(int codPedido) {
        ArrayList<Estoques> estoques = new ArrayList<Estoques>();
            try{
                sql = "select " +
                      "	tb_it_ped.it_ped_qtd as qtd, " +
                      "	tb_estoques.est_dt_val as dtValidade, " +
                      "	tb_produtos.pro_cod as codProduto, " +
                      "	max(tb_estoques.est_cod), " +  
                      "     tb_estoques.est_qtd as qtd " +
                      "From " +
                      "	tb_pedidos " +
                      "inner join " +
                      "	tb_it_ped " +
                      "on " +
                      "	tb_it_ped.it_ped_ped_cod = tb_pedidos.ped_cod " +
                      "inner join " +
                      "	tb_produtos " +
                      "on " +
                      "	tb_produtos.pro_cod = tb_it_ped.it_ped_pro_cod " +
                      "inner join " +
                      "	tb_estoques " +
                      "on " +
                      "	tb_produtos.pro_cod = tb_estoques.est_pro_cod " +
                     "where " +
                     "	tb_pedidos.ped_cod = ?;";
                st = connection.prepareStatement(sql);
                st.setInt(1, codPedido);
                result = st.executeQuery();
               
                while(result.next()){
                    Estoques est = new Estoques();
                    est.setQuantidade(result.getInt(1));
                    est.setDataValidade(result.getDate(2));
                    est.setCodigo(result.getInt(3));
                    est.setQuantidadeAtual(result.getInt(4));
                    est.setArmazem("Nutrição");
                    estoques.add(est);
                }
                return estoques;
            }catch(SQLException se){
                resultado = "Erro!";
                return null;
        }
    }
}
