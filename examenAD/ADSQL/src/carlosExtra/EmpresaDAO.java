
package carlosExtra;

import clases.Departamento;
import clases.Empregado;
import clases.Proxecto;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class EmpresaDAO implements AutoCloseable {

    Connection conexion;
    private TipoSGBD tipoSGBD;

    @Override
    public void close() {
        cerrar();
    }


    public EmpresaDAO(TipoSGBD tipoSGBD, String baseDatos, String usuario, String contrasinal) {
        try {
            this.conexion = GestorConexiones.getConnection(tipoSGBD, baseDatos, usuario, contrasinal);
            this.tipoSGBD = tipoSGBD;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void cerrar() {
        GestorConexiones.cerrarConexion(conexion);
    }


    public List<Departamento> mostrarDepartamentos() {
        List<Departamento> departamentos = new ArrayList<>();

        String sqlConsulta = "SELECT * FROM Departamento ORDER BY NumDepartamento";

        try (ResultSet rs = GestorConexiones.ejecutarConsulta(conexion, sqlConsulta)) {
            while (rs.next()) {
                Departamento d = new Departamento(
                        rs.getInt("NumDepartamento"),
                        rs.getString("NomeDepartamento"),
                        rs.getString("NSSDirector")
                );
                departamentos.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar departamentos", e);
        }
        return departamentos;
    }


    public boolean existeProxecto(String nomeProxecto) {

        String sql = "SELECT COUNT(*) FROM Proxecto WHERE NomeProxecto = ?";
        try (ResultSet rs = GestorConexiones.ejecutarConsulta(conexion, sql, nomeProxecto)) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al comprobar proyecto", e);
        }
        return false;
    }


    public void insertarProyecto(Proyecto p) {
        p.setNumProxecto(obtenerUltimoNumProxecto() + 1);
        String sqlInsert = "INSERT INTO PROXECTO" +
                " (NumProxecto, NomeProxecto, Lugar, NumDepartControla)"
                + "VALUES (?, ?, ?, ?)";
        try {
            GestorConexiones.ejecutarSentencia(conexion, sqlInsert,
                    p.getNumProxecto(),
                    p.getNomeProxecto(),
                    p.getLugar(),
                    p.getNumDepartControla());
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar proyecto", e);
        }

    }


    private int obtenerUltimoNumProxecto() {
        String sql = "SELECT MAX(NumProxecto) FROM Proxecto";
        try (ResultSet rs = GestorConexiones.ejecutarConsulta(conexion, sql)) {
            if (rs.next()) {
                return rs.getInt(1); // devuelve el máximo encontrado
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }


    public boolean comprobarExisteTabla(String nombreTabla) {
        try {
            return GestorConexiones.tablaExiste(conexion, nombreTabla);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void crearTablaFamiliar_SQLite() {
        String sqlCreate = """
                CREATE TABLE FAMILIAR
                """;
    }

    public void crearTablaFamiliar() {
        String familiar = """
                NSS_em
                """;
    }


    //Ejer1. Visualizar o número e nome dos departamentos que teñen proxectos asignados

    public void mostrarDepartamentosConproyectosAsignados() {
        List<Departamento> departamentosWProyectos = new ArrayList<>();
        String sqlConsulta = "SELECT DISTINCT numDepartamento, nomeDepartamento FROM Departamento D INNER JOIN PROXECTO P ON D.numDepartamento = P.numDepartControla";
        try (ResultSet rs = GestorConexiones.ejecutarConsulta(conexion, sqlConsulta)) {
            while (rs.next()) {
                Departamento d = new Departamento(
                        rs.getInt("NumDepartamento"),
                        rs.getString("NomeDepartamento")
                );
                departamentosWProyectos.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Departamento d : departamentosWProyectos) {
            System.out.println("(NUM DEPART:" + d.getNumDepartamento() + ") " + d.getNomeDepartamento());
        }
    }


    // Ejer2. Visualizar o número e nome, nombre e apelidos do director dos departamentos que teñen proxectos
    //asignados.

    public void directoresConProyectosAsignados() {
        List<Empregado> directoresWProyectos = new ArrayList<>();
        List<Departamento> departamentosWProyectos = new ArrayList<>();

        String sqlConsulta = "SELECT DISTINCT numDepartamento, nomeDepartamento, Nome, Apelido1, Apelido2 " +
                "FROM Departamento D INNER JOIN  PROXECTO P ON D.numDepartamento = P.numDepartControla INNER JOIN Empregado E" +
                "    ON D.NSSDirector = E.NSS GROUP BY\n" +
                "    D.NumDepartamento, D.NomeDepartamento,\n" +
                "    E.Nome, E.Apelido1, E.Apelido2;";
        try (ResultSet rs = GestorConexiones.ejecutarConsulta(conexion, sqlConsulta)) {
            while (rs.next()) {
                String nome = rs.getString("Nome");
                String apelido1 = rs.getString("Apelido1");
                String apelido2 = rs.getString("Apelido2");
                String nomeDepartamento = rs.getString("NomeDepartamento");
                int numDepartamento = rs.getInt("numDepartamento");

                Empregado e = new Empregado(nome, apelido1, apelido2);
                Departamento d = new Departamento(numDepartamento, nomeDepartamento);

                directoresWProyectos.add(e);
                departamentosWProyectos.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Empregado dir : directoresWProyectos) {
            for (Departamento dep : departamentosWProyectos) {
                System.out.println("(" + dep.getNumDepartamento() + ")" + dep.getNomeDepartamento() + "-" + dir.getNomeEmpregado() + " " + dir.getApelido1() + " " + dir.getApelido2());
            }
        }

    }


    // Ejer3.
    // Visualizar o NSS, o nome e apelidos e a idade dos empregados da empresa.

    public void mostrarDatosEmpregado() {
        List<Empregado> empregados = new ArrayList<>();
        String sqlConsulta = "SELECT Nome, Apelido1, Apelido2, DataNacemento FROM Empregado order by Nome";
        try (ResultSet rs = GestorConexiones.ejecutarConsulta(conexion, sqlConsulta)) {
            while (rs.next()) {
                String nome = rs.getString("Nome");
                String apelido1 = rs.getString("Apelido1");
                String apelido2 = rs.getString("Apelido2");
                LocalDate dataNacemento = rs.getDate("DataNacemento").toLocalDate();

                // calcular edad
                LocalDate hoy = LocalDate.now();
                Period periodo = Period.between(dataNacemento, hoy);
                int edad = periodo.getYears();

                Empregado e = new Empregado(nome, apelido1, apelido2, edad);

                empregados.add(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Empregado e : empregados) {
            System.out.println(e);
        }

    }

    //Ejer4
    public void filtrarEmpleadosPorDepartamento(String nombreDepartamento) {
        List<Empregado> empregadosPorDepartamento = new ArrayList<>();

        String sqlConsulta = "SELECT \n" +
                "    e.NSS," +
                "    e.Nome,\n" +
                "    e.Apelido1,\n" +
                "    e.Apelido2,\n" +
                "    CASE \n" +
                "        WHEN ef.NSS IS NOT NULL THEN 'Fixo'\n" +
                "        WHEN et.NSS IS NOT NULL THEN 'Temporal'\n" +
                "    END AS TipoEmpregado\n" +
                "FROM Empregado e\n" +
                "JOIN Departamento d\n" +
                "    ON e.NumDepartamentoPertenece = d.NumDepartamento\n" +
                "LEFT JOIN empregadofixo ef\n" +
                "    ON e.NSS = ef.NSS\n" +
                "LEFT JOIN empregadotemporal et\n" +
                "    ON e.NSS = et.NSS\n" +
                "WHERE d.NomeDepartamento = ?";

        try (ResultSet rs = GestorConexiones.ejecutarConsulta(conexion, sqlConsulta, nombreDepartamento)) {
            while (rs.next()) {
                Empregado e = new Empregado(
                        rs.getString("NSS"),
                        rs.getString("Nome"),
                        rs.getString("Apelido1"),
                        rs.getString("Apelido2"),
                        rs.getString("TipoEmpregado")
                );
                empregadosPorDepartamento.add(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Empregado e : empregadosPorDepartamento) {
            System.out.println("(" + e.getTipo() + ") " + e.getNss() + ": " + e.getNomeEmpregado() + " " + e.getApelido1() + " " + e.getApelido2());
        }
    }

    //Ejer5
    public void filtrarEmpleadosFijosPorNombreProyectoYLocalidad(String nombreProyecto, String localidadEmpleado) {

        List<Empregado> empleadosFijos = new ArrayList<>();

        String sqlConsulta = "SELECT e.NSS, e.Nome, e.Apelido1, e.Apelido2, ef.Salario, d.NomeDepartamento FROM EMPREGADO e JOIN EMPREGADOFIXO ef ON e.NSS = ef.NSS " +
                "JOIN DEPARTAMENTO d ON e.NumDepartamentoPertenece = d.NumDepartamento JOIN EMPREGADO_PROXECTO ep ON e.NSS = ep.NSSEmpregado " +
                "JOIN PROXECTO p ON ep.NumProxecto = p.NumProxecto WHERE e.Localidade = ? AND p.NomeProxecto = ?";


        try (ResultSet rs = GestorConexiones.ejecutarConsulta(conexion, sqlConsulta, localidadEmpleado, nombreProyecto)) {
            while (rs.next()) {
                Empregado e = new Empregado(
                        rs.getString("NSS"),
                        rs.getString("Nome"),
                        rs.getString("Apelido1"),
                        rs.getString("Apelido2"),
                        rs.getDouble("Salario")
                );
                empleadosFijos.add(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Empregado e : empleadosFijos) {
            System.out.println(e.getNss() + ": " + e.getNomeEmpregado() + " " + e.getApelido1() + " " + e.getApelido2());
        }
    }

    // EJER 6

    public void mostrarNumEmpregadosPorDepartamento() {

        String sql = """
                SELECT d.NomeDepartamento,
                       COUNT(ef.NSS) AS NumFixos,
                       COUNT(et.NSS) AS NumTemporais
                FROM DEPARTAMENTO d
                INNER JOIN EMPREGADO e
                       ON e.NumDepartamentoPertenece = d.NumDepartamento
                LEFT JOIN EMPREGADOFIXO ef
                       ON e.NSS = ef.NSS
                LEFT JOIN EMPREGADOTEMPORAL et
                       ON e.NSS = et.NSS
                GROUP BY d.NomeDepartamento
                """;

        try (ResultSet rs = GestorConexiones.ejecutarConsulta(conexion, sql)) {
            while (rs.next()) {
                String departamento = rs.getString("NomeDepartamento");
                int fixos = rs.getInt("NumFixos");
                int temporais = rs.getInt("NumTemporais");

                System.out.println(departamento.toUpperCase() + ":");
                System.out.println("Empleados fijos (" + fixos + ")");
                System.out.println("Empleados temporales (" + temporais + ")");
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // EJER 7
    public void mostrarDepartamentosConMasDeNEmpleados(int n) {
        String sql = "SELECT d.NumDepartamento,\n" +
                "       d.NomeDepartamento,\n" +
                "       COUNT(e.NSS) AS TotalEmpregados\n" +
                "FROM DEPARTAMENTO d\n" +
                "JOIN EMPREGADO e\n" +
                "     ON e.NumDepartamentoPertenece = d.NumDepartamento\n" +
                "GROUP BY d.NumDepartamento, d.NomeDepartamento\n" +
                "HAVING COUNT(e.NSS) > ?";

        System.out.println("Departamentos con + de " + n + " empleados: ");
        try (ResultSet rs = GestorConexiones.ejecutarConsulta(conexion, sql, n)) {
            while (rs.next()) {
                int numDepartamento = rs.getInt("NumDepartamento");
                String nomeDepartamento = rs.getString("NomeDepartamento");

                System.out.println("[" + numDepartamento + "]" + nomeDepartamento);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // EJER 8
    public void mostrarEmpleadosQueCobranMasDe(double salario) {
        String sql = "SELECT DISTINCT e.NSS, e.Nome, e.Apelido1, e.Apelido2 FROM Empregado e LEFT JOIN empregadofixo ef ON e.NSS = ef.NSS WHERE ef.Salario > ?";

        System.out.println("Empleados que cobran + de " + salario + ": ");
        try (ResultSet rs = GestorConexiones.ejecutarConsulta(conexion, sql, salario)) {
            while (rs.next()) {
                String NSS = rs.getString("NSS");
                String nome = rs.getString("Nome");
                String apelido1 = rs.getString("Apelido1");
                String apelido2 = rs.getString("Apelido2");

                System.out.println("[" + NSS + "] " + nome + " " + apelido1 + " " + (apelido2 == null ? " " : apelido2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // EJER 9
    public void mostrarEmpleadosQueCobranMasDeCadaDepart() {
        String sql = "SELECT e.NSS,\n" +
                "       e.Nome,\n" +
                "       e.Apelido1,\n" +
                "       e.Apelido2,\n" +
                "       ef.Salario,\n" +
                "       d.NomeDepartamento\n" +
                "FROM EMPREGADO e\n" +
                "JOIN EMPREGADOFIXO ef\n" +
                "     ON e.NSS = ef.NSS\n" +
                "JOIN DEPARTAMENTO d\n" +
                "     ON e.NumDepartamentoPertenece = d.NumDepartamento\n" +
                "WHERE ef.Salario = (\n" +
                "    SELECT MAX(ef2.Salario)\n" +
                "    FROM EMPREGADO e2\n" +
                "    JOIN EMPREGADOFIXO ef2\n" +
                "         ON e2.NSS = ef2.NSS\n" +
                "    WHERE e2.NumDepartamentoPertenece = e.NumDepartamentoPertenece\n" +
                ")\n" +
                "ORDER BY d.NomeDepartamento;\n";

        try {
            Statement st = conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs = st.executeQuery(sql);
            rs.afterLast();
            String ultimoDepartamento = "";
            while (rs.previous()) {
                String departamento = rs.getString("NomeDepartamento");
                String nss = rs.getString("NSS");
                String nomeEmpleado = rs.getString("Nome");
                String apelido1 = rs.getString("Apelido1");
                String apelido2 = rs.getString("Apelido2");

                if (!departamento.equals(ultimoDepartamento)) {
                    System.out.println(departamento.toUpperCase() + ":");
                    ultimoDepartamento = departamento;
                }
                System.out.println("[" + nss + "] " + nomeEmpleado + " " + apelido1 + " " + (apelido2 == null ? " " : apelido2));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //EJER 10
    public void mostrarDeptQueManejanMasProyectos() {
        String sql = "SELECT d.NumDepartamento,\n" +
                "       d.NomeDepartamento,\n" +
                "       COUNT(p.NumProxecto) AS NumProxectos\n" +
                "FROM DEPARTAMENTO d\n" +
                "JOIN PROXECTO p\n" +
                "     ON d.NumDepartamento = p.NumDepartControla\n" +
                "GROUP BY d.NumDepartamento, d.NomeDepartamento\n" +
                "HAVING COUNT(p.NumProxecto) = (\n" +
                "    SELECT MAX(NumP)\n" +
                "    FROM (\n" +
                "        SELECT COUNT(*) AS NumP\n" +
                "        FROM PROXECTO\n" +
                "        GROUP BY NumDepartControla\n" +
                "    ) AS sub\n" +
                ");";

        try (ResultSet rs = GestorConexiones.ejecutarConsulta(conexion, sql)) {
            while (rs.next()) {
                int numProyectos = rs.getInt("NumProxectos");
                int numDepartamento = rs.getInt("NumDepartamento");
                String nomeDepartamento = rs.getString("NomeDepartamento");

                System.out.println("[" + numDepartamento + "] " + nomeDepartamento + "(" + numProyectos + ")");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}