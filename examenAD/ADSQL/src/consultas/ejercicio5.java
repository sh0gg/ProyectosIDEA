package consultas;

import persistencia.EmpresaService;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ejercicio5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Dado o nome dun proxecto e unha localidade, visualizar os empregados fixos que traballan nese proxecto e
        // que pertencen á localidade indicada. Mostrar: NSS, nome completo, salario e nome do departamento no que
        // traballan.

        TipoSGBD tipo = TipoSGBD.MYSQL;

        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "root", "abc123.,")) {

            System.out.println("¿Qué proyecto quiere visualizar?");
            String nomProx = sc.nextLine().toUpperCase();

            while (!EmpresaService.existeProxecto(conn, nomProx)) {
                System.out.println("El proyecto no existe");
                nomProx = sc.nextLine();
            }

            System.out.println("¿Qué localidad quiere visualizar?");
            String nomLoc = sc.nextLine().toUpperCase();

            while (!EmpresaService.existeLocalidade(conn, nomLoc)) {
                System.out.println("Esa localidad no existe");
                nomLoc = sc.nextLine();
            }

            EmpresaService.listarEmpregadosFixosLocalidade(conn, nomProx, nomLoc);

        } catch (SQLException e) {
            System.out.println("Error al obtener los datos");
        }
    }
}