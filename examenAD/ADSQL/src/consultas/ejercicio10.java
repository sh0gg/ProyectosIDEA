package consultas;

import clases.Departamento;
import clases.DepartamentoNumProyectos;
import logica.GestorEmpresa;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ejercicio10 {
    public static void main(String[] args) {

        // Departamentos que controlan el máximo número de proyectos

        TipoSGBD tipo = TipoSGBD.SQLSERVER;
        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            GestorEmpresa gestor = new GestorEmpresa();
            List<DepartamentoNumProyectos> deps = gestor.getDepartamentosConMaxNumProyectos(conn);

            for (DepartamentoNumProyectos d : deps) System.out.println(d);

        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de departamentos con máximo número de proyectos");
        }
    }
}
