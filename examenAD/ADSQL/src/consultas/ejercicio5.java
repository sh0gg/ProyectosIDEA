package consultas;

import clases.Empregado;
import logica.GestorEmpresa;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ejercicio5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Dado o nome dun proxecto e unha localidade,
        // visualizar empregados fixos nese proxecto e desa localidade.

        TipoSGBD tipo = TipoSGBD.SQLSERVER;
        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            GestorEmpresa gestor = new GestorEmpresa();

            System.out.println("¿Qué proyecto quiere visualizar?");
            String nomProx = sc.nextLine().trim().toUpperCase();

            System.out.println("¿Qué localidad quiere visualizar?");
            String nomLoc = sc.nextLine().trim().toUpperCase();

            List<Empregado> res = gestor.getEmpregadosFixosProxectoLocalidade(conn, nomProx, nomLoc);
            for (Empregado e : res) System.out.println(e);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al obtener los datos");
        }
    }
}
