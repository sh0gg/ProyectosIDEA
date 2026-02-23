/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocina22;

import POJOS.Cocinero;
import POJOS.Contactococinero;
import POJOS.Premio;
import POJOS.Receta;
import POJOS.Restaurante;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/*
 Código para el examen de HB

 nombre del alumno: Raúl Piñeiro Martínez
 DNI: 77416997D

 */
public class Operaciones {

    public Operaciones() {
    }

    public void testConnection() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            if (session.isConnected()) {
                System.out.println("Conexion realizada con exito");
                session.close();
            } else {
                System.out.println("Ha ocurrido un error posterior al inicio.");
            }

        } catch (HibernateException e) {
            System.out.println("Error de Conexion.");
            e.printStackTrace();

        }
    }

    //Apartado 1B
    public void insertarCocineroContactoPremios(Cocinero c, Contactococinero cc, Collection<Premio> premios) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction ts = null;

        Query q1 = session.createQuery("from Cocinero where APODO=?");
        q1.setString(0, c.getApodo());

        Cocinero cocinero = (Cocinero) q1.uniqueResult();

        if (cocinero == null) { //SI NO EXISTE UN COCINERO CON ESE MISMO ALIAS

            Collection<Premio> premiosC = new ArrayList();

            for (Premio premio : premios) {
                if (comprobarPremiosInsertados(premio)) {    //Si no está en ninguno lo añadimos
                    premiosC.add(premio);
                }
            }
            c.setContacto(cc);  //Guradamos en cocinero su contacto
            cc.setCocinero(c);  //Y en contacto su cocinero

            if (!premios.isEmpty()) {
                c.setPremios(premiosC); //Solo guardamos los premios si contienen algun valor
            }

            try {
                ts = session.beginTransaction();
                session.save(c);        //Guardamos el nuevo objeto cocinero
                session.save(cc);       //Guardamos el nuevo objeto contacto (Opcional?)
                ts.commit();
                System.out.println("Cocinero: " + c.getApodo() + " guardado correctamente");
            } catch (HibernateException h) {
                System.out.println("[Error]: la operacion no ha podido ser completada debido a: \n" + h.getMessage());
                if (ts != null) {
                    ts.rollback();
                }
            }

        } else {
            System.out.println("Lo sentimos, ya existe un cocinero con el apodo indicado");
        }
        session.close();
    }

    //Metodo que recorre todos los premios de todos los cocineros para comprobar
    //si este ya está añadido en alguno
    //Funciona pero algunos premios los comprueba varias veces
    public boolean comprobarPremiosInsertados(Premio premioIn) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction ts = null;

        Query query = session.createQuery("from Cocinero c inner join c.premios");

        List<Cocinero> cocineros = query.list();

        for (Cocinero cocinero : cocineros) {
            Collection<Premio> premiosC = cocinero.getPremios();

            for (Premio premioCocinero : premiosC) {

                if (premioCocinero.getAnho() == premioIn.getAnho() && premioCocinero.getPremio().equals(premioIn.getPremio())) {

                    System.out.println("El premio: " + premioIn.getPremio() + " "
                            + premioIn.getAnho() + " ya está registrado para un cocinero");
                    return false;
                }

            }

        }

        session.close();
        System.out.println("El premio: " + premioIn.getPremio() + " " + premioIn.getAnho() + " será añadido al cocinero");
        return true;

    }

    //METODO EN EL EJERCICIO 2
    public void mostrarPublicacionesEditorial() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("select p.editorial, count(*) from Publicacion p "
                + "group by p.editorial order by 2 desc");

        List<Object[]> listaPublicaciones = query.list();

        //Cabecera
        System.out.printf("%-20s %-20s\n", "Nombre Editorial", "N publicaciones");
        System.out.println("------------------------------------");

        //Mostrar campos
        for (Object[] campos : listaPublicaciones) {
            if (campos != null) {
                System.out.printf("%-20s %-20s\n", campos[0], campos[1]);
            }
        }

        session.close();
    }

    //METODO EN EL EJERCICIO 3
    public void borrarReceta(int codReceta) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction ts = null;

        Receta receta = (Receta) session.get(Receta.class, codReceta);

        if (receta != null) {       //SI EXISTE ALGUNA RECETA CON ESE CODIGO

            System.out.println("LA RECETA: " + receta.getNome());   //Muestro receta
            System.out.println("AUTOR :" //Muestro cocinero
                    + receta.getCocinero().getApellido1() + " "
                    + receta.getCocinero().getApellido2() + ", "
                    + receta.getCocinero().getNombre() + " alias "
                    + receta.getCocinero().getApodo());

            try {
                ts = session.beginTransaction();
                session.delete(receta);            //BORRO RECETA
                ts.commit();
                System.out.println("Se ha borrado la receta"); //SI TODO BIEN
            } catch (HibernateException h) {
                System.out.println("[Error]: la operacion no ha podido ser completada debido a: \n" + h.getMessage());
                if (ts != null) {
                    ts.rollback();
                }

            }
        } else {
            System.out.println("No existe ninguna receta en el sistema con el codigo indicado");
        }

    }

    public void mostrarDueño(String nomRestaurante) {

        boolean encontrado = false;
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction ts = null;

        String consulta = "from Cocinero";
        Query query = session.createQuery(consulta);

        List<Cocinero> cocineros = query.list();
        List<Restaurante> restaurantes;
        
        for (Cocinero cocinero : cocineros) {                   //Recorro todos los cocineros

            restaurantes= cocinero.getRestaurantes();
            
            for (Restaurante restaurante : restaurantes) {      //Para cada uno recorro sus restaurantes
                
                if (restaurante.getNombrerestaurante().equalsIgnoreCase(nomRestaurante)){   //Si el indicado se encuentra entre ellos:
                    
                    System.out.println("El dueño del restaurante " + nomRestaurante + " es:");
                    System.out.println( cocinero.getApellido1() + " "
                                      + cocinero.getApellido2() + ", "
                                      + cocinero.getNombre()+ " alias "
                                      + cocinero.getApodo());
                    
                    encontrado = true;
                }
                
            }
            
        }

        if (!encontrado){   //Si no lo hemos encontrado:
            System.out.println("No hay ningun restaurante en la base de datos con ese nombre");
        }
        
        session.close();
        
    }

}
