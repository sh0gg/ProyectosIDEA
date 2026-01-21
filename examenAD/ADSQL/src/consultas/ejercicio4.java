package consultas;

import clases.Empregado;
import logica.GestorEmpresa;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ejercicio4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Lista de empregados de un departamento pasado por parametro

        TipoSGBD tipo = TipoSGBD.SQLSERVER;
        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            GestorEmpresa gestor = new GestorEmpresa();

            System.out.println("¿Qué departamento quiere visualizar?");
            String nomDep = sc.nextLine().trim().toUpperCase();

            List<Empregado> empleados = gestor.getEmpregadosDeDepartamento(conn, nomDep);
            for (Empregado e : empleados) System.out.println(e);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al obtener el listado de empleados de un departamento");
        }
    }
}
