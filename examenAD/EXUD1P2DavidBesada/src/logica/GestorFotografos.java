package logica;

import static persistencia.FileUtilsRandomAccess.*;

public class GestorFotografos {

    public void visualizarRegistros(String ruta) {
        int totalRegistros = contarRegistros(ruta);

        System.out.println("-----------------------------------------------------");
        for  (int i = 0; i < totalRegistros; i++) {
            leerRegistro(ruta, i);
            System.out.println("-----------------------------------------------------");
        }
    }


}
