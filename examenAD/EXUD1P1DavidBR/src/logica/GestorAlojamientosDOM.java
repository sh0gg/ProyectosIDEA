package logica;

import clases.Alojamiento;
import org.w3c.dom.Document;
import persistenciaDOM.AlojamientosXML;
import persistenciaDOM.XMLDOMUtils;

import java.time.LocalDate;

public class GestorAlojamientosDOM {

    private String rutaXML;
    private String rutaActu;
    private Document doc;
    private Document docActu;
    private AlojamientosXML dao;

    public GestorAlojamientosDOM(String rutaXML, String rutaActu) throws Exception{
        this.rutaXML = rutaXML;
        this.rutaActu = rutaActu;
        cargarDocumento();
    }

    private void cargarDocumento() throws Exception{
        this.doc = XMLDOMUtils.cargarDocumentoXML(rutaXML);
        this.docActu = XMLDOMUtils.cargarDocumentoXML(rutaActu);
        this.dao = new AlojamientosXML(doc, docActu);
    }

    public void guardar() {
        try {
            XMLDOMUtils.guardarDocumentoXML(doc,rutaXML);
            XMLDOMUtils.guardarDocumentoXML(docActu,rutaActu);
            System.out.println("Guardado exitosamente");
        } catch (Exception e) {
            System.out.println("Error guardando documento: " + e.getMessage());
        }
    }

    public void insertarReserva(String numero, String codigo, String nombreUsuario, String dniUsuario, LocalDate fechaInicio) {
        dao.crearReserva(numero, codigo, nombreUsuario, dniUsuario, fechaInicio);
        System.out.println(nombreUsuario + "(" + dniUsuario + ") ha reservado la " + numero + " a partir del dia " + fechaInicio);
    }
}
