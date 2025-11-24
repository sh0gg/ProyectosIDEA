package persistencia.persistenciaDOM;

import clases.ClasesCorredor.*;
import org.w3c.dom.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de Corredores: trabaja directamente con el DOM (Document),
 * pero ya sabe cómo construir Fondista/Velocista y Puntuacion.
 */
public class CorredoresXML {

    private Document doc;
    private Element raiz; // <corredores>

    public CorredoresXML(Document doc) {
        this.doc = doc;
        this.raiz = doc.getDocumentElement();
    }

    // ============================
    // LECTURA: XML -> Objetos Java
    // ============================
    public List<Corredor> cargarCorredores() {

        List<Corredor> lista = new ArrayList<>();

        // Leemos <velocista> y <fondista>
        NodeList nodosVel = doc.getElementsByTagName("velocista");
        for (int i = 0; i < nodosVel.getLength(); i++) {
            Element e = (Element) nodosVel.item(i);
            lista.add(leerVelocista(e));
        }

        NodeList nodosFon = doc.getElementsByTagName("fondista");
        for (int i = 0; i < nodosFon.getLength(); i++) {
            Element e = (Element) nodosFon.item(i);
            lista.add(leerFondista(e));
        }

        return lista;
    }

    private Velocista leerVelocista(Element e) {
        String codigo = e.getAttribute("codigo");
        int dorsal = Integer.parseInt(e.getAttribute("dorsal"));
        String equipo = e.getAttribute("equipo");

        String nombre = XMLDOMUtils.getTextoHijo(e, "nombre");
        LocalDate fecha = LocalDate.parse(
                XMLDOMUtils.getTextoHijo(e, "fecha_nacimiento"));
        float velMedia = Float.parseFloat(
                XMLDOMUtils.getTextoHijo(e, "velocidad_media"));

        Velocista v = new Velocista(codigo, dorsal, nombre, fecha, equipo, velMedia);
        leerHistorial(e, v);
        return v;
    }

    private Fondista leerFondista(Element e) {
        String codigo = e.getAttribute("codigo");
        int dorsal = Integer.parseInt(e.getAttribute("dorsal"));
        String equipo = e.getAttribute("equipo");

        String nombre = XMLDOMUtils.getTextoHijo(e, "nombre");
        LocalDate fecha = LocalDate.parse(
                XMLDOMUtils.getTextoHijo(e, "fecha_nacimiento"));
        float distMax = Float.parseFloat(
                XMLDOMUtils.getTextoHijo(e, "distancia_max"));

        Fondista f = new Fondista(codigo, dorsal, nombre, fecha, equipo, distMax);
        leerHistorial(e, f);
        return f;
    }

    // Leer <historial><puntuacion anio="..">valor</puntuacion>...</historial>
    private void leerHistorial(Element corredorElem, Corredor c) {
        NodeList historiales = corredorElem.getElementsByTagName("historial");
        if (historiales.getLength() == 0) return;

        Element eHist = (Element) historiales.item(0);
        NodeList puntuaciones = eHist.getElementsByTagName("puntuacion");

        for (int i = 0; i < puntuaciones.getLength(); i++) {
            Element eP = (Element) puntuaciones.item(i);
            int anio = Integer.parseInt(eP.getAttribute("anio"));
            float puntos = Float.parseFloat(eP.getTextContent());
            c.addOrUpdatePuntuacion(new Puntuacion(anio, puntos));
        }
    }

    // ============================
    // BÚSQUEDAS
    // ============================
    public Element buscarCorredorPorCodigo(String codigo) {
        // buscamos en cualquier tipo de corredor
        Element e = XMLDOMUtils.buscarPorAtributo(doc, "velocista",
                "codigo", codigo);
        if (e == null) {
            e = XMLDOMUtils.buscarPorAtributo(doc, "fondista",
                    "codigo", codigo);
        }
        return e;
    }

