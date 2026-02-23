/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cocina22;

import POJOS.Cocinero;
import POJOS.Contactococinero;
import POJOS.Premio;
import java.util.ArrayList;

/*
 Código para el examen de HB

 nombre del alumno: Raúl Piñeiro Martínez
 DNI: 77416997D

 */
public class Principal {
    public static void main(String[] args) {
        
        Operaciones op = new Operaciones();
        
        //op.testConnection();
        
        //1 DATOS Y EXEC DEL METODO:
        Cocinero cocineroBien = new Cocinero("Raul", "Piñeiro", "Martinez", 'H', "Raul");
        Contactococinero contacto = new Contactococinero("email@dominio.com", "111222333", "333222111");
        
        ArrayList <Premio> premios = new ArrayList<>();
        Premio p1 = new Premio ("CUCHILLO PLATINO", 2020); //Repetido
        Premio p2 = new Premio ("CUCHILLO PLATINO", 2025); //Nuevo
        premios.add(p1);
        premios.add(p2);

        //Con parametro correcto (la primera vez que lo intentes insertar)
        op.insertarCocineroContactoPremios(cocineroBien, contacto, premios);
        System.out.println("-----------");
        //Con parametro incorrecto (intentar reinsertarlo)
        op.insertarCocineroContactoPremios(cocineroBien, contacto, premios);
        
        //2 publicaciones por editorial
        System.out.println("\n----------METODO 2----------");
        op.mostrarPublicacionesEditorial();
        
        //3 BORRAR RECETA MOSTRANDO INFO AUTOR
        System.out.println("\n----------METODO 3----------");
        //Con parametro correcto
        op.borrarReceta(5); 
        
        System.out.println("-----------");
        
        //Con parametro incorrecto
        op.borrarReceta(300);
        
        
        
        //4 MOSTRAR DUEÑO DADO UN RESTAURANTE
        System.out.println("\n----------METODO 4----------");
        
        //Con parametro correcto
        op.mostrarDueño("Casa Xian");
        
        System.out.println("-----------");
        
        //Con parametro incorrecto
        op.mostrarDueño("Incorrecto");
        
    }
}
