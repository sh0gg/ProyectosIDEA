package consultas;

import clases.Departamento;
import clases.Empregado;
import persistencia.EmpresaService;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ejercicio2 {
    public static void main(String[] args) {

        // Lista de departamentos con proyectos asignados

        TipoSGBD tipo = TipoSGBD.MYSQL;

        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "root", "abc123.,")) {

            EmpresaService emServ = new EmpresaService();

            List<Departamento> dAsignados = emServ.departamentosProyectosAsignados(conn);

            List<String> nnss = new ArrayList<>();
            for (Departamento d :  dAsignados) {
                nnss.add(d.getNssDirector());
            }

            List<Empregado> directoresAsignados = emServ.directoresDepProAsignados(conn, nnss);

            for (Empregado e : directoresAsignados) {
                System.out.println(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}