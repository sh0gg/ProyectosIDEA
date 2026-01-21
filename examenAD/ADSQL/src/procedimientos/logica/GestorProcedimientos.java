package procedimientos.logica;

import procedimientos.persistencia.EmpresaProcedimientosDAO;
import procedimientos.persistencia.InstaladorProcedimientosSQLServer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GestorProcedimientos {

    // Para que puedas llamar al "instalador" desde un main y olvidarte
    public void instalar(Connection conn) {
        try {
            InstaladorProcedimientosSQLServer.instalarTodo(conn);
            System.out.println("Procedimientos y función instalados correctamente en SQL Server.");
        } catch (SQLException e) {
            System.out.println("ERROR instalando procedimientos/función: " + e.getMessage());
        }
    }

    public void cambioDomicilio(Connection conn, String nss, String rua, int numero, String piso,
                                String codPostal, String localidade) {
        try {
            int filas = EmpresaProcedimientosDAO.prCambioDomicilio(conn, nss, rua, numero, piso, codPostal, localidade);
            if (filas > 0) System.out.println("Domicilio actualizado. Filas afectadas: " + filas);
            else System.out.println("No se actualizó nada (¿NSS no existe?).");
        } catch (SQLException e) {
            System.out.println("ERROR (pr_CambioDomicilio): " + e.getMessage());
        }
    }

    public void mostrarDatosProxecto(Connection conn, int numProx) {
        try {
            EmpresaProcedimientosDAO.DatosProxecto dp = EmpresaProcedimientosDAO.prDatosProxectos(conn, numProx);

            if (dp == null) {
                System.out.println("No existe el proyecto con número: " + numProx);
                return;
            }

            System.out.println("Proyecto " + numProx + ":");
            System.out.println("- Nombre: " + dp.getNome());
            System.out.println("- Lugar: " + dp.getLugar());
            System.out.println("- Departamento: " + dp.getDepartamento());

        } catch (SQLException e) {
            System.out.println("ERROR (pr_DatosProxectos): " + e.getMessage());
        }
    }

    public void departamentosConMinProx(Connection conn, int n) {
        try {
            List<String> res = EmpresaProcedimientosDAO.prDepartControlaProxec(conn, n);

            boolean seleccion = res.stream().anyMatch(s -> s.startsWith("["));
            System.out.println("Operación: " + (seleccion ? "SELECCIÓN (ResultSet)" : "ACTUALIZACIÓN (UpdateCount)"));

            for (String s : res) System.out.println(s);

        } catch (SQLException e) {
            System.out.println("ERROR (pr_DepartControlaProxec): " + e.getMessage());
        }
    }

    public void numEmpregadosDepartamento(Connection conn, String nomeDep) {
        try {
            int n = EmpresaProcedimientosDAO.fnNEmpDepart(conn, nomeDep);
            System.out.println("Número de empleados en '" + nomeDep + "': " + n);
        } catch (SQLException e) {
            System.out.println("ERROR (fn_nEmpDepart): " + e.getMessage());
        }
    }
}
