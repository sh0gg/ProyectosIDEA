package persistencia.persistenciaSAX;

import clases.ClasesCorredor.Corredor;

import java.util.List;

public class CorredoresSAX {

    private String ruta;

    public CorredoresSAX(String ruta) {
        this.ruta = ruta;
    }

    public List<Corredor> listarTodos(XMLSAXUtils.TipoValidacion validacion) throws Exception {
        CorredoresSAXHandler handler = new CorredoresSAXHandler();
        XMLSAXUtils.procesarDocumento(ruta, handler, validacion);
        return handler.getCorredores();
    }

    // Ejemplo adicional: filtrar por equipo
    public List<Corredor> listarPorEquipo(String equipo) throws Exception {
        CorredoresFiltroEquipoHandler handler =
                new CorredoresFiltroEquipoHandler(equipo);
        XMLSAXUtils.procesarDocumento(ruta, handler, XMLSAXUtils.TipoValidacion.NO_VALIDAR);
        return handler.getCorredoresFiltrados();
    }
}
