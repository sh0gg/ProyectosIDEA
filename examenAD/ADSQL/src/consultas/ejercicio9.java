package consultas;

import persistencia.EmpresaService;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;

public class ejercicio9 {
    //  Visualizar os datos dos empregados fixos que máis gañan en cada departamento, ordenados por nome do
    //  departemento. Utilizar unha sentenza con scroll para navegar polo ResultSet desde o último elemento ata o
    //  primeiro.

    public static void main(String[] args) {

        TipoSGBD tipo = TipoSGBD.MYSQL;

        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "root", "abc123.,")) {

            EmpresaService emServ = new EmpresaService();

            emServ.listarEmpregadosFixosSalarioMaxScroll(conn);

        } catch (SQLException e) {
            System.out.println("Error al obtener el listado de empleados.");
        }
    }
}

