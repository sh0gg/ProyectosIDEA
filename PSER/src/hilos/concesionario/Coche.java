package hilos.concesionario;

public class Coche {
    private int id;
    private String modelo;
    private int numVisitas;
    private boolean isVendido;
    private boolean enPrueba;

    public Coche(int id, String modelo) {
        this.id = id;
        this.modelo = modelo;
        this.numVisitas = 0;
        this.isVendido = false;
        this.enPrueba = false;
    }

    synchronized void esVisitado() {
        numVisitas++;
    }

    synchronized boolean vender() {
        if (!isVendido) {
            isVendido = true;
            return true;
        } else {
            return false;
        }
    }

    int getId() {
        return id;
    }

    String getModelo() {
        return modelo;
    }

    // Getters (sincronizados!!!)

    synchronized int getVisitas() {
        return numVisitas;
    }

    synchronized void setEnPrueba(boolean enPrueba) {
        this.enPrueba = enPrueba;
    }

    synchronized boolean isEnPrueba() {
        return enPrueba;
    }

    synchronized boolean isVendido() {
        return isVendido;
    }

}
