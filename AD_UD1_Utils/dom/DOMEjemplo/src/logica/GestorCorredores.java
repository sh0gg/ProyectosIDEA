package logica;

import clases.ClasesCorredor.*;
import persistencia.persistenciaDOM.CorredoresXML;
import persistencia.persistenciaDOM.XMLDOMUtils;

import org.w3c.dom.Document;

import java.util.List;

/**
 * Capa de lógica/servicio.
 * - Orquesta las operaciones.
 * - Da feedback por consola.
 * - Decide cuándo guardar el DOM en disco.
 */
public class GestorCorredores {

    private String rutaXML;
    private Document doc;
    private CorredoresXML dao;

    public GestorCorredores(String rutaXML) throws Exception {
        this.rutaXML = rutaXML;
        cargarDocumento();
    }

    // Carga el XML en memoria y crea el DAO
    private void cargarDocumento() throws Exception {
        this.doc = XMLDOMUtils.cargarDocumentoXML(rutaXML);
        this.dao = new CorredoresXML(doc);
    }

    // Guardar cambios en disco
    public void guardar() {
        try {
            XMLDOMUtils.guardarDocumentoXML(doc, rutaXML);
            System.out.println("Documento guardado correctamente en " + rutaXML);
        } catch (Exception e) {
            System.out.println("ERROR al guardar el documento: " + e.getMessage());
        }
    }

    // ========= OPERACIONES DE LECTURA =========

    public void listarTodos() {
        List<Corredor> lista = dao.cargarCorredores();
        if (lista.isEmpty()) {
            System.out.println("No hay corredores.");
            return;
        }
        for (Corredor c : lista) {
            System.out.println(c);
        }
    }

    public void mostrarCorredorPorCodigo(String codigo) {
        Corredor c = dao.mapearElementoACorredor(
                dao.buscarCorredorPorCodigo(codigo));

        if (c == null) {
            System.out.println("No se encontró corredor con código " + codigo);
        } else {
            System.out.println("Corredor encontrado: " + c);
        }
    }

    public void mostrarCorredorPorDorsal(int dorsal) {
        Corredor c = dao.mapearElementoACorredor(
                dao.buscarCorredorPorDorsal(dorsal));

        if (c == null) {
            System.out.println("No se encontró corredor con dorsal " + dorsal);
        } else {
            System.out.println("Corredor encontrado: " + c);
        }
    }

    // ========= OPERACIONES DE MODIFICACIÓN =========

    // Inserta corredor aplicando la norma de dorsal autoincremental
    public void insertarCorredor(Corredor c) {
        int nuevoDorsal = dao.obtenerSiguienteDorsal();
        c.setDorsal(nuevoDorsal);

        dao.insertarCorredor(c);
        System.out.println("Corredor insertado con dorsal " + nuevoDorsal);
    }

    public void eliminarCorredorPorCodigo(String codigo) {
        boolean ok = dao.eliminarCorredorPorCodigo(codigo);
        if (ok) {
            System.out.println("Corredor con código " + codigo + " eliminado.");
        } else {
            System.out.println("No se encontró corredor con código " + codigo);
        }
    }

    public void actualizarPuntuacion(String codigo, Puntuacion p) {
        CorredoresXML.ResultadoPuntuacion res =
                dao.addOrUpdatePuntuacion(codigo, p);

        switch (res) {
            case NO_ENCONTRADO ->
                    System.out.println("No se encontró corredor con código " + codigo);
            case ANADIDA ->
                    System.out.println("Puntuación AÑADIDA para el año " + p.getAnio());
            case MODIFICADA ->
                    System.out.println("Puntuación MODIFICADA para el año " + p.getAnio());
        }
    }

    public void borrarPuntuacion(String codigo, int anio) {
        boolean ok = dao.eliminarPuntuacionPorAnio(codigo, anio);
        if (ok) {
            System.out.println("Puntuación del año " + anio +
                    " eliminada para el corredor " + codigo);
        } else {
            System.out.println("No se encontró esa puntuación.");
        }
    }
}
