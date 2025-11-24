package persistencia.persistenciaJAXB;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Adaptador para mapear:
 *   List<String> <-> "Estudios Trabajo Deporte"
 * en un único elemento XML.
 */
public class CategoriasAdapter extends XmlAdapter<String, List<String>> {

    @Override
    public List<String> unmarshal(String v) {
        if (v == null || v.isBlank()) {
            return Collections.emptyList();
        }
        // Separar por espacios
        return Arrays.asList(v.trim().split("\\s+"));
    }

    @Override
    public String marshal(List<String> v) {
        if (v == null || v.isEmpty()) {
            return "";
        }
        // Unir categorías separadas por espacios
        return String.join(" ", v);
    }
}
