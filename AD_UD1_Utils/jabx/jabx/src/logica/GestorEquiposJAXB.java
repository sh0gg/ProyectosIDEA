package logica;

import clases.ClasesEquipo.Equipo;
import clases.ClasesEquipo.Equipos;
import clases.ClasesEquipo.Patrocinador;
import clases.ClasesEquipo.Patrocinadores;
import jakarta.xml.bind.JAXBException;
import persistencia.persistenciaJAXB.EquiposJAXB;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

/**
 * Lógica para trabajar con EQUIPOS usando JAXB.
 * Cubre:
 *  - Punto 1: listar todos los equipos.
 *  - Punto 4: leer fichero de texto con nuevos equipos, aplicar reglas
 *             de duplicados y generar EquiposUpdateJAXB.xml.
 */
public class GestorEquiposJAXB {

    // ====== PUNTO 1: LISTAR EQUIPOS ======
    public void listarEquipos(String rutaXML) {
        try {
            Equipos equipos = EquiposJAXB.leerEquipos(rutaXML);

            if (equipos == null || equipos.getEquipo() == null
                    || equipos.getEquipo().isEmpty()) {
                System.out.println("No hay equipos para mostrar.");
                return;
            }

            for (Equipo e : equipos.getEquipo()) {
                System.out.println("Equipo " + e.getIdEquipo() +
                        " - " + e.getNombre());
                Patrocinadores cont = e.getPatrocinadores();
                if (cont != null && cont.getPatrocinadores() != null) {
                    for (Patrocinador p : cont.getPatrocinadores()) {
                        System.out.println("   Patrocinador: " + p.getNombre() +
                                " | Donación: " + p.getDonacion() +
                                " | Fecha inicio: " + p.getFechaInicio());
                    }
                }
                System.out.println("-----------------------------------");
            }

        } catch (JAXBException e) {
            System.out.println("Error JAXB al leer equipos: " + e.getMessage());
        }
    }

    // ====== PUNTO 4: AÑADIR EQUIPOS DESDE FICHERO TEXTO ======
    /**
     * Lee un fichero de texto con el formato del enunciado y
     * añade equipos al XML, aplicando las reglas:
     *
     * 1) Código repetido  -> no se inserta el equipo.
     * 2) Nombre repetido  -> no se inserta el equipo.
     * 3) Patrocinador repetido (en el MISMO equipo de la línea)
     *    -> se inserta solo el PRIMER patrocinador con ese nombre.
     */
    public void actualizarEquiposDesdeTxt(String rutaEquiposXML,
                                          String rutaFicheroTxt,
                                          String rutaSalidaXML) {
        try {
            // 1) Leer equipos actuales
            Equipos equipos = EquiposJAXB.leerEquipos(rutaEquiposXML);
            List<Equipo> lista = equipos.getEquipo();

            // Conjuntos para detectar duplicados de código y nombre
            Set<String> codigos = new HashSet<>();
            Set<String> nombres = new HashSet<>();
            for (Equipo e : lista) {
                codigos.add(e.getIdEquipo());
                nombres.add(e.getNombre().toLowerCase());
            }

            // 2) Leer líneas del fichero de texto
            List<String> lineas = Files.readAllLines(
                    Path.of(rutaFicheroTxt), StandardCharsets.UTF_8);

            for (String linea : lineas) {
                if (linea == null || linea.isBlank()) continue;

                // Ejemplo:
                // E9, Celta, Patrocinadores: Nike|1500.0|2025-01-15;Adidas|2500.0|2024-03-15
                String[] partes = linea.split("Patrocinadores:");
                String cabecera = partes[0].trim();  // "E9, Celta," ...
                String datosPatrocinadores = partes.length > 1
                        ? partes[1].trim() : "";

                String[] cab = cabecera.split(",");
                if (cab.length < 2) {
                    System.out.println("Línea mal formada (cabecera): " + linea);
                    continue;
                }
                String id = cab[0].trim();
                String nombreEquipo = cab[1].trim();

                // Reglas de duplicados de equipo
                if (codigos.contains(id)) {
                    System.out.println("No se inserta equipo " + id +
                            " (código repetido).");
                    continue;
                }
                if (nombres.contains(nombreEquipo.toLowerCase())) {
                    System.out.println("No se inserta equipo " + id +
                            " (nombre repetido).");
                    continue;
                }

                // Crear equipo nuevo
                Equipo nuevo = new Equipo();
                nuevo.setIdEquipo(id);
                nuevo.setNombre(nombreEquipo);
                Patrocinadores cont = new Patrocinadores();
                nuevo.setPatrocinadores(cont);

                // 3) Procesar patrocinadores de la línea
                if (!datosPatrocinadores.isEmpty()) {
                    String[] bloques = datosPatrocinadores.split(";");
                    Set<String> nombresPatLocal = new HashSet<>();

                    for (String bloque : bloques) {
                        if (bloque.isBlank()) continue;
                        String[] campos = bloque.trim().split("\\|");
                        if (campos.length != 3) {
                            System.out.println("Patrocinador mal formado en línea: " + linea);
                            continue;
                        }
                        String nomPat = campos[0].trim();
                        float donacion = Float.parseFloat(campos[1].trim());
                        LocalDate fecha = LocalDate.parse(campos[2].trim());

                        // Regla 3: si en la MISMA línea ya vimos ese nombre, se ignora
                        String claveNom = nomPat.toLowerCase();
                        if (nombresPatLocal.contains(claveNom)) {
                            System.out.println("Patrocinador repetido en equipo "
                                    + id + " (se ignora): " + nomPat);
                            continue;
                        }
                        nombresPatLocal.add(claveNom);

                        Patrocinador p = new Patrocinador(nomPat, donacion, fecha);
                        cont.addPatrocinador(p);      // el propio cont actualiza numPatrocinadores
                    }
                }

                // 4) Añadir equipo a la lista y actualizar conjuntos globales
                lista.add(nuevo);
                codigos.add(id);
                nombres.add(nombreEquipo.toLowerCase());
                System.out.println("Equipo insertado correctamente: " +
                        id + " - " + nombreEquipo);
            }

            // 5) Guardar en el XML de salida
            EquiposJAXB.escribirEquipos(rutaSalidaXML, equipos);
            System.out.println("Equipos actualizados guardados en: " + rutaSalidaXML);

        } catch (JAXBException e) {
            System.out.println("Error JAXB con equipos: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error leyendo fichero de texto: " + e.getMessage());
        }
    }
}
