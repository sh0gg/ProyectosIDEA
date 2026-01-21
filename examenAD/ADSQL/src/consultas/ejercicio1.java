package consultas;

import clases.Departamento;
import logica.GestorEmpresa;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ejercicio1 {
    public static void main(String[] args) {

        // Lista de departamentos con proyectos asignados

        TipoSGBD tipo = TipoSGBD.SQLSERVER; // en el examen
        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            GestorEmpresa gestor = new GestorEmpresa();
            List<Departamento> deps = gestor.getDepartamentosConProyectosAsignados(conn);

            for (Departamento d : deps) System.out.println(d);

        } catch (SQLException e) {
            System.out.println("Error al obtener los datos de departamentos asignados");
        }
    }
}
