package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Entradas extends NotasFiscais {
    private Date dataVal;
    private int quantidade;
    private Produtos produto;
    private SimpleDateFormat sdf;

    public Entradas() {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    
    
    public Date getDataVal() {
        return dataVal;
    }

    public void setDataVal(Date dataEntrada) {
        this.dataVal = dataEntrada;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }
    
    @Override
    public String toString() {
        return "Entradas{" + "dataEntrada=" +sdf.format(dataVal)+ ", quantidade=" + quantidade + ", produto=" + produto;
    }
    
    
    
    
    
    
}
