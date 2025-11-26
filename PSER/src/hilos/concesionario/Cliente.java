package hilos.concesionario;

import java.util.Random;

public class Cliente extends Thread {

    private final String[] LISTA_NOMBRES = {"Jaime", "Lola", "Robe", "Juan", "Maria", "Fulano", "Mengano", "David", "Jorge", "Carmen"};

    private String nombre;
    private boolean haCompradoCoche;
    private Vendedor vendedor;
    private boolean sinStock;
    private Coche cocheComprado;
    static int id = 0;

    private final Random r = new Random();

    public Cliente(Vendedor vendedor) {
        this.vendedor = vendedor;
        this.nombre = LISTA_NOMBRES[r.nextInt(LISTA_NOMBRES.length)] + "(" + ++id + ")";
        this.haCompradoCoche = false;
        this.sinStock = false;
    }

    @Override
    public void run() {
        try {
            while (!haCompradoCoche && !sinStock) {
                decideVisitar();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (haCompradoCoche) {
            System.out.println("[" + nombre + "] - He comprado un " + cocheComprado.getModelo() + " " + cocheComprado.getId() + ".");
        } else {
            System.out.println("[" + nombre + "] - Jo, no compré coche.");
        }
    }

    void decideVisitar() throws InterruptedException {

        Coche coche = vendedor.asignarCocheLibre();

        if (coche == null) {
            sinStock = true;
            return;
        }


        coche.esVisitado();
        System.out.println("[" + nombre + "] - He visto el " + coche.getModelo() + " " + coche.getId() + ".");

        sleep(20);

        int visitas = coche.getVisitas();

        double probabilidad = Math.min(visitas, 100) / 100.0;
        if (probabilidad > Math.random()) {
            boolean comprado = vendedor.venderCoche(coche, this);
            if (comprado) {
                haCompradoCoche = true;
                cocheComprado = coche;
                return;
            }
        }

        vendedor.devolverCoche(coche);

    }

    // Getters


    public Coche getCocheComprado() {
        return cocheComprado;
    }

    public boolean isHaCompradoCoche() {
        return haCompradoCoche;
    }

    public String getNombre() {
        return nombre;
    }
}
