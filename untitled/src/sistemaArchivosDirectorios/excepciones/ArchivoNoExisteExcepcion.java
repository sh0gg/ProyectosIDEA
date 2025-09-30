package sistemaArchivosDirectorios.excepciones;

public class ArchivoNoExisteExcepcion extends Exception {
    public ArchivoNoExisteExcepcion(String mensaje) {
        super(mensaje);
    }
}