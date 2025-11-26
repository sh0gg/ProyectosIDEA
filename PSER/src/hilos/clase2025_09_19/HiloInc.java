package hilos.clase2025_09_19;

//Realice una aplicación que cree NUM_HILOS hilos que incrementen NUM_INCREMENTOS un dato común.
//
// Al final se visualizará el valor del dato verificando si su valor es NUM_HILOS*NUM_INCREMENTOS.
//
// Problemas que se pueden dar:
//
// se crea un dato para cada hilo, no uno común
// se visualiza el valor en el hilo principal antes de que acaben todos los incrementos
// sin código sincronizado no se puede asegurar que se incremente de manera correcta.

public class HiloInc extends Thread {

    final static int NUM_HILOS = 10;
    final static int NUM_INCREMENTOS = 2;
    final static int VALOR_FINAL = NUM_HILOS * NUM_INCREMENTOS;

    public static class Contador {
        int valor;

        public Contador(int valor) {
            this.valor = valor;
        }

        public synchronized void incrementar() {
            valor += 1;
        }

        public synchronized int getValor() {
            return valor;
        }
    }

    private final Contador contador;

    public HiloInc(Contador contador) {
        this.contador = contador;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_INCREMENTOS; i++) {
            contador.incrementar();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Contador contador = new Contador(0);

        HiloInc[] h = new HiloInc[NUM_HILOS];

        for (int i = 0; i < NUM_HILOS; i++) {
            h[i] = new HiloInc(contador);
            h[i].start();
        }

        for (int i = 0; i < NUM_HILOS; i++) {
            h[i].join();
        }

        if (contador.getValor() == VALOR_FINAL) {
            System.out.println("Todo ha salido bien! (" + contador.getValor() + " y " + VALOR_FINAL + ")");
        } else {
            System.out.println("Todo ha salido mal... (" + contador.getValor() + " y " + VALOR_FINAL + ")");
        }
    }
}
