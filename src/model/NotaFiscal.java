package model;

import java.util.Date;

public class NotaFiscal {
    private String numero, fornecedor;
    private int quantidade;
    private Date dtNotaFiscal;

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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDtNotaFiscal() {
        return dtNotaFiscal;
    }

    public void setDtNotaFiscal(Date dtNotaFiscal) {
        this.dtNotaFiscal = dtNotaFiscal;
    }

    @Override
    public String toString() {
        return "NotaFiscal{" + "numero=" + numero + ", fornecedor=" + fornecedor + ", quantidade=" + quantidade + ", dtNotaFiscal=" + dtNotaFiscal + '}';
    }
    
    
}
