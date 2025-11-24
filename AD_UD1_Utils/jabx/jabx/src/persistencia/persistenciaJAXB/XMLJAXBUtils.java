package persistencia.persistenciaJAXB;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

/**
 * Utilidades genéricas para trabajar con JAXB:
 *  - marshal  : objeto Java  -> XML
 *  - unmarshal: XML          -> objeto Java
 */
public class XMLJAXBUtils {

    /**
     * Convierte un objeto Java en un fichero XML.
     *
     * @param objeto      objeto raíz anotado con @XmlRootElement
     * @param rutaArchivo ruta del XML de salida
     */
    public static <T> void marshal(T objeto, String rutaArchivo) throws JAXBException {
        if (objeto == null) {
            throw new IllegalArgumentException("El objeto a convertir no puede ser null");
        }

        // 1. Contexto JAXB para la clase concreta del objeto
        JAXBContext context = JAXBContext.newInstance(objeto.getClass());

        // 2. Marshaller
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // 3. Escritura a fichero
        marshaller.marshal(objeto, new File(rutaArchivo));
    }

    /**
     * Convierte un fichero XML en un objeto Java.
     *
     * @param clase       clase raíz anotada con @XmlRootElement
     * @param rutaArchivo ruta del XML de entrada
     * @return instancia de la clase con los datos del XML
     */
    public static <T> T unmarshal(Class<T> clase, String rutaArchivo) throws JAXBException {
        if (clase == null) {
            throw new IllegalArgumentException("La clase raíz no puede ser null");
        }

        JAXBContext context = JAXBContext.newInstance(clase);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Object resultado = unmarshaller.unmarshal(new File(rutaArchivo));
        return clase.cast(resultado);
    }
}
