package persistencia.persistenciaStAXEventos;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import java.io.FileInputStream;

public class XMLStAXUtilsEventos {

    public enum TipoValidacion {
        NO_VALIDAR, DTD, XSD
    }

    public static XMLEventReader cargarDocumentoStAX(String rutaFichero,
                                                     TipoValidacion validacion) throws Exception {

        if (rutaFichero == null || rutaFichero.isEmpty()) {
            throw new IllegalArgumentException("Ruta de fichero vacía");
        }

        XMLInputFactory factory = XMLInputFactory.newInstance();
        return factory.createXMLEventReader(
                new FileInputStream(rutaFichero),
                "UTF-8"
        );
    }
}
