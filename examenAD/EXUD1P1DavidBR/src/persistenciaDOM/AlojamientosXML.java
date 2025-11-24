package persistenciaDOM;

import org.w3c.dom.*;

import java.time.LocalDate;
import java.util.Objects;


public class AlojamientosXML {
    private Document doc;
    private Document actualizaciones;
    private Element raiz;
    private Element raizActu;

    public AlojamientosXML(Document doc, Document actualizaciones) {
        this.doc = doc;
        this.raiz = doc.getDocumentElement();
        this.actualizaciones = actualizaciones;
        this.raizActu = actualizaciones.getDocumentElement();
    }

    public void crearReserva(String numero, String codigo, String nombreUsuario, String dniUsuario, LocalDate fechaInicio) {
        Element alo = buscarAlojamientoPorNumero(numero);
        if (alo == null) {
            System.out.println("El alojamiento " + numero + " no existe.");
        } else if (alo.getAttribute("estado").equals("ocupada")) {
            System.out.println("El alojamiento " + numero + " está ocupado.");
        } else {
            Element reservas = (Element) alo.getFirstChild();
            NodeList r = reservas.getChildNodes();
            for  (int i = 0; i < r.getLength(); i++) {
                Node n = r.item(i);
                NamedNodeMap nnm = n.getAttributes();
                if (Objects.equals(nnm.getNamedItem("codigo").getNodeValue(), codigo)) {
                    System.out.println("El alojamiento " + numero + " ya tiene una reserva " + codigo + ".");
                    return;
                }
            }
            Element nuevaReserva = doc.createElement("Reserva");
            XMLDOMUtils.addAtributo(nuevaReserva, "codigo", codigo);

            Element usuario = doc.createElement("Usuario");
            XMLDOMUtils.addAtributo(usuario, "dni", dniUsuario);
            usuario.setTextContent(nombreUsuario);
            nuevaReserva.appendChild(usuario);

            Element fechaIni = doc.createElement("FechaInicio");
            fechaIni.setTextContent(fechaInicio.toString());
            nuevaReserva.appendChild(fechaIni);

            Element copiaActu = actualizaciones.createElement("Reserva");
            copiaActu.appendChild(nuevaReserva);

            reservas.appendChild(nuevaReserva);
        }
    }

    public Element buscarAlojamientoPorNumero(String numero) {
        // buscamos en cualquier tipo de alojamiento
        Element e = XMLDOMUtils.buscarPorAtributo(doc, "Habitacion",
                "numero", numero);
        if (e == null) {
            e = XMLDOMUtils.buscarPorAtributo(doc, "Suite",
                    "numero", numero);
        }
        return e;
    }
}
