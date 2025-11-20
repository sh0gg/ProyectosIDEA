package persistencia;

import clases.ClasesEquipo.Equipos;
import jakarta.xml.bind.JAXBException;

/**
 * Clase de acceso a la persistencia XML de equipos usando JAXB.
 */
public class EquiposJAXB {
    /**
     * Lee el XML de equipos y lo convierte en un objeto Equipos.
     */
    public static Equipos leerEquipos(String rutaXML) throws JAXBException {
        try {
            return XMLJAXBUtils.unmarshal(Equipos.class, rutaXML);
        } catch (JAXBException e) {
            throw new JAXBException("Error al leer el XML de equipos: " + rutaXML, e);
        }
    }
    /**
     * NUEVO:
     * Escribe un objeto Equipos en la ruta indicada.
     * Te servirá, por ejemplo, para guardar EquiposUpdateJAXB.xml.
     */
    public static void escribirEquipos(String rutaXML, Equipos equipos) throws JAXBException {
        try {
            XMLJAXBUtils.marshal(equipos, rutaXML);
        } catch (JAXBException e) {
            throw new JAXBException("Error al escribir el XML de equipos: " + rutaXML, e);
        }
    }
}
