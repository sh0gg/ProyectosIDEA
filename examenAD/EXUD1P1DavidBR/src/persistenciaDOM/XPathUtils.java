package persistenciaDOM;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class XPathUtils {

    private static final XPathFactory xpf = XPathFactory.newInstance();

    // Ejecuta una expresión XPath y devuelve NodeList
    public static NodeList queryNodes(Document doc, String expression) throws Exception {
        XPath xpath = xpf.newXPath();
        XPathExpression expr = xpath.compile(expression);
        return (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
    }

    // Ejecuta una expresión XPath que devuelve un único String
    public static String queryString(Document doc, String expression) throws Exception {
        XPath xpath = xpf.newXPath();
        XPathExpression expr = xpath.compile(expression);
        return (String) expr.evaluate(doc, XPathConstants.STRING);
    }

    // Ejecuta una expresión XPath que devuelve un número
    public static double queryNumber(Document doc, String expression) throws Exception {
        XPath xpath = xpf.newXPath();
        XPathExpression expr = xpath.compile(expression);
        return (double) expr.evaluate(doc, XPathConstants.NUMBER);
    }

    // Ejecuta una expresión XPath que devuelve boolean
    public static boolean queryBoolean(Document doc, String expression) throws Exception {
        XPath xpath = xpf.newXPath();
        XPathExpression expr = xpath.compile(expression);
        return (boolean) expr.evaluate(doc, XPathConstants.BOOLEAN);
    }
}
