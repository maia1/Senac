//CREATE TABLE tb_it_ent(
//    it_ent_qtd int,
//    it_ent_dt_val date,
//    it_ent_pro_cod int references tb_produtos(pro_cod),
//    it_ent_ent_cod int references tb_entradas(ent_cod)
//);
package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ItensEntradas {
    private int quantidadeEnt;
    private Date dataValidade;
    private ArrayList<String> produtos;
    private ArrayList<String> entradas;
    private SimpleDateFormat sdf;

    public int getQuantidadeEnt() {
        return quantidadeEnt;
    }

    public void setQuantidadeEnt(int quantidadeEnt) {
        this.quantidadeEnt = quantidadeEnt;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public ArrayList<String> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<String> produtos) {
        this.produtos = produtos;
    }

    public ArrayList<String> getEntradas() {
        return entradas;
    }

    public void setEntradas(ArrayList<String> entradas) {
        this.entradas = entradas;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    @Override
    public String toString() {
        return "ItensEntradas{" + "quantidadeEnt=" + quantidadeEnt + ", dataValidade=" + dataValidade + ", produtos=" + produtos + ", entradas=" + entradas + ", sdf=" + sdf + '}';
    }
    
   
    
    
}
