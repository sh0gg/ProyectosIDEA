package persistencia.persistenciaStAXEventos;

import clases.ClasesCorredor.*;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CorredoresStAXEventos {

    // 1) Listar todos con modelo eventos
    public List<Corredor> cargarTodosCorredoresEventos(String rutaXML,
                                                       XMLStAXUtilsEventos.TipoValidacion validacion)
            throws Exception {

        XMLEventReader lector = XMLStAXUtilsEventos.cargarDocumentoStAX(rutaXML, validacion);
        return leerCorredores(lector, null);
    }

    // 2) Listar por equipo con modelo eventos
    public List<Corredor> leerCorredoresPorEquipo(String rutaXML,
                                                  String equipoBuscado,
                                                  XMLStAXUtilsEventos.TipoValidacion validacion)
            throws Exception {

        XMLEventReader lector = XMLStAXUtilsEventos.cargarDocumentoStAX(rutaXML, validacion);
        return leerCorredores(lector, equipoBuscado);
    }

    private List<Corredor> leerCorredores(XMLEventReader lector, String equipoFiltro)
            throws Exception {

        List<Corredor> lista = new ArrayList<>();
        Corredor actual = null;
        String etiquetaActual = "";
        int anioPuntuacion = 0;

        while (lector.hasNext()) {
            XMLEvent e = lector.nextEvent();

            if (e.isStartElement()) {
                StartElement st = e.asStartElement();
                etiquetaActual = st.getName().getLocalPart();

                switch (etiquetaActual) {
                    case "velocista" -> {
                        actual = new Velocista();
                        cargarAtributosBasicos(st, actual);
                    }
                    case "fondista" -> {
                        actual = new Fondista();
                        cargarAtributosBasicos(st, actual);
                    }
                    case "puntuacion" -> {
                        Attribute a = st.getAttributeByName(new QName("anio"));
                        anioPuntuacion = Integer.parseInt(a.getValue());
                    }
                }
            }

            if (e.isCharacters() && actual != null) {
                String txt = e.asCharacters().getData().trim();
                if (!txt.isEmpty()) {
                    switch (etiquetaActual) {
                        case "nombre" -> actual.setNombre(txt);
                        case "fecha_nacimiento" -> actual.setFechaNacimiento(LocalDate.parse(txt));
                        case "velocidad_media" -> ((Velocista) actual).setVelocidadMedia(Float.parseFloat(txt));
                        case "distancia_max" -> ((Fondista) actual).setDistanciaMax(Float.parseFloat(txt));
                        case "puntuacion" ->
                                actual.addOrUpdatePuntuacion(new Puntuacion(anioPuntuacion, Float.parseFloat(txt)));
                    }
                }
            }

            if (e.isEndElement()) {
                String cierre = e.asEndElement().getName().getLocalPart();
                if ((cierre.equals("velocista") || cierre.equals("fondista")) && actual != null) {
                    if (equipoFiltro == null ||
                            equipoFiltro.equalsIgnoreCase(actual.getEquipo())) {
                        lista.add(actual);
                    }
                    actual = null;
                }
            }
        }

        lector.close();
        return lista;
    }

    private void cargarAtributosBasicos(StartElement st, Corredor c) {
        c.setCodigo(st.getAttributeByName(new QName("codigo")).getValue());
        c.setDorsal(Integer.parseInt(st.getAttributeByName(new QName("dorsal")).getValue()));
        c.setEquipo(st.getAttributeByName(new QName("equipo")).getValue());
    }
}
