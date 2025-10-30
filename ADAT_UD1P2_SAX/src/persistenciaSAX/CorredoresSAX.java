package persistenciaSAX;

import clases.Corredor;

import java.util.ArrayList;
import java.util.List;

public class CorredoresSAX {

    public List<Corredor> cargarTodosCorredores(String rutaXML, TipoValidacion validacion) throws ExcepcionXML {
        CorredorSAXHandler handler = new CorredorSAXHandler();
        XMLSAXUtils.cargarDocumentosSAX(rutaXML,handler,validacion);
        return handler.getCorredores();
    };
}
