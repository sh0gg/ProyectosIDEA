package clase2025_09_26;

/*
Simúlese una votación en las elecciones de un municipio de CENSO~10.000 personas,
donde un Votante (hilo) piensa su voto durante un tiempo aleatorio no mayor a medio segundo y
luego vota (aleatoriamente) a uno de los NUM_PARTIDOS~5 partidos que se presentan.

Visualice al final los votos de cada partido y el total, comprobando que no hubo
"abstenciones" (~ se han contabilizado bien todos los votos).

Visualice el ganador (mejor, sin ordenar previamente el array ;).

De no haberlo, visualice qué partidos han empatado.
 */

import java.util.Random;

public class EleccionesMunicipales extends Thread {

    static Random r() {
        return new Random();
    }

    public static final int NUM_VOTANTES = 10000;
    public static final String[] PARTIDO = {"Partido populoso", "Partido socialmente obrero", "Box", "Lo conseguiremos", "Frente Judaico Popular/Frente Popular de Judea"};
    public static int[] votos = new int[5];

    @Override
    public void run() {
        try {
            Thread.sleep(r().nextInt(500));
            Votar.votar();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        EleccionesMunicipales[] em = new EleccionesMunicipales[NUM_VOTANTES];
        int ganador = 0;
        int ganadorVotos = 0;

        for (int i = 0; i < NUM_VOTANTES; i++) {
            em[i] = new EleccionesMunicipales();
            em[i].start();
        }

        for (int i = 0; i < NUM_VOTANTES; i++) {
            em[i].join();
        }

        int votosTotales = 0;
        for (int voto : votos) {
            votosTotales += voto;
        }

        System.out.println("Elecciones Municipales");
        for (int i = 0; i < PARTIDO.length; i++) {
            System.out.println(" - " + PARTIDO[i] + ": " + votos[i] + " votos (" + (votos[i] / votosTotales) * 100 + "%)");
            if (votos[i] > ganadorVotos) {
                ganadorVotos = votos[i];
                ganador = i;
            }
        }

        System.out.println("Votos totales: " + votosTotales);
        System.out.println("Ha ganado " + PARTIDO[ganador] + " con " + votos[ganador] + " votos");

    }

    // TRES PROBLEMAS:

    // NO SE CALCULA EL PORCENTAJE -> NO ENTIENDO NADA
    // FALTAN VOTOS -> SE CHOCAN O NO DA TIEMPO A QUE VOTEN? SOLUCION: CLASE EXTERNA CON EL METODO VOTAR!!!!
    // CALCULAR EL votos[i] MÁS ALTO Y SACAR GANADOR!! DONE!!
}
