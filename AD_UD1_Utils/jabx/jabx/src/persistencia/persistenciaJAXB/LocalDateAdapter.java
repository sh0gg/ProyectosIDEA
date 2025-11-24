package persistencia.persistenciaJAXB;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Adaptador JAXB para mapear LocalDate <-> String "yyyy-MM-dd".
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
