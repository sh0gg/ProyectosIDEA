package hilos.steaks;

// ProductorConsumidor1.java
public class ProductorConsumidor1 {

    static class Buffer1 {
        private Integer dato = null; // null = vacío

        // Monitor: método synchronized + wait en while
        public synchronized void put(int x) throws InterruptedException {
            while (dato != null) wait();   // si está lleno, espero
            dato = x;                      // sección crítica
            notifyAll();                   // aviso: ya hay dato
        }

        public synchronized int get() throws InterruptedException {
            while (dato == null) wait();   // si está vacío, espero
            int x = dato;                  // sección crítica
            dato = null;                   // lo dejo vacío
            notifyAll();                   // aviso: ya hay hueco
            return x;
        }
    }

    static class Productor extends Thread {
        private final Buffer1 b;
        Productor(Buffer1 b) { this.b = b; }
        public void run() {
            try {
                for (int i = 1; i <= 10; i++) {
                    b.put(i);
                    System.out.println("P -> " + i);
                    Thread.sleep(80);
                }
            } catch (InterruptedException ignored) {}
        }
    }

    static class Consumidor extends Thread {
        private final Buffer1 b;
        Consumidor(Buffer1 b) { this.b = b; }
        public void run() {
            try {
                for (int i = 1; i <= 10; i++) {
                    int x = b.get();
                    System.out.println("C <- " + x);
                    Thread.sleep(120);
                }
            } catch (InterruptedException ignored) {}
        }
    }

    public static void main(String[] args) {
        Buffer1 b = new Buffer1();
        new Productor(b).start();
        new Consumidor(b).start();
    }
}
