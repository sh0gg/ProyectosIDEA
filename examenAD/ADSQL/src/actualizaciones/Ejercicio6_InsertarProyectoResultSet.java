package actualizaciones;

import clases.Proxecto;
import logica.GestorEmpresa;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.util.Scanner;

public class Ejercicio6_InsertarProyectoResultSet {
    public static void main(String[] args) {

        // EJERCICIO 6:
        // Insertar proyecto usando ResultSet dinámico (scroll_sensitive + updatable)
        // - comprobar que no existe num ni nombre
        // - comprobar que existe departamento controlador

        Scanner sc = new Scanner(System.in);
        TipoSGBD tipo = TipoSGBD.SQLSERVER;

        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            GestorEmpresa gestor = new GestorEmpresa();

            Proxecto p = new Proxecto();

            System.out.println("NumProxecto:");
            p.setNumProxecto(Integer.parseInt(sc.nextLine().trim()));

            System.out.println("NomeProxecto:");
            p.setNomeProxecto(sc.nextLine().trim());

            System.out.println("Lugar:");
            p.setLugar(sc.nextLine().trim());

            System.out.println("NumDepartControla:");
            p.setNumDepartControla(Integer.parseInt(sc.nextLine().trim()));

            gestor.insertarProyectoResultSet(conn, p);

        } catch (Exception e) {
            System.out.println("Error en Ejercicio 6: " + e.getMessage());
        }
    }
}
