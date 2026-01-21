package consultas;

import clases.Empregado;
import logica.GestorEmpresa;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ejercicio9 {
    public static void main(String[] args) {

        // Empleados fijos que más ganan en cada departamento,
        // ordenados por nombre del departamento y navegando RS desde el último al primero.

        TipoSGBD tipo = TipoSGBD.SQLSERVER;
        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            GestorEmpresa gestor = new GestorEmpresa();
            List<Empregado> res = gestor.getMaxSalarioFijosPorDepartamentoDesdeUltimo(conn);

            for (Empregado e : res) System.out.println(e);

        } catch (SQLException e) {
            System.out.println("Error al obtener el listado de empleados.");
        }
    }
}
