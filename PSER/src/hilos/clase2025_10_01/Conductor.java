package hilos.clase2025_10_01;

import java.util.Random;

class Conductor extends Thread {
    private final int idConductor;
    private final Aparcamiento aparcamiento;
    private final Random aleatorio = new Random();

    public Conductor(int idConductor, Aparcamiento aparcamiento) {
        super("Conductor-" + idConductor);
        this.idConductor = idConductor;
        this.aparcamiento = aparcamiento;
    }

    @Override
    public void run() {
        try {
            int plaza = aparcamiento.entrar(idConductor);
            int segundos = aleatorio.nextInt(5) + 1; // 1 a 5
            Thread.sleep(segundos * 1000L);
            aparcamiento.salir(plaza, idConductor);
        } catch (InterruptedException e) {
            interrupt();
        }
    }
}