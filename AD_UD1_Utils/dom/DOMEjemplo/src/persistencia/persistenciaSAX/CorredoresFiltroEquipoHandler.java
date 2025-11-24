package persistencia.persistenciaSAX;

import clases.ClasesCorredor.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CorredoresFiltroEquipoHandler extends DefaultHandler {

    private String equipoFiltro;
    private List<Corredor> filtrados = new ArrayList<>();
    private Corredor actual = null;
    private StringBuilder contenido = new StringBuilder();
    private int anioActual;

    public CorredoresFiltroEquipoHandler(String equipo) {
        this.equipoFiltro = equipo;
    }

    public List<Corredor> getCorredoresFiltrados() {
        return filtrados;
    }

    @Override
    public void startElement(String uri, String local, String q, Attributes att) {
        contenido.setLength(0);

        if (q.equals("velocista") || q.equals("fondista")) {
            String eq = att.getValue("equipo");
            if (eq.equalsIgnoreCase(equipoFiltro)) {
                actual = q.equals("velocista") ? new Velocista() : new Fondista();
                actual.setCodigo(att.getValue("codigo"));
                actual.setDorsal(Integer.parseInt(att.getValue("dorsal")));
                actual.setEquipo(eq);
            }
        }
        if (q.equals("puntuacion"))
            anioActual = Integer.parseInt(att.getValue("anio"));
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        contenido.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String local, String q) {
        String txt = contenido.toString().trim();

        if (actual != null) {
            switch (q) {
                case "nombre" -> actual.setNombre(txt);
                case "fecha_nacimiento" -> actual.setFechaNacimiento(LocalDate.parse(txt));

                case "velocidad_media" ->
                        { if (actual instanceof Velocista v) v.setVelocidadMedia(Float.parseFloat(txt)); }

                case "distancia_max" ->
                        { if (actual instanceof Fondista f) f.setDistanciaMax(Float.parseFloat(txt)); }

                case "puntuacion" ->
                        actual.addOrUpdatePuntuacion(new Puntuacion(anioActual, Float.parseFloat(txt)));

                case "velocista", "fondista" -> {
                    filtrados.add(actual);
                    actual = null;
                }
            }
        }
    }
}
