package consultas;

import clases.Departamento;
import persistencia.EmpresaService;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ejercicio1 {
    public static void main(String[] args) {

        // Lista de departamentos con proyectos asignados

        TipoSGBD tipo = TipoSGBD.MYSQL;

        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "root", "abc123.,")) {

            EmpresaService emServ = new EmpresaService();

            List<Departamento> dAsignados = emServ.departamentosProyectosAsignados(conn);

            for (Departamento d :  dAsignados) {
                System.out.println(d);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}