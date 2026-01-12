package hilos.steaks;

// ContadorRaceAtomic.java
import java.util.concurrent.atomic.AtomicInteger;

public class ContadorRaceAtomic {

    // MAL: int compartido sin protección
    static class ContadorMalo {
        int x = 0;
        void inc() { x++; } // no es atómico
    }

    // BIEN: AtomicInteger
    static class ContadorBueno {
        AtomicInteger x = new AtomicInteger(0);
        void inc() { x.incrementAndGet(); }
    }

    static class HiloInc extends Thread {
        private final int rep;
        private final Runnable inc;
        HiloInc(String name, int rep, Runnable inc) { super(name); this.rep = rep; this.inc = inc; }
        public void run() { for (int i = 0; i < rep; i++) inc.run(); }
    }

    public static void main(String[] args) throws InterruptedException {
        int rep = 200_000;

        ContadorMalo cm = new ContadorMalo();
        Thread a1 = new HiloInc("A1", rep, cm::inc);
        Thread a2 = new HiloInc("A2", rep, cm::inc);
        a1.start(); a2.start(); a1.join(); a2.join();
        System.out.println("MALO esperado=" + (2 * rep) + " real=" + cm.x);

        ContadorBueno cb = new ContadorBueno();
        Thread b1 = new HiloInc("B1", rep, cb::inc);
        Thread b2 = new HiloInc("B2", rep, cb::inc);
        b1.start(); b2.start(); b1.join(); b2.join();
        System.out.println("BUENO esperado=" + (2 * rep) + " real=" + cb.x.get());
    }
}
