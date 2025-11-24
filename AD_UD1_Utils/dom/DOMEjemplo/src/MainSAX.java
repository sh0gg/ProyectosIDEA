import logica.GestorCorredoresSAX;

public class MainSAX {
    public static void main(String[] args) {
        GestorCorredoresSAX g = new GestorCorredoresSAX("test/corredores.xml");

        g.listarTodos();
        g.listarPorEquipo("E1");
    }
}
