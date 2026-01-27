
package LOGICA;

import PERSITENCIA.EmpresaHBDAO;
import POJOS.Proxecto;


public class GestorEmpresaHB {
    public static void comprobarConexion() {
        int resultado = EmpresaHBDAO.conectarHibernate();

        if (resultado == 0) {
            System.out.println("Conexión correcta");
        
        } else {
            System.out.println("Error de conexión ");
            
        }
    }
  public static void visualizarProxecto(int proxecto) {
    try {
        Proxecto p = EmpresaHBDAO.buscarProxecto(proxecto);

        if (p == null) {
            System.out.println("No existe el proyecto con código " + proxecto);
        } else {
            System.out.println("Proyecto encontrado:");
            System.out.println("Número: " + p.getNumProxecto());
            System.out.println("Nombre: " + p.getNomeProxecto());
        }

    } catch (RuntimeException e) {
        System.out.println("Error de acceso a la base de datos: " + e.getMessage());
    }
}

}

