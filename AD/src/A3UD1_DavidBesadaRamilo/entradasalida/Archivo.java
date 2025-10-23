package A3UD1_DavidBesadaRamilo.entradasalida;

import java.io.File;

public final class Archivo {
    private Archivo(){}
    public static void asegurarDirectorio(String ruta){
        File f = new File(ruta);
        if(!f.exists()) f.mkdirs();
    }
}
