package clase2025_09_26;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EleccionesMunicipales extends Thread {

    public static final int NUM_VOTANTES = 10000;
    public static final String[] PARTIDO = {
            "Partido populoso",
            "Partido socialmente obrero",
            "Box", "Lo conseguiremos",
            "Frente Judaico Popular o Frente Popular de Judea"
    };
    public static int[] votos = new int[PARTIDO.length];

    // Urnas por partido
    public static final Object[] URNAS = new Object[PARTIDO.length];

    static {
        for (int i = 0; i < URNAS.length; i++) {
            URNAS[i] = new Object();
        }
    }

    private final int voto;

    public EleccionesMunicipales() {
        this.voto = new Random().nextInt(PARTIDO.length);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (URNAS[voto]) {
            votos[voto]++;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        EleccionesMunicipales[] em = new EleccionesMunicipales[NUM_VOTANTES];

        for (int i = 0; i < NUM_VOTANTES; i++) {
            em[i] = new EleccionesMunicipales();
            em[i].start();
        }

        for (int i = 0; i < NUM_VOTANTES; i++) {
            em[i].join();
        }

        int votosTotales = 0;
        int maxVotos = 0;
        for (int v : votos) votosTotales += v;

        System.out.println("Elecciones Municipales");

        List<Integer> partidosGanadores = new ArrayList<>();

        for (int i = 0; i < PARTIDO.length; i++) {
            float porcentaje = (((float) votos[i] / votosTotales) * 100);
            System.out.printf(" - %s: %d votos (%.2f%%)\n", PARTIDO[i], votos[i], porcentaje);
            if (votos[i] > maxVotos) {
                maxVotos = votos[i];
                partidosGanadores.clear();
                partidosGanadores.add(i);
            }
            if (votos[i] == maxVotos) {
                partidosGanadores.add(i);
            }
        }

        System.out.println("Votos totales: " + votosTotales);

        if (partidosGanadores.size() > 1) {
            System.out.println("Hay empate entre los partidos:");
            for (int i : partidosGanadores) {
                System.out.println(" - " + PARTIDO[i]);
            }
        } else {
            System.out.println("El ganador es: " + PARTIDO[partidosGanadores.getFirst()]);
        }


    }
}
