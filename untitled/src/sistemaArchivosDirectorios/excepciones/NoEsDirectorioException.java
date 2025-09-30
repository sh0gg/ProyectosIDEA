package sistemaArchivosDirectorios.excepciones;

public class NoEsDirectorioException extends Exception {
    public NoEsDirectorioException(String mensaje) {
        super(mensaje);
    }
}