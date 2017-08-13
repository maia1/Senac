package contoller;

import excecoes.ProdutoNaoEncontrado;
import java.sql.SQLException;
import java.util.ArrayList;
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
        
        sai = crudSaida.buscar(nome);
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
    


}
