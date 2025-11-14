package hackers;

import java.util.Scanner;

public class Main {

    private static final int MAX_CHARS = 4;  // longitud máx de clave
    private static final int NUM_HACKS = 5;  // por enunciado: total = NUM_HACKS * 3
    private static final String STR_FIN = "fin";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Tabla rainbow común para todo el programa
        RainbowTable rainbowTable = new RainbowTable();
        ControlAtaque control = new ControlAtaque(rainbowTable);

        String password;

        do {
            System.out.print("\nIntroduce contraseña (\"" + STR_FIN + "\" para terminar): ");
            password = sc.nextLine().trim();

            if (!password.equalsIgnoreCase(STR_FIN)) {

                control.nuevaVictima(password);

                int totalHackers = NUM_HACKS * 3;
                Hacker[] hackers = new Hacker[totalHackers];
                int idx = 0;

                // Hackers alfabéticos
                for (int i = 0; i < NUM_HACKS; i++) {
                    hackers[idx++] = new Hacker("H_ALFA_" + i, control, TipoClave.ALFABETICA, MAX_CHARS);
                }

                // Hackers numéricos
                for (int i = 0; i < NUM_HACKS; i++) {
                    hackers[idx++] = new Hacker("H_NUM_" + i, control, TipoClave.NUMERICA, MAX_CHARS);
                }

                // Hackers mixtos
                for (int i = 0; i < NUM_HACKS; i++) {
                    hackers[idx++] = new Hacker("H_MIX_" + i, control, TipoClave.MIXTA, MAX_CHARS);
                }

                // Lanzar threads
                for (Hacker h : hackers) h.start();

                // Esperar a que todos terminen
                for (Hacker h : hackers) {
                    try {
                        h.join();
                    } catch (InterruptedException e) {}
                }

                // Resumen de ronda
                if (control.getClaveGanadora() != null) {
                    System.out.println("\n>>> RESUMEN RONDA:");
                    System.out.println("    Hacker ganador: " + control.getHackerGanador());
                    System.out.println("    Clave candidata hallada: " + control.getClaveGanadora());
                }
            }

        } while (!password.equalsIgnoreCase(STR_FIN));

        System.out.println("\nFin del programa. ¡Servidor desconectado!");
        sc.close();
    }
}
