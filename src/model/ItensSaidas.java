//CREATE TABLE tb_it_sai(
//	it_sai_qtd int,
//    	it_sai_pro_cod int references tb_produtos(pro_cod),
//    	it_sai_sai_cod int references tb_saidas(sai_cod)
//);
package model;

import java.util.ArrayList;

public class ItensSaidas {
    private int quantidadeSai;
    private ArrayList<String> produtos;
    private ArrayList<String> saidas;

    public int getQuantidadeSai() {
        return quantidadeSai;
    }

    public void setQuantidadeSai(int quantidadeSai) {
        this.quantidadeSai = quantidadeSai;
    }

    public ArrayList<String> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<String> produtos) {
        this.produtos = produtos;
    }

    public ArrayList<String> getSaidas() {
        return saidas;
    }

    public void setSaidas(ArrayList<String> saidas) {
        this.saidas = saidas;
    }

    @Override
    public String toString() {
        return "ItensSaidas{" + "quantidadeSai=" + quantidadeSai + ", produtos=" + produtos + ", saidas=" + saidas + '}';
    }


}
