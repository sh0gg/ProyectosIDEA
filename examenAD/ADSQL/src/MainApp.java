import clases.Proxecto;
import logica.GestorEmpresa;
import persistencia.EmpresaService;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;

public class MainApp {

    public static void main(String[] args) {

        TipoSGBD tipo = TipoSGBD.SQLITE;

//        try (Connection conn = GestorConexiones.getConnection(
//                tipo,
//                "BDEMPRESA25",
//                "sa",
//                "abc123."
//        )) {

        try (Connection conn = GestorConexiones.getConnection(
                tipo, "D:\\dbesarami\\IDEAProjects\\examenAD\\ADSQL\\src\\sqlite\\EMPRESA25.db"
        )) {

            EmpresaService empresaService = new EmpresaService();
            GestorEmpresa ddlService = new GestorEmpresa();

            System.out.println("Conectado correctamente a " + tipo);

            // --- Operaciones ---
            System.out.println("Departamentos:");
            empresaService.listarDepartamentos(conn)
                    .forEach(System.out::println);

            // Insertar proyecto
            Proxecto p = new Proxecto(999, "PROBA", "Vigo", 1);
            empresaService.insertarProxecto(conn, p);

            // Crear tablas nuevas
                ddlService.crearTablaFamiliares(conn, tipo);
                ddlService.crearTaboasVehiculos(conn, tipo);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
