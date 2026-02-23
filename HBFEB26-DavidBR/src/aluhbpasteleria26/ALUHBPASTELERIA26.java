
package aluhbpasteleria26;

import LOGICA.GestionHBPasteleria;
import POJOS.Pastelero;
import POJOS.Tecnica;

import java.time.LocalDate;

// Autor: David Besada Ramilo (53612286E)

public class ALUHBPASTELERIA26 {  

    public static void main(String[] args) {       
        GestionHBPasteleria.comprobarConexion();

        /*
        Pastelero pastelero1 = new Pastelero("ABCD", "Elias", "Martinez Martinez", "Eli", LocalDate.of(1992,2,23), 'M');

        // caso pastelero no existe
        GestionHBPasteleria.addUpdateTecnica(pastelero1.getCodigo(), "Glaseado","Alto");

        // caso actualizar tecnica
        GestionHBPasteleria.addUpdateTecnica("P001", "Glaseado","Alto");

        // caso añadir tecnica TODO !!!!! (descomentar en la correcion)
        // GestionHBPasteleria.addUpdateTecnica("P005", "Glaseado","Alto");

        // Cambiar dueño funciona TODO !!!!! (descomentar en la correcion)
        //GestionHBPasteleria.cambiarDueno("Seo","BakedCake");

        // Cambiar dueño, no existe pasteleria
        GestionHBPasteleria.cambiarDueno("Sergi", "QUEEEEE");

        // Cambiar dueño, nuevo dueño NO EXISTE
        GestionHBPasteleria.cambiarDueno("PEPE", "BakedCake");

        // Cambiar dueño, ya es el dueño TODO !!!!! (descomentar en la correcion)
        //GestionHBPasteleria.cambiarDueno("Seo", "BakedCake");
        */

        // Eliminar producto (falta eliminacion en pastelerias: default-cascade="all"?
        GestionHBPasteleria.borrarProducto(13);

        // Eliminar producto (ya no existe)
        GestionHBPasteleria.borrarProducto(13);

        // Mostrar certificaciones


    }

    
}
