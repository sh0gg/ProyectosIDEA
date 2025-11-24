package persistenciaDOM;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Utilidades genéricas para manejar DOM (cargar, guardar, crear elementos...).
 * No sabe nada de corredores; sirve para cualquier XML.
 */
public class XMLDOMUtils {

    // Cargar un XML en un Document
    public static Document cargarDocumentoXML(String rutaXML)
            throws Exception {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // Más adelante aquí se activará DTD/XSD si hace falta
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(rutaXML));
        doc.getDocumentElement().normalize();
        return doc;
    }

    // Guardar un Document en fichero
    public static void guardarDocumentoXML(Document doc, String rutaXML)
            throws Exception {

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(
                "{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(rutaXML));
        transformer.transform(source, result);
    }

    // Crear un elemento <nombre> con texto y añadirlo a padre
    public static Element addElement(Document doc, Element padre,
                                     String nombre, String texto) {
        Element e = doc.createElement(nombre);
        if (texto != null) {
            e.setTextContent(texto);
        }
        padre.appendChild(e);
        return e;
    }

    // Añadir/actualizar atributo
    public static void addAtributo(Element elemento,
                                   String nombreAttr, String valor) {
        elemento.setAttribute(nombreAttr, valor);
    }

    // Buscar primer elemento por etiqueta y valor de atributo
    public static Element buscarPorAtributo(Document doc,
                                            String etiqueta,
                                            String nombreAttr,
                                            String valorAttr) {

        NodeList lista = doc.getElementsByTagName(etiqueta);
        for (int i = 0; i < lista.getLength(); i++) {
            Element e = (Element) lista.item(i);
            if (valorAttr.equals(e.getAttribute(nombreAttr))) {
                return e;
            }
        }
        return null;
    }

    // Eliminar un nodo del DOM (si no es null)
    public static void eliminarElemento(Element e) {
        if (e != null && e.getParentNode() != null) {
            e.getParentNode().removeChild(e);
        }
    }

    // Obtener texto de una subetiqueta <nombreTag>hijo</nombreTag>
    public static String getTextoHijo(Element padre, String nombreTag) {
        NodeList nl = padre.getElementsByTagName(nombreTag);
        if (nl.getLength() == 0) return null;
        return nl.item(0).getTextContent();
    }
}
