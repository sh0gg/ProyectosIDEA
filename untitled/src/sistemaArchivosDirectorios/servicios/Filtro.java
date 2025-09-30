package sistemaArchivosDirectorios.servicios;

import java.io.File;
import java.io.FilenameFilter;

public class Filtro implements FilenameFilter {

    private final String ext;

    public Filtro(String ext) {
        String e = ext.startsWith(".")
                ? ext.substring(1)
                : ext;
        this.ext = e.toLowerCase();
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(ext);
    }


}
