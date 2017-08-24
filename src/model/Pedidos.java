//create table tb_pedidos(
//	ped_cod int not null auto_increment primary key,
//	ped_dt_pedido date
//);

package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pedidos extends ItensPedidos{
    private int codigo, quantidade;
    private Date dtPedido;
    private String nomePro;
    private SimpleDateFormat sdf;
    
    public Pedidos(){
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    public String getNomePro() {
        return nomePro;
    }

    public void setNomePro(String nomePro) {
        this.nomePro = nomePro;
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

    public Date getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(Date dtPedido) {
        this.dtPedido = dtPedido;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    @Override
    public String toString() {
        return "Pedidos{" + "codigo=" + codigo + ", quantidade=" + quantidade + ", dtPedido=";
    }

    
    
    
    
}
