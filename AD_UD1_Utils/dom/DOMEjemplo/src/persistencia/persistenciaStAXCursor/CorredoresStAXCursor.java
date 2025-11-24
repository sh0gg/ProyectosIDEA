package persistencia.persistenciaStAXCursor;

import clases.ClasesCorredor.*;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CorredoresStAXCursor {

    // 1) Listar TODOS los corredores usando cursor
    public List<Corredor> cargarTodosCorredoresCursor(String rutaXML,
                                                      XMLStAXUtilsCursor.TipoValidacion validacion)
            throws Exception {

        XMLStreamReader reader = XMLStAXUtilsCursor.cargarDocumentoStAX(rutaXML, validacion);
        return leerCorredores(reader, null);   // null => sin filtro de equipo
    }

    // 2) Listar SOLO los de un equipo usando cursor
    public List<Corredor> leerCorredoresPorEquipo(String rutaXML,
                                                  String equipoBuscado,
                                                  XMLStAXUtilsCursor.TipoValidacion validacion)
            throws Exception {

        XMLStreamReader reader = XMLStAXUtilsCursor.cargarDocumentoStAX(rutaXML, validacion);
        return leerCorredores(reader, equipoBuscado);
    }

    // Método privado con la lógica común de lectura
    private List<Corredor> leerCorredores(XMLStreamReader reader, String equipoFiltro)
            throws Exception {

        List<Corredor> lista = new ArrayList<>();
        Corredor actual = null;
        int anioPuntuacion = 0;
        String etiquetaActual = "";

        while (reader.hasNext()) {
            int evento = reader.next();

            switch (evento) {

                case XMLStreamConstants.START_ELEMENT -> {
                    etiquetaActual = reader.getLocalName();

                    switch (etiquetaActual) {
                        case "velocista" -> {
                            actual = new Velocista();
                            cargarAtributosBasicos(reader, actual);
                        }
                        case "fondista" -> {
                            actual = new Fondista();
                            cargarAtributosBasicos(reader, actual);
                        }
                        case "puntuacion" ->
                                anioPuntuacion = Integer.parseInt(reader.getAttributeValue(null, "anio"));
                        case "nombre" ->
                                actual.setNombre(XMLStAXUtilsCursor.leerTexto(reader));
                        case "fecha_nacimiento" ->
                                actual.setFechaNacimiento(LocalDate.parse(XMLStAXUtilsCursor.leerTexto(reader)));
                        case "velocidad_media" ->
                                ((Velocista) actual).setVelocidadMedia(
                                        Float.parseFloat(XMLStAXUtilsCursor.leerTexto(reader)));
                        case "distancia_max" ->
                                ((Fondista) actual).setDistanciaMax(
                                        Float.parseFloat(XMLStAXUtilsCursor.leerTexto(reader)));
                    }
                }

                case XMLStreamConstants.CHARACTERS -> {
                    // solo usamos texto explícito en leerTexto(), aquí nada
                }

                case XMLStreamConstants.END_ELEMENT -> {
                    String cierre = reader.getLocalName();
                    if ((cierre.equals("velocista") || cierre.equals("fondista")) && actual != null) {
                        // aplicar filtro de equipo si corresponde
                        if (equipoFiltro == null ||
                                equipoFiltro.equalsIgnoreCase(actual.getEquipo())) {
                            lista.add(actual);
                        }
                        actual = null;
                    } else if (cierre.equals("puntuacion") && actual != null) {
                        // el texto de la puntuación ya se ha leído en leerTexto
                        // así que recuperamos el último valor leído del reader:
                        // truco sencillo: el último texto está en getText()
                        float puntos = Float.parseFloat(reader.getElementText());
                        actual.addOrUpdatePuntuacion(new Puntuacion(anioPuntuacion, puntos));
                    }
                }
            }
        }

        reader.close();
        return lista;
    }

    private void cargarAtributosBasicos(XMLStreamReader reader, Corredor c) {
        c.setCodigo(reader.getAttributeValue(null, "codigo"));
        c.setDorsal(Integer.parseInt(reader.getAttributeValue(null, "dorsal")));
        c.setEquipo(reader.getAttributeValue(null, "equipo"));
    }
}
