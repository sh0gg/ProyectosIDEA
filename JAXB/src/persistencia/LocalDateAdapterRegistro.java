package persistencia;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Adaptador específico para el ejercicio de Registro de personas.
 * Convierte entre LocalDate y cadenas con formato dd-M-yy (ej: 12-3-99).
 */
public class LocalDateAdapterRegistro extends XmlAdapter<String, LocalDate> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yy");
    @Override
    public LocalDate unmarshal(String v) {
        if (v == null || v.isEmpty()) {
            return null;
        }
        return LocalDate.parse(v, formatter);
    }
    @Override
    public String marshal(LocalDate v) {
        if (v == null) {
            return null;
        }
        return v.format(formatter);
    }
}
