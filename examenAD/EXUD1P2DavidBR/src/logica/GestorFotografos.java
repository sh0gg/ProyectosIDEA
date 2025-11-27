package logica;

import static persistencia.FileUtilsRandomAccess.*;

public class GestorFotografos {

    public static void visualizarRegistros(String ruta) {
        int totalRegistros = contarRegistros(ruta);

        for  (int i = 0; i < totalRegistros; i++) {
            System.out.println("-----------------------------------------------------");
            leerRegistro(ruta, i);
            System.out.println("-----------------------------------------------------");
        }
    }


}
