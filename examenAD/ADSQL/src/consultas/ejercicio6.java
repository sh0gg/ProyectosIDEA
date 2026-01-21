package consultas;

import clases.Empregado;
import logica.GestorEmpresa;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ejercicio6 {
    public static void main(String[] args) {

        // Lista de empregados de todos los departamentos

        TipoSGBD tipo = TipoSGBD.SQLSERVER;
        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            GestorEmpresa gestor = new GestorEmpresa();

            Map<String, List<Empregado>> porDep = gestor.getEmpregadosAgrupadosPorDepartamento(conn);

            for (Map.Entry<String, List<Empregado>> entry : porDep.entrySet()) {
                System.out.println(entry.getKey());
                for (Empregado e : entry.getValue()) System.out.println(e);
                System.out.println("===================");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el listado de empleados por departamento");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
