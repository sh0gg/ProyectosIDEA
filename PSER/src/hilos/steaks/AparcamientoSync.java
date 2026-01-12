package hilos.steaks;
// AparcamientoSync.java
public class AparcamientoSync {

    static class Aparcamiento {
        private int libres;

        Aparcamiento(int plazas) { this.libres = plazas; }

        public synchronized void entrar(String coche) throws InterruptedException {
            while (libres == 0) wait(); // si no hay plazas, espero
            libres--;
            System.out.println(coche + " entra. Libres=" + libres);
        }

        public synchronized void salir(String coche) {
            libres++;
            System.out.println(coche + " sale. Libres=" + libres);
            notifyAll(); // aviso a los que esperaban plaza
        }
    }

    static class Coche extends Thread {
        private final Aparcamiento ap;
        Coche(String name, Aparcamiento ap) { super(name); this.ap = ap; }

        public void run() {
            try {
                ap.entrar(getName());
                Thread.sleep(200); // está aparcado un rato
                ap.salir(getName());
            } catch (InterruptedException ignored) {}
        }
    }

    public static void main(String[] args) {
        Aparcamiento ap = new Aparcamiento(2);
        for (int i = 1; i <= 6; i++) new Coche("Coche-" + i, ap).start();
    }
}
