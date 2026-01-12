package consultas;

import persistencia.EmpresaService;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ejercicio6 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Lista de empregados de todos los departamentos

        TipoSGBD tipo = TipoSGBD.MYSQL;

        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "root", "abc123.,")) {

            List<String> listaDepartamentos = EmpresaService.listaDepartamentos(conn);
            for (String nomeDep :  listaDepartamentos) {
                System.out.println(nomeDep);
                EmpresaService.listarEmpregadosDepartamentos(conn, nomeDep);
                System.out.println("===================");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el listado de empleados de un departamento");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
