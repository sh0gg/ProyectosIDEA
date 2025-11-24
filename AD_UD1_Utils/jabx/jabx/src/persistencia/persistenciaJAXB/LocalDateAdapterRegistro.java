package persistencia.persistenciaJAXB;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Adaptador específico para el ejercicio de Registro de personas.
 * LocalDate <-> String "d-M-yy" (ej: 12-3-99).
 */
public class LocalDateAdapterRegistro extends XmlAdapter<String, LocalDate> {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("d-M-yy");

    @Override
    public LocalDate unmarshal(String v) {
        if (v == null || v.isBlank()) {
            return null;
        }
        return LocalDate.parse(v.trim(), FORMATTER);
    }

    @Override
    public String marshal(LocalDate v) {
        if (v == null) {
            return null;
        }
        return v.format(FORMATTER);
    }
}
