package hilos.clase2025_10_01;

public class AparcamientoPublico {

    public static final int NUM_PLAZAS = 10;
    public static final int NUM_CONDUCTORES = 50;

    public static void main(String[] args) throws InterruptedException {
        Aparcamiento aparcamiento = new Aparcamiento(NUM_PLAZAS);
        Conductor[] conductores = new Conductor[NUM_CONDUCTORES];

        for (int i = 0; i < NUM_CONDUCTORES; i++) {
            conductores[i] = new Conductor(i, aparcamiento);
            conductores[i].start();
        }
        for (Conductor c : conductores) {
            c.join();
        }
        System.out.println("\n--- Simulación finalizada ---");
    }
}