import logica.GestorFotografos;

public class MainRAF {
    public static void main(String[] args) {
        String rutaDAT = "src/tests/fotografos.dat";
        GestorFotografos gf = new GestorFotografos();
        gf.visualizarRegistros(rutaDAT);
    }
}
