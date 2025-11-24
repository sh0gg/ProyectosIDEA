package logica;

import clases.ClasesCorredor.*;
import jakarta.xml.bind.JAXBException;
import persistencia.persistenciaJAXB.CorredoresJAXB;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Lógica para trabajar con corredores usando JAXB.
 * Cubre:
 *  - Punto 2: listar todos los corredores.
 *  - Punto 3: crear CorredoresNuevos.xml con corredores en memoria.
 */
public class GestorCorredoresJAXB {

    private String rutaCorredoresXML;

    public GestorCorredoresJAXB(String rutaCorredoresXML) {
        this.rutaCorredoresXML = rutaCorredoresXML;
    }

    // ====== PUNTO 2: LISTAR CORREDORES EXISTENTES CON JAXB ======
    public void listarCorredores() {
        try {
            Corredores corredores = CorredoresJAXB.leerCorredores(rutaCorredoresXML);
            List<Corredor> lista = corredores.getLista();

            if (lista == null || lista.isEmpty()) {
                System.out.println("No hay corredores en el XML.");
                return;
            }

            for (Corredor c : lista) {
                if (c instanceof Velocista v) {
                    System.out.println("Tipo: VELOCISTA");
                    System.out.println("  Nombre: " + v.getNombre());
                    System.out.println("  Fecha nac.: " + v.getFechaNacimiento());
                    System.out.println("  Velocidad media: " + v.getVelocidadMedia());
                    System.out.println("  Historial:");
                    if (v.getHistorial() != null) {
                        v.getHistorial().forEach(p ->
                                System.out.println("    Año " + p.getAnio() +
                                        " -> " + p.getPuntos()));
                    }
                } else if (c instanceof Fondista f) {
                    System.out.println("Tipo: FONDISTA");
                    System.out.println("  Nombre: " + f.getNombre());
                    System.out.println("  Fecha nac.: " + f.getFechaNacimiento());
                    System.out.println("  Distancia máx.: " + f.getDistanciaMax());
                    System.out.println("  Historial:");
                    if (f.getHistorial() != null) {
                        f.getHistorial().forEach(p ->
                                System.out.println("    Año " + p.getAnio() +
                                        " -> " + p.getPuntos()));
                    }
                }
                System.out.println("-----------------------------------");
            }

        } catch (JAXBException e) {
            System.out.println("Error JAXB al leer corredores: " + e.getMessage());
        }
    }

    // ====== PUNTO 3: CREAR CorredoresNuevos.xml DESDE CERO ======
    public void crearCorredoresNuevos(String rutaSalida) {
        try {
            // 1) Crear lista de corredores en memoria
            List<Corredor> lista = new ArrayList<>();

            Velocista v1 = new Velocista("V1", 1, "Usain Bolt",
                    LocalDate.of(1986, 8, 21), "E1", 27.5f);
            v1.getHistorial().add(new Puntuacion(2022, 95.0f));
            v1.getHistorial().add(new Puntuacion(2023, 98.0f));

            Velocista v2 = new Velocista("V2", 2, "Velocista Dos",
                    LocalDate.of(1995, 3, 10), "E2", 24.3f);

            Fondista f1 = new Fondista("F1", 11, "Kenenisa Bekele",
                    LocalDate.of(1982, 6, 13), "E3", 42.195f);
            f1.getHistorial().add(new Puntuacion(2021, 90.0f));

            Fondista f2 = new Fondista("F2", 12, "Fondista Dos",
                    LocalDate.of(1990, 1, 5), "E2", 30.0f);

            lista.add(v1);
            lista.add(v2);
            lista.add(f1);
            lista.add(f2);

            // 2) Encapsular en objeto raíz Corredores
            Corredores nuevos = new Corredores();
            nuevos.setLista(lista);

            // 3) Guardar en fichero nuevo
            CorredoresJAXB.escribirCorredores(rutaSalida, nuevos);
            System.out.println("CorredoresNuevos JAXB guardado en: " + rutaSalida);

        } catch (JAXBException e) {
            System.out.println("Error JAXB al escribir corredores nuevos: " + e.getMessage());
        }
    }
}
