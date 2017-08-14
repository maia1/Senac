package contoller;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import jdbc.CRUDEntrada;
import model.Entradas;

public class ControleEntrada {
    private CRUDEntrada crudEntrada;
    
    public ControleEntrada() {
        crudEntrada = new CRUDEntrada();
    }
    
    
    
    public void cadastrar(ArrayList<Entradas> entradas, Date data){   
        crudEntrada.cadastrar(entradas, data);
    }
    
    public String[] listarCategorias(){
        ArrayList<String> categorias = crudEntrada.categorias();
        String[] cat = new String[categorias.size()+1];
        int i=1;
        cat[0] = "";
        for(String cate : categorias){
            cat[i++]= cate;
        }
        return cat;
    }
}
