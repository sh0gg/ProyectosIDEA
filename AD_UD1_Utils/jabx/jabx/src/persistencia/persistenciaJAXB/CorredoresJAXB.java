package persistencia.persistenciaJAXB;

import clases.ClasesCorredor.Corredores;
import jakarta.xml.bind.JAXBException;

/**
 * Acceso a la persistencia XML de CORREDORES usando JAXB.
 */
public class CorredoresJAXB {

    /**
     * Lee el XML de corredores y lo convierte en un objeto Corredores.
     */
    public static Corredores leerCorredores(String rutaXML) throws JAXBException {
        try {
            return XMLJAXBUtils.unmarshal(Corredores.class, rutaXML);
        } catch (JAXBException e) {
            throw new JAXBException("Error al leer el XML de corredores: " + rutaXML, e);
        }
    }

    /**
     * Escribe un objeto Corredores en el XML indicado.
     * Útil para generar, por ejemplo, CorredoresNuevosJAXB.xml.
     */
    public static void escribirCorredores(String rutaXML, Corredores corredores) throws JAXBException {
        try {
            XMLJAXBUtils.marshal(corredores, rutaXML);
        } catch (JAXBException e) {
            throw new JAXBException("Error al escribir el XML de corredores: " + rutaXML, e);
        }
    }
}
