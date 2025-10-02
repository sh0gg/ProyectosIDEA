package sistemaArchivosDirectorios.excepciones;

public class DirectorioNoExisteException extends Exception {
    public DirectorioNoExisteException(String mensaje) {
        super(mensaje);
    }
}
