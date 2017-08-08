package contoller;

import excecoes.ProdutoNaoEncontrado;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import jdbc.CRUDProduto;
import model.Categorias;
import model.Produtos;

public class ControleProduto {
    private CRUDProduto crudProduto;
  

    public ControleProduto() {
        crudProduto =  new CRUDProduto();
    }
    
    
    
    public void cadastrar(String nome, Date dataFabricacao, Date dataValidade, String categoria){
        Produtos prod = new Produtos();
        prod.setNome(nome);
        prod.setDataFabricacao(dataFabricacao);
        prod.setDataValidade(dataValidade);
        crudProduto.cadastrar(prod, categoria);
    }
    
    public Produtos buscar(String nome )throws ProdutoNaoEncontrado{
        Produtos pro = crudProduto.buscar(nome);
        if(pro != null){
            return pro;
        }
        throw new ProdutoNaoEncontrado();
    }
    
    public void editar(String nome, Date dataFabricacao, Date dataValidade, int codigo, String categoria){
        Produtos prod = new Produtos();
        prod.setCodigo(codigo);
        prod.setNome(nome);
        prod.setDataFabricacao(dataFabricacao);
        prod.setDataValidade(dataValidade);
        crudProduto.editar(prod, categoria);
    }
    public void excluir(int cod){
        crudProduto.excluir(cod);
    }
    
     public String[] listarCategorias(){
        ArrayList<String> categorias = crudProduto.categorias();
        String[] cat = new String[categorias.size()+1];
        int i=1;
        cat[0] = "";
        for(String cate : categorias){
            cat[i++]= cate;
        }
        return cat;
    }

}
