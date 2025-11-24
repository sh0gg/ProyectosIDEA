package persistenciaJAXB;

import clases.Alojamientos;
import jakarta.xml.bind.JAXBException;

public class AlojamientosJAXB {

    public static Alojamientos leerAlojamientos(String rutaXML) throws JAXBException {
        try {
            return XMLJAXBUtils.unmarshal(Alojamientos.class, rutaXML);
        } catch (JAXBException e) {
            throw new JAXBException("Error al leer el XML de alojamientos: " + rutaXML, e);
        }
    }
}
