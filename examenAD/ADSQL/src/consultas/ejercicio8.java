package consultas;

import clases.Empregado;
import logica.GestorEmpresa;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ejercicio8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Empleados fijos que cobran más que X

        TipoSGBD tipo = TipoSGBD.SQLSERVER;
        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            System.out.println("Lista empleados con salario mayor a:");
            int salarioMin = Integer.parseInt(sc.nextLine().trim());

            GestorEmpresa gestor = new GestorEmpresa();
            List<Empregado> res = gestor.getEmpregadosFixosConSalarioMayor(conn, salarioMin);

            for (Empregado e : res) System.out.println(e);

        } catch (SQLException e) {
            System.out.println("Error al obtener el listado de empleados que más cobran");
        }
    }
}
