package persistencia.persistenciaSAX;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;

import java.io.FileInputStream;

public class XMLSAXUtils {

    // Tipos de validación que indica el enunciado
    public enum TipoValidacion {
        NO_VALIDAR, DTD, XSD
    }

    // Método principal para procesar un XML con SAX
    public static void procesarDocumento(String rutaFichero,
                                         org.xml.sax.helpers.DefaultHandler handler,
                                         TipoValidacion validacion)
            throws Exception {

        if (rutaFichero == null || rutaFichero.isEmpty())
            throw new Exception("Ruta vacía");

        // 1. Crear SAXParserFactory con la validación que toque
        SAXParserFactory factory = configurarParser(validacion);

        // 2. Crear parser
        SAXParser parser = factory.newSAXParser();

        // 3. Obtener XMLReader desde JAXP (apuntes pág. 5) :contentReference[oaicite:3]{index=3}
        XMLReader reader = parser.getXMLReader();

        // 4. Registrar el manejador
        reader.setContentHandler(handler);

        // 5. Procesar XML
        reader.parse(new InputSource(new FileInputStream(rutaFichero)));
    }

    // Configura validación según DTD / XSD / ninguna
    private static SAXParserFactory configurarParser(TipoValidacion v) {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        switch (v) {
            case NO_VALIDAR -> {
                factory.setValidating(false);
                factory.setNamespaceAware(false);
            }
            case DTD -> {
                factory.setValidating(true);
                factory.setNamespaceAware(false);
            }
            case XSD -> {
                factory.setValidating(true);
                factory.setNamespaceAware(true); // obligatorio para XSD
            }
        }
        return factory;
    }
}
