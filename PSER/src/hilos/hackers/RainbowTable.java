package hilos.hackers;

import java.util.HashMap;
import java.util.Map;

public class RainbowTable {

    private final Map<String, RainbowEntry> tabla = new HashMap<>();

    // Devuelve la entrada asociada a la clave y la crea si no existe
    public synchronized RainbowEntry obtenerEntrada(String clave) {
        RainbowEntry entry = tabla.get(clave);

        if (entry == null) {
            entry = new RainbowEntry(clave);
            tabla.put(clave, entry);
        }

        entry.incrementarUsos();
        return entry;
    }

    // Entrada más utilizada de la tabla
    public synchronized RainbowEntry getMasUsada() {
        RainbowEntry masUsada = null;

        for (RainbowEntry e : tabla.values()) {
            if (masUsada == null || e.getUsos() > masUsada.getUsos()) {
                masUsada = e;
            }
        }

        return masUsada;
    }
}
