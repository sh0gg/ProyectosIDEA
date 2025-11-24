package persistenciaJAXB;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class XMLJAXBUtils {

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
