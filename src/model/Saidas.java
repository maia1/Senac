//create table tb_saidas(
//	sai_cod int not null auto_increment primary key,
//	sai_dt date
//);

package model;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Saidas extends ItensSaidas{
    private Date dataSaida;
    private int codigo;
    private SimpleDateFormat sdf;

    public Saidas() {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    @Override
    public String toString() {
        
        return super.toString()+"Saidas{" + "dataSaida=" + dataSaida + ", codigo=" + codigo + ", sdf=" + sdf + '}';
    }

    

        
    
    

    
    
    
}
