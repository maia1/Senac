package contoller;

import excecoes.ProdutoNaoEncontrado;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import jdbc.CRUDPedido;
import model.Pedidos;

public class ControlePedido {
    private CRUDPedido crudPedido;
    
    public ControlePedido(){
        crudPedido = new CRUDPedido();
    }
    
    public void cadastrar(ArrayList<Pedidos> pedido){
        crudPedido.cadastrar(pedido);
    }
    
    public Pedidos buscar(String nome ){
        Pedidos ped = new Pedidos();
        
        ped = crudPedido.buscar(nome);
        return ped;
    }
    
    public void excluir(String nome){
        crudPedido.excluir(nome);
    }

    public String[] listarProdutos() {
        ArrayList<String> produtos = crudPedido.listarProdutos();
        
        String[] prod = new String[produtos.size()+1];
        int i = 0;
        prod[0] = "";
        for(String prods: produtos){
            prod[++i] = prods;
        }
        return prod;
    }
    
    public int buscarCod(String nome){
        int cod = crudPedido.codPro(nome);
        return cod;
    }
    
    public String[] listarPedidos() {
        ArrayList<String> pedidos = crudPedido.codigosPedidos();
        
        String[] ped = new String[pedidos.size()+1];
        int i = 0;
        ped[0] = "";
        for(String pedido: pedidos){
            ped[++i] = pedido;
        }
        return ped;
    }
    
    public ArrayList<Pedidos> buscarPedidos(int cod){
        ArrayList<Pedidos> pedidos = new ArrayList<Pedidos>();
        pedidos = crudPedido.buscarProdutosCod(cod);
        return pedidos;
    }
}
