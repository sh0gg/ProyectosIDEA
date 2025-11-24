package logica;

import clases.ClasesCorredor.Corredor;
import persistencia.persistenciaSAX.CorredoresSAX;
import persistencia.persistenciaSAX.XMLSAXUtils;

import java.util.List;

public class GestorCorredoresSAX {

    private CorredoresSAX corredoresSAX;

    public GestorCorredoresSAX(String ruta) {
        this.corredoresSAX = new CorredoresSAX(ruta);
    }

    public void listarTodos() {
        try {
            List<Corredor> lista =
                    corredoresSAX.listarTodos(XMLSAXUtils.TipoValidacion.NO_VALIDAR);

            lista.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Error SAX: " + e.getMessage());
        }
    }

    public void listarPorEquipo(String eq) {
        try {
            List<Corredor> lista = corredoresSAX.listarPorEquipo(eq);

            if (lista.isEmpty())
                System.out.println("No hay corredores del equipo " + eq);
            else
                lista.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Error SAX: " + e.getMessage());
        }
    }
}
