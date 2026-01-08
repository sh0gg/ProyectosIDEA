package consultas;

import persistencia.EmpresaService;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ejercicio4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Lista de empregados de un departamento pasado por parametro

        TipoSGBD tipo = TipoSGBD.MYSQL;

        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "root", "abc123.,")) {

            EmpresaService emServ = new EmpresaService();

            System.out.println("¿Qué departamento quiere visualizar?");
            String nomDep = sc.nextLine().toUpperCase();

            while (!emServ.existeDepartamento(conn, nomDep)) {
                System.out.println("Departamento no existe");
                nomDep = sc.nextLine();
            }

            emServ.listarEmpregadosDepartamentos(conn, nomDep);

        } catch (SQLException e) {
            System.out.println("Error al obtener el listado de empleados de un departamento");
        }
    }
}
