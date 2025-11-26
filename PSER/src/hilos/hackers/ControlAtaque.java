package hilos.hackers;

public class ControlAtaque {

    private final RainbowTable rainbowTable;
    private int hashObjetivo;

    private boolean encontrada = false;
    private String claveGanadora = null;
    private String hackerGanador = null;

    public ControlAtaque(RainbowTable rainbowTable) {
        this.rainbowTable = rainbowTable;
    }

    public synchronized void nuevaVictima(String passwordEnClaro) {
        this.hashObjetivo = passwordEnClaro.hashCode();
        this.encontrada = false;
        this.claveGanadora = null;
        this.hackerGanador = null;

        System.out.println("\n========================================");
        System.out.println("NUEVO ATAQUE >>> Hash objetivo = " + hashObjetivo);
        System.out.println("========================================");
    }

    public synchronized boolean isEncontrada() {
        return encontrada;
    }

    public synchronized String getClaveGanadora() {
        return claveGanadora;
    }

    public synchronized String getHackerGanador() {
        return hackerGanador;
    }

    // Un hacker prueba una clave
    public boolean probarClave(String nombreHacker, String clave) {

        // 1. Consultar / crear entrada en la tabla rainbow
        RainbowEntry entry = rainbowTable.obtenerEntrada(clave);

        // 2. Comparar con el hash objetivo
        synchronized (this) {
            if (encontrada) {
                return true; // otro hacker ya ganó
            }

            if (entry.getHash() == hashObjetivo) {
                encontrada = true;
                claveGanadora = clave;
                hackerGanador = nombreHacker;

                System.out.println("\n>>> HACKER " + nombreHacker + " encontró una CLAVE CANDIDATA: " + clave);
                System.out.println(">>> ¡Medalla TIC para " + nombreHacker + "!");

                // Mostrar la entrada más usada
                RainbowEntry masUsada = rainbowTable.getMasUsada();
                if (masUsada != null) {
                    System.out.println("\n>>> Entrada más usada de la tabla rainbow:");
                    System.out.println("    " + masUsada);
                }

                return true;
            }
        }

        return false;
    }
}
