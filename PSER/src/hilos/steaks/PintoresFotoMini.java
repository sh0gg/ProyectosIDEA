package hilos.steaks;

// PintoresFotoMini.java
import java.util.Arrays;

public class PintoresFotoMini {

    static class Muro {
        private final int[] t; // 0 = sin pintar
        private boolean fotoHecha = false;

        Muro(int n) { t = new int[n]; }

        public synchronized boolean pintarUno(int color, String pintor) {
            if (fotoHecha) return false;
            int idx = -1;
            for (int i = 0; i < t.length; i++) if (t[i] == 0) { idx = i; break; }
            if (idx == -1) { notifyAll(); return false; } // ya completo
            t[idx] = color;
            System.out.println(pintor + " pinta " + idx + " -> " + Arrays.toString(t));
            notifyAll(); // cambia estado: puede interesar al fotógrafo
            return true;
        }

        public synchronized int[] esperarCompletoYFoto() throws InterruptedException {
            while (!fotoHecha && !completo()) wait();
            fotoHecha = true;
            return Arrays.copyOf(t, t.length);
        }

        private boolean completo() {
            for (int v : t) if (v == 0) return false;
            return true;
        }
    }

    static class Pintor extends Thread {
        private final Muro m; private final int color;
        Pintor(String name, Muro m, int color) { super(name); this.m = m; this.color = color; }

        public void run() {
            try {
                while (m.pintarUno(color, getName())) Thread.sleep(80);
            } catch (InterruptedException ignored) {}
        }
    }

    static class Fotografo extends Thread {
        private final Muro m;
        Fotografo(Muro m) { super("Fotografo"); this.m = m; }

        public void run() {
            try {
                int[] foto = m.esperarCompletoYFoto();
                System.out.println("FOTO -> " + Arrays.toString(foto));
            } catch (InterruptedException ignored) {}
        }
    }

    public static void main(String[] args) {
        Muro m = new Muro(8);
        new Pintor("P1", m, 1).start();
        new Pintor("P2", m, 2).start();
        new Fotografo(m).start();
    }
}
