package persistencia.persistenciaSAX;

import clases.ClasesCorredor.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CorredoresSAXHandler extends DefaultHandler {

    private List<Corredor> corredores = new ArrayList<>();
    private Corredor actual = null;

    private StringBuilder contenido = new StringBuilder();
    private boolean leyendoHistorial = false;
    private int anioActual;

    public List<Corredor> getCorredores() {
        return corredores;
    }

    @Override
    public void startElement(String uri, String local, String qName, Attributes att) {
        contenido.setLength(0); // limpiar texto

        switch (qName) {
            case "velocista" -> {
                actual = new Velocista();
                actual.setCodigo(att.getValue("codigo"));
                actual.setDorsal(Integer.parseInt(att.getValue("dorsal")));
                actual.setEquipo(att.getValue("equipo"));
            }
            case "fondista" -> {
                actual = new Fondista();
                actual.setCodigo(att.getValue("codigo"));
                actual.setDorsal(Integer.parseInt(att.getValue("dorsal")));
                actual.setEquipo(att.getValue("equipo"));
            }
            case "historial" -> leyendoHistorial = true;
            case "puntuacion" -> anioActual = Integer.parseInt(att.getValue("anio"));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        contenido.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String local, String qName) {

        String texto = contenido.toString().trim();

        if (actual != null) {
            switch (qName) {
                case "nombre" -> actual.setNombre(texto);

                case "fecha_nacimiento" ->
                        actual.setFechaNacimiento(LocalDate.parse(texto));

                case "velocidad_media" -> {
                    if (actual instanceof Velocista v)
                        v.setVelocidadMedia(Float.parseFloat(texto));
                }

                case "distancia_max" -> {
                    if (actual instanceof Fondista f)
                        f.setDistanciaMax(Float.parseFloat(texto));
                }

                case "puntuacion" -> {
                    if (leyendoHistorial)
                        actual.addOrUpdatePuntuacion(new Puntuacion(anioActual,
                                Float.parseFloat(texto)));
                }

                case "historial" -> leyendoHistorial = false;

                case "velocista", "fondista" -> {
                    corredores.add(actual);
                    actual = null;
                }
            }
        }
    }
}
