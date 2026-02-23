package POJOS;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "EMPREGADOTEMPORAL", schema = "dbo", catalog = "EMPRESAHB26")
@PrimaryKeyJoinColumn(name = "NSS") // Se enlaza con la clave primaria de EMPREGADO
public class Empregadotemporal extends Empregado implements java.io.Serializable {

    @Column(name = "DataInicio")
    private LocalDate dataInicio;

    @Column(name = "DataFin")
    private LocalDate dataFin;

    @Column(name = "CosteHora")
    private Double costeHora;

    @Column(name = "NumHoras")
    private Double numHoras;

    public Empregadotemporal() {
    }

    public Empregadotemporal(LocalDate dataInicio, LocalDate dataFin, Double costeHora, Double numHoras, String nss, String nome, String apelido1) {
        super(nss, nome, apelido1);
        this.dataInicio = dataInicio;
        this.dataFin = dataFin;
        this.costeHora = costeHora;
        this.numHoras = numHoras;
    }
    public Empregadotemporal(String nss, String nome, String apelido1, String apelido2, LocalDate dataNacemento,
            Character sexo, Enderezo enderezo, LocalDate dataInicio, LocalDate dataFin, Double costeHora, Double numHoras) {

        super(nss, nome, apelido1, apelido2, dataNacemento, sexo, enderezo);
        this.dataInicio = dataInicio;
        this.dataFin = dataFin;
        this.costeHora = costeHora;
        this.numHoras = numHoras;
    }


    public LocalDate getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFin() {
        return this.dataFin;
    }

    public void setDataFin(LocalDate dataFin) {
        this.dataFin = dataFin;
    }

    public Double getCosteHora() {
        return this.costeHora;
    }

    public void setCosteHora(Double costeHora) {
        this.costeHora = costeHora;
    }

    public Double getNumHoras() {
        return this.numHoras;
    }

    public void setNumHoras(Double numHoras) {
        this.numHoras = numHoras;
    }

}
