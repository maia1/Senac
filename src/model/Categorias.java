//create table tb_categorias(
//	cat_cod int not null auto_increment primary key,
//	cat_nome varchar(100)
//);

package model;

public class Categorias {
    private int codigo;
    private String nomeCategoria;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    @Override
    public String toString() {
        return "Categorias{" + "codigo=" + codigo + ", nomeCategoria=" + nomeCategoria + '}';
    }

    
    
    
    
}
