import logica.GestorCorredoresStAX;

public class MainStAX {
    public static void main(String[] args) throws Exception {
        GestorCorredoresStAX g = new GestorCorredoresStAX("tests/corredores.xml");

        g.listarCursor();
        g.listarEventos();

        g.listarCursorPorEquipo("E1");
        g.listarEventosPorEquipo("E1");
    }
}
