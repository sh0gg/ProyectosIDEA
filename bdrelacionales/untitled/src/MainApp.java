import es.ieschandomonte.ud3.empresa25.logica.DDLService;
import es.ieschandomonte.ud3.empresa25.logica.EmpresaService;
import es.ieschandomonte.ud3.empresa25.modelo.Departamento;
import es.ieschandomonte.ud3.empresa25.modelo.Proxecto;
import es.ieschandomonte.ud3.empresa25.persistencia.GestorConexiones;
import es.ieschandomonte.ud3.empresa25.persistencia.TipoBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        EmpresaService empresaService = new EmpresaService();
        DDLService ddlService = new DDLService();

        for (TipoBD tipo : TipoBD.values()) {
            System.out.println("\n================== PROBANDO " + tipo + " ==================");

            try (Connection conn = GestorConexiones.abrirConexion(tipo)) {

                // Ejercicio 5: metadatos de la conexión
                GestorConexiones.mostrarMetadatosConexion(conn, tipo);

                // Ejercicio 6: operaciones sencillas
                List<Departamento> departamentos = empresaService.listarDepartamentos(conn);
                System.out.println("Departamentos existentes:");
                for (Departamento d : departamentos) {
                    System.out.println(" - " + d);
                }

                // Inserción de un proyecto de prueba
                Proxecto p = new Proxecto(999, "PROXECTO_DE_PROBA_" + tipo, "Vigo", 1);
                try {
                    empresaService.insertarProxecto(conn, p);
                } catch (SQLException ex) {
                    System.out.println("No se pudo insertar proyecto: " + ex.getMessage());
                }

                // Ejercicio 7: DDL desde Java
                ddlService.crearTablaFamiliares(conn, tipo);
                ddlService.crearTaboasVehiculos(conn, tipo);

            } catch (SQLException e) {
                System.err.println("Error trabajando con " + tipo + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
