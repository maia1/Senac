//create table tb_entradas(
//	ent_cod int not null auto_increment primary key,
//	ent_dt date
//);

package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Entradas extends NotasFiscais {
    private int codigo;
    private Date dataEntrada;
    private SimpleDateFormat sdf;

    public Entradas() {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    @Override
    public String toString() {
        return "Entradas{" + "codigo=" + codigo + ", dataEntrada=" + sdf.format(dataEntrada) +'}';
    }
    
    
    
    
    
    
}
