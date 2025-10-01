package sistemaArchivosDirectorios.servicios;

import java.util.Comparator;

public class CompararNombres implements Comparator<String> {
    private final boolean descendente;

    public CompararNombres(boolean descendente) {
        this.descendente = descendente;
    }

    @Override
    public int compare(String a, String b) {
        int cmp = a.compareToIgnoreCase(b);
        return descendente ? -cmp : cmp;
    }
}
