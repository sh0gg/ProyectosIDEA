package consultas;

import clases.Empregado;
import persistencia.EmpresaService;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class ejercicio3 {
    public static void main(String[] args) {
            // Lista de empregados

            TipoSGBD tipo = TipoSGBD.MYSQL;

            try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "root", "abc123.,")) {

                EmpresaService emServ = new EmpresaService();

                List<Empregado> lEmpregados = emServ.listarEmpregados(conn);

                lEmpregados.sort(Comparator.comparingInt(Empregado::getEdad));

                for (Empregado e : lEmpregados) {
                    System.out.println(e.toString());
                }

            } catch (SQLException e) {
                System.out.println("Error al obtener el listado de empleados.");
            }
        }
}
