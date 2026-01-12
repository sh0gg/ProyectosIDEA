package consultas;

import persistencia.EmpresaService;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;

public class ejercicio10 {
    // Visualizar os departamentos (número e nome) que controlan o máximo número de proxectos

    public static void main(String[] args) {

        TipoSGBD tipo = TipoSGBD.MYSQL;

        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "root", "abc123.,")) {

            EmpresaService.listaDepartamentosMaxProyectos(conn);

        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de departamentos que controlan o máximo número de proxectos");
        }
    }
}
