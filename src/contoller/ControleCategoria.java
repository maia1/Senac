
package contoller;

import java.util.ArrayList;
import jdbc.CRUDCategria;


public class ControleCategoria {
    private CRUDCategria crudCategoria;

    public ControleCategoria() {
        crudCategoria = new CRUDCategria();
    }
    
    
    public void cadastrar(String nome){
        crudCategoria.cadastrar(nome);
    }
    
    public void excluir (String nome){
        crudCategoria.excluir(nome);
    }
    
    public void editar(String nome, String novoNome){
        crudCategoria.editar(nome, novoNome);
    }
   
    
    
    public String[] listarCategorias(){
        ArrayList<String> categorias = crudCategoria.categorias();
        String[] cat = new String[categorias.size()+1];
        int i=1;
        cat[0] = "";
        for(String cate : categorias){
            cat[i++]= cate;
        }
        return cat;
    }

}
