package persistencia;

import clases.ClasesCorredor.Corredor;
import clases.ClasesCorredor.Corredores;
import jakarta.xml.bind.JAXBException;

public class CorredoresJAXB {

    public static Corredores leerCorredores(String rutaXML) throws JAXBException {
        try {
            return XMLJAXBUtils.unmarshal(Corredores.class, rutaXML);
        } catch (JAXBException e) {
            throw new JAXBException("Error al leer el XML de equipos: " + rutaXML, e);
        }
    }

    public static void escribirCorredores(String rutaXML, Corredores corredores) throws JAXBException {
        try {
            XMLJAXBUtils.marshal(corredores, rutaXML);
        } catch (JAXBException e) {
            throw new JAXBException("Error al escribir el XML de equipos: " + rutaXML, e);
        }
    }
}
