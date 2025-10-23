package A3UD1_DavidBesadaRamilo.principal;

import A3UD1_DavidBesadaRamilo.entradasalida.Archivo;
import A3UD1_DavidBesadaRamilo.modelo.*;
import A3UD1_DavidBesadaRamilo.servicios.GestorCorredores;
import A3UD1_DavidBesadaRamilo.servicios.GestorEquipos;
import A3UD1_DavidBesadaRamilo.servicios.GestorIntegracion;
import A3UD1_DavidBesadaRamilo.utilidades.UtilidadesFechas;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {
    private static final Path DATA_DIR = Path.of("data");
    private static final Path F_CORREDORES = DATA_DIR.resolve("corredores.dat");
    private static final Path F_EQUIPOS = DATA_DIR.resolve("equipos.dat");

    public static void main(String[] args) {
        try {
            Archivo.asegurarDirectorio(DATA_DIR.toString());

            try (GestorEquipos ge = new GestorEquipos(F_EQUIPOS)) {
                Equipo e0 = new Equipo(0, "Team Relámpago", "ES", 2010, true);
                e0.getSponsors().add(new Patrocinador("TurboCorp", 6000f, LocalDate.of(2022, 3, 1)));
                ge.insertarOVolverIndice(e0);

                Equipo e1 = new Equipo(1, "Panteras del Norte", "FR", 2015, true);
                e1.getSponsors().add(new Patrocinador("Energex", 8000f, LocalDate.of(2023, 5, 15)));
                ge.insertarOVolverIndice(e1);

                Equipo e2 = new Equipo(2, "Ciclones del Sur", "IT", 2018, true);
                ge.insertarOVolverIndice(e2);
            }

            GestorCorredores gc = new GestorCorredores(F_CORREDORES);

            Corredor c1 = new Velocista("Laura Pérez", UtilidadesFechas.parse("12/04/1997"), 0, 45.3f);
            c1.addOrUpdatePuntuacion(2023, 120f);
            c1.addOrUpdatePuntuacion(2024, 135f);
            gc.alta(c1);

            Corredor c2 = new Fondista("Diego Santos", UtilidadesFechas.parse("23/11/1995"), 1, 42.195f);
            c2.addOrUpdatePuntuacion(2023, 160f);
            c2.addOrUpdatePuntuacion(2024, 175f);
            gc.alta(c2);

            Corredor c3 = new Velocista("Elena López", UtilidadesFechas.parse("01/06/2001"), 2, 48.2f);
            c3.addOrUpdatePuntuacion(2024, 110f);
            gc.alta(c3);

            List<Corredor> corredores = gc.listar();

            try (GestorEquipos ge = new GestorEquipos(F_EQUIPOS)) {
                List<Equipo> equipos = ge.leerTodos();
                Map<Integer, Float> totales = GestorIntegracion.totalPuntosPorEquipo(corredores);

                System.out.println("=== Totales por equipo ===");
                totales.forEach((id, pts) -> System.out.println("Equipo " + id + ": " + pts + " puntos"));

                System.out.println("\n=== Ranking ===");
                GestorIntegracion.ranking(totales)
                        .forEach(e -> System.out.println("Equipo " + e.getKey() + " -> " + e.getValue() + " puntos"));

                System.out.println("\n=== Corredores huérfanos ===");
                List<Corredor> huerfanos = GestorIntegracion.corredoresHuerfanos(corredores, equipos);
                if (huerfanos.isEmpty()) System.out.println("Ninguno");
                else huerfanos.forEach(System.out::println);

                System.out.println("\n=== Media por año (equipo 0) ===");
                Map<Integer, Double> medias = GestorIntegracion.mediaPorAnioEquipo(0, corredores);
                medias.forEach((anio, media) ->
                        System.out.println(anio + ": " + String.format("%.2f", media) + " puntos de media"));
            }

            System.out.println("\nProceso completado. Archivos generados en la carpeta /data.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
