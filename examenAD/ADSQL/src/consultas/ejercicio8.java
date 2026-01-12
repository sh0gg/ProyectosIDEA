package consultas;

import persistencia.EmpresaService;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ejercicio8 {
    // Visualizar os datos dos empregados fixos que cobran máis que un valor introducido como parámetro
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TipoSGBD tipo = TipoSGBD.MYSQL;

        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "root", "abc123.,")) {

            EmpresaService emServ = new EmpresaService();

            System.out.println("Lista empleados con salario mayor a:");
            int salarioMin = sc.nextInt();

            emServ.listarEmpregadosFixosSalario(conn, salarioMin);

        } catch (SQLException e) {
            System.out.println("Error al obtener el listado de empleados que mas cobran");
        }
    }
}
