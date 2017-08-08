//CREATE TABLE tb_it_ped(
//	it_ped_qtd int,
//    	it_ped_pro_cod int references tb_produtos(pro_cod),
//    	it_ped_ped_cod int references tb_pedidos(ped_cod)
//);
package model;

import java.util.ArrayList;


public class ItensPedidos {
    private int quantidadePed;
    private ArrayList<String> produtos;
    private ArrayList<String> pedidos;

    public int getQuantidadePed() {
        return quantidadePed;
    }

    public void setQuantidadePed(int quantidadePed) {
        this.quantidadePed = quantidadePed;
    }

    public ArrayList<String> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<String> produtos) {
        this.produtos = produtos;
    }

    public ArrayList<String> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<String> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "ItensPedidos{" + "quantidadePed=" + quantidadePed + ", produtos=" + produtos + ", pedidos=" + pedidos + '}';
    }

    
    
    
    
}
