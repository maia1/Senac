package contoller;

import excecoes.ProdutoNaoEncontrado;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import jdbc.CRUDSaida;
import model.Saidas;


public class ControleSaida {
    private CRUDSaida crudSaida;

    public ControleSaida() {
        crudSaida =  new CRUDSaida();
 
    }
    
      public void cadastrar(ArrayList<Saidas> saidas) throws SQLException, ProdutoNaoEncontrado{
        crudSaida.cadastrar(saidas);
    }
    
    public Saidas buscar(String nome ){
        Saidas sai = new Saidas();
        
        
        return sai;
    }
   
    public void excluir(String nome){
        crudSaida.excluir(nome);
    }
    
    public String[] listarProdutos(){

        ArrayList<String> produtos = crudSaida.listarProdutos();
 
        String[] prod = new String[produtos.size()+1];
        int i=1;
        prod[0] = "";
        for(String prods: produtos){
            prod[i++] = prods;
        }
        return prod;
    }
    
    public String[] buscaProdEstoque(){

        ArrayList<String> prodEstoques = crudSaida.buscaProdEstoques();
 
        String[] prodEst = new String[prodEstoques.size()+1];
        int i=1;
        prodEst[0] = "";
        for(String prodEstq: prodEstoques){
            prodEst[i++] = prodEstq;
        }
        return prodEst;
    }
    
    public ArrayList<Saidas> buscarSaidasProdutos(Date data){

        ArrayList<Saidas> saidaEstoques = crudSaida.buscarSaidasProdutos(data);
        return saidaEstoques;
    }
    


}
