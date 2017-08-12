package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Produtos {
    private int codigo;
    private String nome , descricao;
    private Categorias categoria;
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

    

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Produtos{" + "nome=" + nome + ", descricao=" + descricao + ", categoria=" + categoria + '}';
    }

    
}
