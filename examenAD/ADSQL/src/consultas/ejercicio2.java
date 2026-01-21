package consultas;

import clases.Empregado;
import logica.GestorEmpresa;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ejercicio2 {
    public static void main(String[] args) {

        // Directores de departamentos con proyectos asignados

        TipoSGBD tipo = TipoSGBD.SQLSERVER;
        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            GestorEmpresa gestor = new GestorEmpresa();
            List<Empregado> directores = gestor.getDirectoresDeDepsConProyectosAsignados(conn);

            for (Empregado e : directores) System.out.println(e);

        } catch (SQLException e) {
            System.out.println("Error al obtener los directores de departamentos con proyectos asignados");
        }
    }
}
