package examen_ud2.logica;

import examen_ud2.persistencia.ExamenDAO;
import examen_ud2.persistencia.InstaladorBD;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GestorExamen {

    // 0) Metadatos
    public void mostrarMetadatos(Connection conn) {
        try {
            var info = ExamenDAO.getInfoBD(conn);
            System.out.println("BD: " + info.producto() + " " + info.version());
            System.out.println("Driver: " + info.driver());
            System.out.println("URL: " + info.url());
            System.out.println("Usuario: " + info.usuario());
        } catch (SQLException e) {
            System.out.println("ERROR metadatos: " + e.getMessage());
        }
    }

    public void mostrarColumnas(Connection conn, String tabla) {
        try {
            System.out.println("Columnas de " + tabla + ":");
            for (String c : ExamenDAO.columnasTabla(conn, tabla)) System.out.println(" - " + c);
        } catch (SQLException e) {
            System.out.println("ERROR columnas: " + e.getMessage());
        }
    }

    // 1) Crear tablas + rutinas
    public void reinstalarTodo(Connection conn) {
        try {
            InstaladorBD.recrearTablas(conn);
            InstaladorBD.recrearRutinas(conn);
            System.out.println("OK: tablas y rutinas recreadas.");
        } catch (SQLException e) {
            System.out.println("ERROR instalando: " + e.getMessage());
        }
    }

    // 2) Insertar datos
    public void insertarDatosBase(Connection conn) {
        try {
            conn.setAutoCommit(false);

            ExamenDAO.insertDep(conn, "VENTAS", "Vigo");
            ExamenDAO.insertDep(conn, "PRODUCCION", "Pontevedra");

            // empleados batch
            List<ExamenDAO.EmpRow> emps = List.of(
                    new ExamenDAO.EmpRow("111A","Ana","Lopez", new BigDecimal("1800.00"), "FIXO", 1),
                    new ExamenDAO.EmpRow("222B","Brais","Paz",  new BigDecimal("1400.00"), "TEMP", 1),
                    new ExamenDAO.EmpRow("333C","Carmen","Souto",new BigDecimal("2100.00"), "FIXO", 2)
            );
            ExamenDAO.insertEmpBatch(conn, emps);

            ExamenDAO.insertProy(conn, "CRM", "Vigo", 1);
            ExamenDAO.insertProy(conn, "WMS", "Pontevedra", 2);

            ExamenDAO.insertAsig(conn, "111A", 1, 40);
            ExamenDAO.insertAsig(conn, "333C", 2, 55);

            conn.commit();
            System.out.println("OK: datos insertados.");
        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ignore) {}
            System.out.println("ERROR insertando (rollback): " + e.getMessage());
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ignore) {}
        }
    }

    // 3) Consultas
    public void listarEmpleadosConDep(Connection conn) {
        try {
            var lista = ExamenDAO.empleadosConDep(conn);
            for (var e : lista) {
                System.out.println(e.nss() + " - " + e.apellidos() + ", " + e.nombre() +
                        " | " + e.tipo() + " | " + e.salario() + " | " + e.depNombre());
            }
        } catch (SQLException e) {
            System.out.println("ERROR consulta: " + e.getMessage());
        }
    }

    public void resumenPorDepartamento(Connection conn) {
        try {
            var lista = ExamenDAO.resumenPorDep(conn);
            for (var d : lista) {
                System.out.println(d.depNombre() + " -> empleados=" + d.numEmp() + " | salarioMedio=" + d.salarioMedio());
            }
        } catch (SQLException e) {
            System.out.println("ERROR resumen: " + e.getMessage());
        }
    }

    // 4) Actualizaciones
    public void subirSalarioDep(Connection conn, int idDep, BigDecimal inc) {
        try {
            int filas = ExamenDAO.updateSalarioDep(conn, idDep, inc);
            System.out.println("OK: subidos salarios dep " + idDep + ". Filas=" + filas);
        } catch (SQLException e) {
            System.out.println("ERROR update: " + e.getMessage());
        }
    }

    public void borrarEmpleado(Connection conn, String nss) {
        try {
            int filas = ExamenDAO.deleteEmpleado(conn, nss);
            System.out.println("OK: borrado " + nss + ". Filas=" + filas);
        } catch (SQLException e) {
            System.out.println("ERROR delete: " + e.getMessage());
        }
    }

    // 5) ResultSet especial
    public void cambiarTipoConResultSet(Connection conn, String from, String to) {
        try {
            int n = ExamenDAO.actualizarTipoConResultSet(conn, from, to);
            System.out.println("OK: cambiados " + n + " empleados de " + from + " a " + to + " usando ResultSet UPDATABLE.");
        } catch (SQLException e) {
            System.out.println("ERROR ResultSet UPDATABLE: " + e.getMessage());
        }
    }

    public void insertarEmpleadoConResultSet(Connection conn) {
        try {
            int n = ExamenDAO.insertarEmpleadoConResultSet(conn,
                    new ExamenDAO.EmpRow("444D","Diego","Ares", new BigDecimal("1600.00"), "TEMP", 2));
            System.out.println("OK: insert ResultSet: " + n);
        } catch (SQLException e) {
            System.out.println("ERROR insert ResultSet: " + e.getMessage());
        }
    }

    // 6) Procedimientos / Funciones
    public void procCambioSalario(Connection conn, String nss, BigDecimal inc) {
        try {
            int filas = ExamenDAO.prCambioSalario(conn, nss, inc);
            System.out.println("PROC pr_CambioSalario filas=" + filas);
        } catch (SQLException e) {
            System.out.println("ERROR proc: " + e.getMessage());
        }
    }

    public void procDatosProy(Connection conn, int idProy) {
        try {
            var datos = ExamenDAO.prDatosProy(conn, idProy);
            if (datos == null) {
                System.out.println("No existe proyecto id=" + idProy);
                return;
            }
            System.out.println("PROY " + idProy + ": " + datos.nombre() + " | " + datos.lugar() + " | " + datos.dep());
        } catch (SQLException e) {
            System.out.println("ERROR proc OUT: " + e.getMessage());
        }
    }

    public void procDepConMinProyExecute(Connection conn, int minProy) {
        try {
            var res = ExamenDAO.prDepConMinProyExecute(conn, minProy);
            boolean seleccion = res.stream().anyMatch(s -> s.startsWith("["));
            System.out.println("EXECUTE() => " + (seleccion ? "SELECCION(ResultSet)" : "UPDATE(UpdateCount)"));
            res.forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("ERROR proc execute(): " + e.getMessage());
        }
    }

    public void fnNumEmpDep(Connection conn, String nombreDep) {
        try {
            int n = ExamenDAO.fnNumEmpDep(conn, nombreDep);
            System.out.println("FUNC fn_numEmpDep('" + nombreDep + "') = " + n);
        } catch (SQLException e) {
            System.out.println("ERROR funcion: " + e.getMessage());
        }
    }
}
