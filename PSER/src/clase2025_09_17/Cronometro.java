package clase2025_09_17;

import java.time.Duration;
import java.time.LocalDateTime;

public class Cronometro extends Thread {
    private LocalDateTime inicio;

    @Override
    public void run() {
        inicio = LocalDateTime.now();

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(100); // Actualiza cada 100ms
            } catch (InterruptedException e) {
                System.out.println("\nCronómetro interrumpido");
                break;
            }

            Duration transcurrido = Duration.between(inicio, LocalDateTime.now());
            long horas = transcurrido.toHours();
            long minutos = transcurrido.toMinutes() % 60;
            long segundos = transcurrido.getSeconds() % 60;
            long milisegundos = transcurrido.toMillis() % 1000;

            System.out.printf("\rTiempo transcurrido: %02d:%02d:%02d.%03d", horas, minutos, segundos, milisegundos);
            System.out.flush(); // Asegura que se imprima de inmediato
        }
    }

    public static void main(String[] args) {
        Cronometro cronometro = new Cronometro();
        cronometro.start();

        // Ejemplo: detener el cronómetro después de 10 segundos
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {}

        cronometro.interrupt();
    }
}