    public Element buscarCorredorPorDorsal(int dorsal) {
        String dorsalStr = String.valueOf(dorsal);

        // Podríamos recorrer: aquí lo hago “a mano” para que se vea claro
        NodeList todos = raiz.getChildNodes();
        for (int i = 0; i < todos.getLength(); i++) {
            Node n = todos.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) n;
                if (dorsalStr.equals(e.getAttribute("dorsal"))) {
                    return e;
                }
            }
        }
        return null;
    }

    // Convertir un Element corredor en objeto Corredor (para capa lógica)
    public Corredor mapearElementoACorredor(Element e) {
        if (e == null) return null;
        if ("velocista".equals(e.getTagName())) {
            return leerVelocista(e);
        } else if ("fondista".equals(e.getTagName())) {
            return leerFondista(e);
        }
        return null;
    }

    // ============================
    // INSERCIÓN / BORRADO / PUNTUACIONES
    // ============================

    // Obtener siguiente dorsal (max + 1)
    public int obtenerSiguienteDorsal() {
        int max = 0;
        NodeList hijos = raiz.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            Node n = hijos.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) n;
                String dorsalStr = e.getAttribute("dorsal");
                if (!dorsalStr.isEmpty()) {
                    int d = Integer.parseInt(dorsalStr);
                    if (d > max) max = d;
                }
            }
        }
        return max + 1;
    }

    // Insertar un nuevo corredor en el DOM (al final)
    public void insertarCorredor(Corredor c) {
        Element nuevo;

        if (c instanceof Velocista) {
            nuevo = doc.createElement("velocista");
            Velocista v = (Velocista) c;
            XMLDOMUtils.addElement(doc, nuevo, "nombre", v.getNombre());
            XMLDOMUtils.addElement(doc, nuevo, "fecha_nacimiento",
                    v.getFechaNacimiento().toString());
            XMLDOMUtils.addElement(doc, nuevo, "velocidad_media",
                    String.valueOf(v.getVelocidadMedia()));
        } else if (c instanceof Fondista) {
            nuevo = doc.createElement("fondista");
            Fondista f = (Fondista) c;
            XMLDOMUtils.addElement(doc, nuevo, "nombre", f.getNombre());
            XMLDOMUtils.addElement(doc, nuevo, "fecha_nacimiento",
                    f.getFechaNacimiento().toString());
            XMLDOMUtils.addElement(doc, nuevo, "distancia_max",
                    String.valueOf(f.getDistanciaMax()));
        } else {
            throw new IllegalArgumentException("Tipo de corredor desconocido");
        }

        // atributos comunes
        nuevo.setAttribute("codigo", c.getCodigo());
        nuevo.setAttribute("dorsal", String.valueOf(c.getDorsal()));
        nuevo.setAttribute("equipo", c.getEquipo());

        // historial (si tiene)
        if (!c.getHistorial().isEmpty()) {
            Element eHist = doc.createElement("historial");
            for (Puntuacion p : c.getHistorial()) {
                Element eP = doc.createElement("puntuacion");
                eP.setAttribute("anio", String.valueOf(p.getAnio()));
                eP.setTextContent(String.valueOf(p.getPuntos()));
                eHist.appendChild(eP);
            }
            nuevo.appendChild(eHist);
        }

        raiz.appendChild(nuevo);
    }

    // Eliminar corredor por código. Devuelve true si lo encontró.
    public boolean eliminarCorredorPorCodigo(String codigo) {
        Element e = buscarCorredorPorCodigo(codigo);
        if (e == null) return false;
        XMLDOMUtils.eliminarElemento(e);
        return true;
    }

    // Añadir o modificar puntuación de un corredor
    public enum ResultadoPuntuacion { ANADIDA, MODIFICADA, NO_ENCONTRADO }

    public ResultadoPuntuacion addOrUpdatePuntuacion(
            String codigo, Puntuacion nueva) {

        Element corredorElem = buscarCorredorPorCodigo(codigo);
        if (corredorElem == null) return ResultadoPuntuacion.NO_ENCONTRADO;

        // buscamos <historial>, si no existe lo creamos
        Element eHist;
        NodeList historiales = corredorElem.getElementsByTagName("historial");
        if (historiales.getLength() == 0) {
            eHist = doc.createElement("historial");
            corredorElem.appendChild(eHist);
        } else {
            eHist = (Element) historiales.item(0);
        }

        // buscar si ya existe puntuacion para ese año
        NodeList puntuaciones = eHist.getElementsByTagName("puntuacion");
        for (int i = 0; i < puntuaciones.getLength(); i++) {
            Element eP = (Element) puntuaciones.item(i);
            int anio = Integer.parseInt(eP.getAttribute("anio"));
            if (anio == nueva.getAnio()) {
                eP.setTextContent(String.valueOf(nueva.getPuntos()));
                return ResultadoPuntuacion.MODIFICADA;
            }
        }

        // si no existe, la añadimos
        Element ePunt = doc.createElement("puntuacion");
        ePunt.setAttribute("anio", String.valueOf(nueva.getAnio()));
        ePunt.setTextContent(String.valueOf(nueva.getPuntos()));
        eHist.appendChild(ePunt);
        return ResultadoPuntuacion.ANADIDA;
    }

    // Eliminar una puntuación concreta (codigo + año)
    public boolean eliminarPuntuacionPorAnio(String codigo, int anio) {
        Element corredorElem = buscarCorredorPorCodigo(codigo);
        if (corredorElem == null) return false;

        NodeList historiales = corredorElem.getElementsByTagName("historial");
        if (historiales.getLength() == 0) return false;

        Element eHist = (Element) historiales.item(0);
        NodeList puntuaciones = eHist.getElementsByTagName("puntuacion");

        for (int i = 0; i < puntuaciones.getLength(); i++) {
            Element eP = (Element) puntuaciones.item(i);
            int anioElem = Integer.parseInt(eP.getAttribute("anio"));
            if (anioElem == anio) {
                eHist.removeChild(eP);
                return true;
            }
        }
        return false;
    }

    public Document getDocument() {
        return doc;
    }
}
