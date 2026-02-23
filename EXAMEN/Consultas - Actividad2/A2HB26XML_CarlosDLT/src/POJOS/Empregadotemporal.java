package POJOS;

import java.time.LocalDate;

public class Empregadotemporal extends Empregado implements java.io.Serializable {

    private LocalDate dataInicio;
    private LocalDate dataFin;
    private Double costeHora;
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
