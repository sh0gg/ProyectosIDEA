package clases;

import java.time.LocalDate;

public class VehiculoPropio extends Vehiculo {

    private LocalDate dataCompra;
    private double prezoCompra;

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public double getPrezoCompra() {
        return prezoCompra;
    }

    public void setPrezoCompra(double prezoCompra) {
        this.prezoCompra = prezoCompra;
    }
}
