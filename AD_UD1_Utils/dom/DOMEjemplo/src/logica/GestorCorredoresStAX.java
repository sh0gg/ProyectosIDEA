package logica;

import clases.ClasesCorredor.Corredor;
import persistencia.persistenciaStAXCursor.*;
import persistencia.persistenciaStAXEventos.*;

import java.util.List;

public class GestorCorredoresStAX {

    private String rutaXML;

    public GestorCorredoresStAX(String rutaXML) {
        this.rutaXML = rutaXML;
    }

    public void listarCursor() throws Exception {
        CorredoresStAXCursor dao = new CorredoresStAXCursor();
        List<Corredor> lista = dao.cargarTodosCorredoresCursor(
                rutaXML,
                XMLStAXUtilsCursor.TipoValidacion.NO_VALIDAR
        );
        lista.forEach(System.out::println);
    }

    public void listarEventos() throws Exception {
        CorredoresStAXEventos dao = new CorredoresStAXEventos();
        List<Corredor> lista = dao.cargarTodosCorredoresEventos(
                rutaXML,
                XMLStAXUtilsEventos.TipoValidacion.NO_VALIDAR
        );
        lista.forEach(System.out::println);
    }

    public void listarCursorPorEquipo(String eq) throws Exception {
        CorredoresStAXCursor dao = new CorredoresStAXCursor();
        dao.leerCorredoresPorEquipo(
                rutaXML,
                eq,
                XMLStAXUtilsCursor.TipoValidacion.NO_VALIDAR
        ).forEach(System.out::println);
    }

    public void listarEventosPorEquipo(String eq) throws Exception {
        CorredoresStAXEventos dao = new CorredoresStAXEventos();
        dao.leerCorredoresPorEquipo(
                rutaXML,
                eq,
                XMLStAXUtilsEventos.TipoValidacion.NO_VALIDAR
        ).forEach(System.out::println);
    }
}
