package consultas;

import persistencia.EmpresaService;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ejercicio7 {

//    Visualizar os departamentos (número e nome) que teñen máis de N empregados. O valor N será introducido
//    como parámetro.

    public static void main(String[] args) {
        int numEmpleados = 0;
        Scanner sc = new Scanner(System.in);

        TipoSGBD tipo = TipoSGBD.MYSQL;

        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "root", "abc123.,")) {

            System.out.println("Ver departamentos con más de X empleados. Introduce un numero:");
            numEmpleados = sc.nextInt();
            List<String> listaDepartamentos = EmpresaService.listaDepartamentosNumEmpleados(conn, numEmpleados);
            for (String departamento :  listaDepartamentos) {
                System.out.println(departamento);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de departamentos que superan los " + numEmpleados + " empleados.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//    """
//    SELECT d.NumDepartamento, d.NomeDepartamento, COUNT(e.NSS) AS NumEmpregados
//    FROM DEPARTAMENTO d
//    LEFT JOIN EMPREGADO e
//        ON e.NumDepartamentoPertenece = d.NumDepartamento
//    GROUP BY d.NumDepartamento, d.NomeDepartamento
//    HAVING COUNT(e.NSS) > ?
//    ORDER BY NumEmpregados DESC
//    """
}
