package consultas;

import clases.Empregado;
import logica.GestorEmpresa;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class ejercicio3 {
    public static void main(String[] args) {

        // Lista de empregados (ordenados por edad)

        TipoSGBD tipo = TipoSGBD.SQLSERVER;
        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            GestorEmpresa gestor = new GestorEmpresa();
            List<Empregado> lEmpregados = gestor.getListaEmpregados(conn);

            lEmpregados.sort(Comparator.comparingInt(Empregado::getEdad));
            for (Empregado e : lEmpregados) System.out.println(e);

        } catch (SQLException e) {
            System.out.println("Error al obtener el listado de empleados.");
        }
    }
}
