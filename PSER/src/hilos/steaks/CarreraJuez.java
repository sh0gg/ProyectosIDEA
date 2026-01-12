package hilos.steaks;

// CarreraJuez.java
public class CarreraJuez {

    static class ControlSalida {
        private boolean salida = false;

        public synchronized void esperarSalida(String corredor) throws InterruptedException {
            while (!salida) wait(); // todos bloquean hasta señal
            System.out.println(corredor + " SALE!");
        }

        public synchronized void darSalida() {
            salida = true;
            System.out.println("JUEZ: PUM! salida");
            notifyAll(); // despierta a todos a la vez
        }
    }

    static class Corredor extends Thread {
        private final ControlSalida control;
        Corredor(String name, ControlSalida c) { super(name); this.control = c; }

        public void run() {
            try {
                System.out.println(getName() + " listo en la línea");
                control.esperarSalida(getName());
                Thread.sleep(150); // corre
                System.out.println(getName() + " llega a meta");
            } catch (InterruptedException ignored) {}
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ControlSalida c = new ControlSalida();
        for (int i = 1; i <= 4; i++) new Corredor("Corredor-" + i, c).start();

        Thread.sleep(500); // el juez espera un poco (simula “preparados…”)
        c.darSalida();
    }
}
