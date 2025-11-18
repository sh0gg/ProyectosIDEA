package persistencia;

import clases.ClasesEquipo.Equipos;
import jakarta.xml.bind.JAXBException;

public class EquiposJAXB {

    public static Equipos leerEquipos(String rutaXML) throws JAXBException {
        try {
            return XMLJAXBUtils.unmarshal(Equipos.class, rutaXML);
        } catch (JAXBException e) {
            throw new JAXBException("Error al leer el XML de equipos: " + rutaXML, e);
        }
    }
}
