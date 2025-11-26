package hilos.clase2025_10_01.sol;

public class Principal {
    static final int NUM_COCHES = 50;

    public static void main(String[] args) {
        Aparcamiento aparcamiento = new Aparcamiento();
        Coche[] coches = new Coche[NUM_COCHES];
        for (int i = 0; i < NUM_COCHES; i++) {
            coches[i] = new Coche(""+(i+1),aparcamiento);
            coches[i].start;
        }
        for (Coche coche : coches) {
            try ()
        }
    }
}
