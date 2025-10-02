package serializacionObjetos;

// Objetivo: Desarrollar una aplicación para almacenar y gestionar la información de los corredores que participan en
// las competiciones en España. Esta información debe ser almacenada en un fichero binario secuencial llamado
// corredores.dat, y debe incluir datos personales, la relación con el equipo y un historial de puntuaciones por año.
// Los corredores se clasifican en dos tipos: Fondistas y Velocistas, los cuales tienen atributos específicos. A
// continuación, se detallan los requisitos de la información a almacenar:

import java.sql.Date;
import java.time.LocalDate;

public class ejercicio3 {

    public static void main(String[] args) {
        Fondista pepe = new Fondista(9801,"Pepe", LocalDate.of(2000,9, 1), 8, 42.4F);
        pepe.addPuntuacion(Puntuacion.of(2002,20.3F));
    }

}
