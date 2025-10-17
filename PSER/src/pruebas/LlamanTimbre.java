package pruebas;

public class LlamanTimbre {

    static boolean isAnswered = false;
    static final int MAX_PACIENCIA = 10;
    static int contadorPaciencia = MAX_PACIENCIA;

    static Thread yo = new Thread(() -> {

        while (contadorPaciencia > 0) {
            System.out.println("Estoy trabajando.");

            if (Thread.currentThread().isInterrupted() && (contadorPaciencia > 0)) {
                System.out.println("Mi paciencia está al: " + contadorPaciencia);
                contadorPaciencia--;
                break;
            }

            if (Thread.currentThread().isInterrupted() && (contadorPaciencia <= 0)) {
                isAnswered = true;
                System.out.println("He atendido la puerta.");
            }
        }
    });

     static Thread timbre = new Thread(() -> {

        while (!isAnswered) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("¡Han llamado a la puerta!");
            yo.interrupt();
        }

    });

    public static void main(String[] args) {

        yo.start();
        timbre.start();

    }


}
