//create table tb_notas_fiscais(
//    nf_cod int not null auto_increment primary key,
//    nf_num varchar(100),
//    nf_forn varchar(150),
//    nf_dt date,
//    nf_ent_cod int REFERENCES tb_entradas(ent_cod)
//);

package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NotasFiscais {
    private int codigo;
    private String numero, fornecedor;
    private Date dtNotaFiscal;
    private ArrayList<String> entradas;
     private SimpleDateFormat sdf;
     
    public NotasFiscais(){
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Date getDtNotaFiscal() {
        return dtNotaFiscal;
    }

    public void setDtNotaFiscal(Date dtNotaFiscal) {
        this.dtNotaFiscal = dtNotaFiscal;
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
        return "NotaFiscal{" + "codigo=" + codigo + ", numero=" + numero + ", fornecedor=" + fornecedor + ", dtNotaFiscal=" + dtNotaFiscal + ", entradas=" + entradas + ", sdf=" + sdf + '}';
    }

    
}
