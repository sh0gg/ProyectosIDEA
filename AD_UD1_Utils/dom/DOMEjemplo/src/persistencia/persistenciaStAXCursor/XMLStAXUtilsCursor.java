package persistencia.persistenciaStAXCursor;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;

public class XMLStAXUtilsCursor {

    // La enum puedes usarla igual que en SAX
    public enum TipoValidacion {
        NO_VALIDAR, DTD, XSD
    }

    // En el examen no te líes: ignora la validación y crea el reader
    public static XMLStreamReader cargarDocumentoStAX(String rutaFichero,
                                                      TipoValidacion validacion) throws Exception {
        if (rutaFichero == null || rutaFichero.isEmpty()) {
            throw new IllegalArgumentException("Ruta de fichero vacía");
        }

        XMLInputFactory factory = XMLInputFactory.newInstance();
        // si quieres, aquí podrías configurar seguridad / namespaces

        return factory.createXMLStreamReader(
                new FileInputStream(rutaFichero),
                "UTF-8"
        );
    }

    // Helper cómodo para leer texto de un elemento
    public static String leerTexto(javax.xml.stream.XMLStreamReader reader) throws Exception {
        reader.next(); // avanzar al CHARACTERS
        return reader.getText().trim();
    }
}
