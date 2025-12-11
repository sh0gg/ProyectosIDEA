package clases;

import java.time.LocalDate;

public class VehiculoRenting extends Vehiculo {

    private LocalDate dataInicio;
    private double prezoMensual;
    private int mesesContratados;

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public double getPrezoMensual() {
        return prezoMensual;
    }

    public void setPrezoMensual(double prezoMensual) {
        this.prezoMensual = prezoMensual;
    }

    public int getMesesContratados() {
        return mesesContratados;
    }

    public void setMesesContratados(int mesesContratados) {
        this.mesesContratados = mesesContratados;
    }
}
