package persistenciaSAX;

import clases.Corredor;
import clases.Fondista;
import clases.Puntuacion;
import clases.Velocista;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CorredorSAXHandler extends DefaultHandler {
    private final List<Corredor> corredores = new ArrayList<Corredor>();
    private Corredor corredorActual;
    private List<Puntuacion> historialActual;
    private String contenidoActual = "";
    private String anioActual;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        contenidoActual = "";

        if (corredorActual == null) {
            return;
        }

        switch (qName) {
            case "velocista", "fondista" -> {
                corredorActual = crearCorredor(qName);
                asignarAtributosCorredor(attributes);
            }
            case "historial" -> {
                historialActual = new ArrayList<>();
            }
            case "puntuacion" -> {
                anioActual = attributes.getValue("anio");
            }
            default -> {
                // no hace naaaaa
            }
        }
    }

    private void asignarAtributosCorredor(Attributes attributes) {
        corredorActual.setCodCorredor(attributes.getValue("codigo"));
        corredorActual.setDorsal(Integer.parseInt(attributes.getValue("dorsal")));
        corredorActual.setEquipoId(Integer.parseInt(attributes.getValue("equipoId")));
    }

    private Corredor crearCorredor(String tipo) {
        return (tipo.equals("fondista") ? new Fondista() : new Velocista());
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (corredorActual == null) {
            return;
        }
        switch (qName) {
            case "nombre" -> {
                corredorActual.setNombre(contenidoActual);
            }
            case "fecha_nacimiento" -> {
                corredorActual.setFechaNacimiento(contenidoActual);
            }
            case "velocidad_media" -> {
                if (corredorActual instanceof Velocista v) {
                    v.setVelocidadMedia(Float.parseFloat(contenidoActual));
                }
            }
            case "distancia_max" -> {
                if (corredorActual instanceof Fondista f) {
                    f.setDistanciaMaxima(Float.parseFloat(contenidoActual));
                }
            }
            case "puntuacion" -> {
                Puntuacion p = new Puntuacion();
                p.setAnio(Integer.parseInt(anioActual));
                p.setPuntos(Float.parseFloat(contenidoActual));
                historialActual.add(p);
            }
        }
        corredores.add(corredorActual);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        contenidoActual = String.valueOf(ch, start, length).trim();
    }
}
