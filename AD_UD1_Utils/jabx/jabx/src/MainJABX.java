import logica.GestorCorredoresJAXB;
import logica.GestorEquiposJAXB;

public class MainJABX {

    public static void main(String[] args) {

        // Ajusta rutas según tu proyecto
        String rutaCorredores = "src/test/Corredores.xml";
        String rutaCorredoresNuevos = "src/test/CorredoresNuevos.xml";

        String rutaEquipos = "src/test/Equipos.xml";
        String rutaTxtEquiposNuevos = "src/test/EquiposNuevos.txt"; // crea este fichero con el formato del enunciado
        String rutaEquiposUpdate = "src/test/EquiposUpdateJAXB.xml";

        GestorCorredoresJAXB gc = new GestorCorredoresJAXB(rutaCorredores);
        GestorEquiposJAXB ge = new GestorEquiposJAXB();

        System.out.println("===== LISTAR CORREDORES (punto 2) =====");
        gc.listarCorredores();

        System.out.println("\n===== CREAR CorredoresNuevos.xml (punto 3) =====");
        gc.crearCorredoresNuevos(rutaCorredoresNuevos);

        System.out.println("\n===== LISTAR EQUIPOS (punto 1) =====");
        ge.listarEquipos(rutaEquipos);

        System.out.println("\n===== ACTUALIZAR EQUIPOS DESDE TXT (punto 4) =====");
        ge.actualizarEquiposDesdeTxt(rutaEquipos, rutaTxtEquiposNuevos, rutaEquiposUpdate);
    }
}
