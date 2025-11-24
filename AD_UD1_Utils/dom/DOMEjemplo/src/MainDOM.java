import clases.ClasesCorredor.Fondista;
import clases.ClasesCorredor.Puntuacion;
import logica.ConsultasXPath;
import logica.GestorCorredores;
import persistencia.persistenciaDOM.XMLDOMUtils;

import java.time.LocalDate;

import org.w3c.dom.Document;

public class MainDOM {
    public static void main(String[] args) throws Exception {

        String ruta = "test/corredores.xml";
        GestorCorredores gestor = new GestorCorredores(ruta);

        // Listar todos
        gestor.listarTodos();

        // Mostrar uno
        gestor.mostrarCorredorPorCodigo("C01");

        // Insertar fondista de ejemplo
        Fondista f = new Fondista("C99", 0, "Nuevo Fondista",
                LocalDate.of(2001, 1, 1), "E1", 10000.0f);
        gestor.insertarCorredor(f);

        // Actualizar puntuación
        gestor.actualizarPuntuacion("C01", new Puntuacion(2024, 90.5f));

        // Guardar cambios
        gestor.guardar();


        // XPath

        Document doc = XMLDOMUtils.cargarDocumentoXML(ruta);

        ConsultasXPath cx = new ConsultasXPath(doc);

        cx.listarTodos();
        cx.buscarPorCodigo("C02");
        cx.contarCorredores();
        cx.obtenerNombrePorCodigo("C03");
        cx.listarPorEquipo("E1");
    }
}
