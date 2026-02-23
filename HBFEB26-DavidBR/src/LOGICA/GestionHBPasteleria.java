package LOGICA;

import PERSISTENCIA.HBPasteleriaDAO;
import POJOS.Pastelero;
import POJOS.Tecnica;

// Autor: David Besada Ramilo (53612286E)

public class GestionHBPasteleria {

    public static void comprobarConexion() {
        int resultado = HBPasteleriaDAO.conectarHibernateDAO();

        if (resultado == 0) {
            System.out.println("Conexión correcta");

        } else {
            System.out.println("Error de conexión ");

        }
    }

    public static void addUpdateTecnica(String id, String nombreT, String nivelT) {
        Pastelero p = getPastelero(id);
        Tecnica t = null;
        int resultado = -1;
        if (p != null) {
            t = new Tecnica(nombreT, nivelT);
            resultado = HBPasteleriaDAO.addUpdateTecnicaPastelero(p, t);
            if (resultado == 0) {
                System.out.println("Se actualizó el nivel de la tecnica " + t.getTecnica() + " del pastelero " + p.getAlias());
            } else if (resultado == 1) {
                System.out.println("El pastelero " + p.getAlias() + " ahora posee la tecnica " + t.getTecnica() + " de un nivel " + t.getNivel().toLowerCase());
            } else {
                System.out.println("Algo ha fallado COMPRUEBA EL CODIGO"); // esta salida es para mi
            }
        }
        if (resultado == -1) {
            System.out.println("El pastelero no existe, no se le puede añadir una tecnica!");
        }
    }

    public static Pastelero getPastelero(String id) {
        Pastelero p = HBPasteleriaDAO.getPasteleroId(id);
        if (p == null) {
            System.out.println("El pastelero " + id + " no existe");
            return null;
        }
        return p;
    }

    public static String getPasteleroPasteleria(String nombrePasteleria) {
        String alias = HBPasteleriaDAO.getPasteleroPasteleria(nombrePasteleria);
        if (alias.equals("errPastelero")) {
            System.out.println("No he conseguido encontrar el dueño de " + nombrePasteleria);
            return null;
        } else if (alias.equals("errPasteleria")) {
            System.out.println("La pasteleria " + nombrePasteleria + " no existe");
            return null;
        }
        System.out.println("Actualmente, el dueño de " + nombrePasteleria + " es " + alias);
        return alias;
    }

    public static void cambiarDueno(String alias, String nombrePasteleria) {
        String duenoActual = getPasteleroPasteleria(nombrePasteleria);
        if (duenoActual == null) {
            System.out.println("Cancelando operacion");
            return;
        } else if (duenoActual.equals(alias)) {
            System.out.println("No se puede traspasar un local a su mismo dueño!");
            return;
        }
        // System.out.println("Actualmente, " + nombrePasteleria + " es de " +  duenoActual); YA SE DA ESTA INFO AL EJECUTAR getPasteleroPasteleria
        System.out.println("Pasará a ser de " + alias);
        String nuevoDueno = HBPasteleriaDAO.cambiarDueno(alias, nombrePasteleria);
        if (nuevoDueno.equals(alias)) {
            System.out.println("Traspaso completado! " + nombrePasteleria + " es ahora de " + nuevoDueno);
        } else if (nuevoDueno.equals("errPastelero")) {
            System.out.println("El alias del nuevo dueño no existe!");
        } else {
            System.out.println("Algo ha fallado COMPRUEBA EL CODIGO"); // esta salida es para mi
        }
    }

    public static void borrarProducto(int id) {
        int resultado = HBPasteleriaDAO.borrarProducto(id);

        if (resultado == 0) {
            System.out.println("Se ha borrado correctamente el producto con el id " + id);
        } else if (resultado == -1) {
            System.out.println("No se ha encontrado el producto con el id " + id + ". Cancelando operacion!");
        } else {
            System.out.println("Algo ha fallado COMPRUEBA EL CODIGO"); // esta salida es para mi
        }
    }
}