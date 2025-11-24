package logica;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import persistencia.persistenciaDOM.XPathUtils;

public class ConsultasXPath {

    private Document doc;

    public ConsultasXPath(Document doc) {
        this.doc = doc;
    }

    // ===============================
    // 1. Listar todos los corredores
    // ===============================
    public void listarTodos() throws Exception {
        NodeList nodos = XPathUtils.queryNodes(doc, "//corredores/*");
        System.out.println("CORREDORES EN EL XML:");

        for (int i = 0; i < nodos.getLength(); i++) {
            System.out.println("- " + nodos.item(i).getNodeName()
                    + " (codigo=" + nodos.item(i).getAttributes().getNamedItem("codigo").getNodeValue() + ")");
        }
        System.out.println();
    }

    // ===============================
    // 2. Buscar por código
    // ===============================
    public void buscarPorCodigo(String codigo) throws Exception {
        String expr = "//corredores/*[@codigo='" + codigo + "']";
        NodeList nodos = XPathUtils.queryNodes(doc, expr);

        if (nodos.getLength() == 0) {
            System.out.println("No se encontró corredor con código " + codigo);
            return;
        }

        var nodo = nodos.item(0);
        System.out.println("Encontrado: " + nodo.getNodeName()
                + " - codigo=" + codigo);
    }

    // ===============================
    // 3. Contar corredores
    // ===============================
    public void contarCorredores() throws Exception {
        double total = XPathUtils.queryNumber(doc, "count(//corredores/*)");
        System.out.println("Total de corredores: " + (int) total);
    }

    // ===============================
    // 4. Obtener nombre por código
    // ===============================
    public void obtenerNombrePorCodigo(String codigo) throws Exception {
        String expr = "string(//corredores/*[@codigo='" + codigo + "']/nombre)";
        String nombre = XPathUtils.queryString(doc, expr);

        if (nombre == null || nombre.isEmpty()) {
            System.out.println("No existe ese corredor.");
        } else {
            System.out.println("Nombre del corredor: " + nombre);
        }
    }

    // ===============================
    // 5. Corredores de un equipo concreto
    // ===============================
    public void listarPorEquipo(String equipo) throws Exception {
        String expr = "//corredores/*[@equipo='" + equipo + "']";
        NodeList nodos = XPathUtils.queryNodes(doc, expr);

        System.out.println("Corredores del equipo " + equipo + ":");
        for (int i = 0; i < nodos.getLength(); i++) {
            System.out.println("- " + nodos.item(i).getAttributes().getNamedItem("codigo").getNodeValue());
        }
        System.out.println();
    }
}
