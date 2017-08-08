//CREATE TABLE tb_produtos(
//	pro_cod int not null auto_increment primary key,
//	pro_nome varchar(100),
//      pro_desc varchar(150),
//	pro_cat_cod int references tb_categoria(cat_cod),
//	pro_est_cod int references tb_armazem(est_cod)
//);
package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Produtos {
    private int codigo;
    private String nome, descricao;
    private Categorias categoria;
    private ArrayList<String> armazem;
    
    private SimpleDateFormat sdf;

    public Produtos() {
       sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public ArrayList<String> getArmazem() {
        return armazem;
    }

    public void setArmazem(ArrayList<String> armazem) {
        this.armazem = armazem;
    }

    @Override
    public String toString() {
        return "Produtos{" + "codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao + ", sdf=" + sdf + '}';
    }

    
    
    
}
