//create table tb_estoques(
//    est_cod int not null auto_increment primary key,
//    est_qtd int,
//    est_dt_val date,
//    est_arm varchar(100)
//);
package model;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Estoques {
    private SimpleDateFormat sdf;
    private int codigo, quantidade, QuantidadeAtual;
    private Date dataValidade;
    private String armazem;
    
    
public Estoques(){
    sdf = new SimpleDateFormat("dd/MM/yyyy");
}

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getArmazem() {
        return armazem;
    }

    public void setArmazem(String armazem) {
        this.armazem = armazem;
    }

    public int getQuantidadeAtual() {
        return QuantidadeAtual;
    }

    public void setQuantidadeAtual(int QuantidadeAtual) {
        this.QuantidadeAtual = QuantidadeAtual;
    }

    @Override
    public String toString() {
        return "Estoques{" + "sdf=" + sdf + ", codigo=" + codigo + ", quantidade=" + quantidade + ", dataValidade=" + dataValidade + ", armazem=" + armazem + '}';
    }
    
    
}
