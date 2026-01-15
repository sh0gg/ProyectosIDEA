package persistencia;

import clases.Departamento;
import clases.Empregado;
import clases.Proxecto;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class EmpresaService {

    public static List<Departamento> listarDepartamentos(Connection conn) throws SQLException {
        List<Departamento> lista = new ArrayList<>();

        String sql = "SELECT NumDepartamento, NomeDepartamento, NSSDirector FROM DEPARTAMENTO";

        return getListaDepartamentos(conn, lista, sql);
    }

    private static List<Departamento> getListaDepartamentos(Connection conn, List<Departamento> lista, String sql) throws SQLException {
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Departamento d = new Departamento(rs.getInt("NumDepartamento"), rs.getString("NomeDepartamento"), rs.getString("NSSDirector"));
                lista.add(d);
            }
        }

        return lista;
    }

    public static void listaDepartamentosMaxProyectos(Connection conn) throws SQLException {

        String sql = """
                        SELECT NumDepartamento, NomeDepartamento, COUNT(*) AS NumProyectos
                        FROM departamento
                                 JOIN proxecto ON departamento.NumDepartamento = proxecto.NumDepartControla
                        GROUP BY NumDepartamento, NomeDepartamento
                        HAVING NumProyectos =
                               (SELECT MAX(NumP)
                                FROM (SELECT COUNT(*) AS NumP FROM PROXECTO GROUP BY NumDepartControla) AS sub)
                        ORDER BY NumDepartamento DESC
                """;

        // EH ATENTO, EN SQL SERVER TIENES QUE HACER HAVING COUNT(proxecto.NumProxecto) =

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println("[" + rs.getInt("NumDepartamento") + " - " + rs.getString("NomeDepartamento") + "] Numero de Proyectos: " + rs.getInt("NumProyectos"));
                }
            }
        }
    }

    private static void existeProxecto(Connection conn, Proxecto p) throws SQLException {
        String sqlCheck = "SELECT COUNT(*) FROM PROXECTO WHERE NomeProxecto = ?";

        try (PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
            psCheck.setString(1, p.getNomeProxecto());
            try (ResultSet rs = psCheck.executeQuery()) {
                rs.next();
                int count = rs.getInt(1);
                if (count > 0) {
                    throw new SQLException("Ya existe un proyecto con ese nombre: " + p.getNomeProxecto());
                }
            }
        }
    }

    public static boolean existeDepartamento(Connection conn, String nomeDep) throws SQLException {
        String sqlCheck = "SELECT COUNT(*) FROM DEPARTAMENTO WHERE NomeDepartamento = ?";

        try (PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
            psCheck.setString(1, nomeDep);

            try (ResultSet rs = psCheck.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    public static boolean existeProxecto(Connection conn, String nomeProx) throws SQLException {
        String sqlCheck = "SELECT COUNT(*) FROM PROXECTO WHERE NomeProxecto = ?";

        try (PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
            psCheck.setString(1, nomeProx);

            try (ResultSet rs = psCheck.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    public static boolean existeLocalidade(Connection conn, String nomeLoc) throws SQLException {
        String sqlCheck = "SELECT COUNT(*) FROM lugar WHERE Lugar like ?";

        try (PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
            psCheck.setString(1, nomeLoc);

            try (ResultSet rs = psCheck.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    public static int numEmpleadosFixDep(Connection conn, int numDep) throws SQLException {
        String sql = """
        SELECT COUNT(*)
        FROM EMPREGADOFIXO EF
        JOIN EMPREGADO E ON EF.NSS = E.NSS
        WHERE E.NumDepartamentoPertenece = ?
    """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numDep);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }


    public static ArrayList<String> listaDepartamentosNumEmpleados(Connection conn, int numEmpleados) throws SQLException, ClassNotFoundException {
        ArrayList<String> lista = new ArrayList<>();
        String sql = """
                Select departamento.NumDepartamento, departamento.NomeDepartamento, count(*) AS NumEmpleados
                From departamento
                INNER JOIN empregado on departamento.NumDepartamento = empregado.NumDepartamentoPertenece
                group by departamento.NumDepartamento
                having NumEmpleados > ?
                ORDER BY NumEmpleados DESC
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numEmpleados);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add("[" + rs.getString("NumDepartamento") + "] " + rs.getString("NomeDepartamento") + " - " + rs.getInt(3) + " empleados.");
                }
            }
        }

        return lista;
    }

    public static ArrayList<String> listaDepartamentos(Connection conn) throws SQLException, ClassNotFoundException {
        ArrayList<String> lista = new ArrayList<>();
        String sql = "SELECT NumDepartamento, NomeDepartamento FROM DEPARTAMENTO";

        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(rs.getString("NomeDepartamento"));
            }
        }

        return lista;
    }

    public static ArrayList<Proxecto> listaProxectos(Connection conn) throws SQLException, ClassNotFoundException {
        ArrayList<Proxecto> lista = new ArrayList<>();
        String sql = "SELECT NumProxecto, NomeProxecto, Lugar, NumDepartControla FROM proxecto";

        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int nProxecto = rs.getInt("NumProxecto");
                String nomProxecto =  rs.getString("NomeProxecto");
                String lugar = rs.getString("Lugar");
                int numDepartControla = rs.getInt("NumDepartControla");

                lista.add(new Proxecto(nProxecto, nomProxecto, lugar, numDepartControla));
            }
        }

        return lista;
    }

    public static void listarEmpregadosDepartamentos(Connection conn, String nomDep) throws SQLException {

        String sql = """
                SELECT e.NSS, e.Nome, e.Apelido1, e.Apelido2,
                       et.NSS AS NSS_TEMPORAL,
                       ef.NSS AS NSS_FIXO
                FROM EMPREGADO e
                JOIN DEPARTAMENTO d
                     ON e.NumDepartamentoPertenece = d.NumDepartamento
                LEFT JOIN EMPREGADOTEMPORAL et
                     ON e.NSS = et.NSS
                LEFT JOIN EMPREGADOFIXO ef
                     ON e.NSS = ef.NSS
                WHERE d.NomeDepartamento = ?
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nomDep);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    String nss = rs.getString("NSS");
                    String nome = rs.getString("Nome");
                    String apelido1 = rs.getString("Apelido1");
                    String apelido2 = rs.getString("Apelido2");

                    if (rs.getString("NSS_TEMPORAL") != null) {
                        System.out.println("TEMPORAL -> " + nss + " - " + nome + " " + apelido1 + " " + apelido2);
                    } else if (rs.getString("NSS_FIXO") != null) {
                        System.out.println("FIXO -> " + nss + " - " + nome + " " + apelido1 + " " + apelido2);
                    }
                }
            }
        }
    }

    public static void listarEmpregadosFixosLocalidade(Connection conn, String nomProx, String nomLoc) {

        String sql = """
                        SELECT DISTINCT e.NSS, Nome, Apelido1, Apelido2, Salario, NomeDepartamento
                        FROM EMPREGADO e
                          JOIN empregado_proxecto ep ON e.NSS = ep.NSSEmpregado
                          JOIN bdempresa25.proxecto p on ep.NumProxecto = p.NumProxecto
                          JOIN DEPARTAMENTO d ON e.NumDepartamentoPertenece = d.NumDepartamento
                          LEFT JOIN EMPREGADOFIXO ef
                              ON e.NSS = ef.NSS
                        WHERE NomeProxecto LIKE ? AND E.Localidade LIKE ? AND ef.NSS IS NOT NULL
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nomProx);
            ps.setString(2, nomLoc);

            printEmpregadoFixoFullDatos(ps);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printEmpregadoFixoFullDatos(PreparedStatement ps) throws SQLException {
        try (ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nss = rs.getString("e.NSS");
                String nomeCompleto = rs.getString("Nome") + " " + rs.getString("Apelido1") + " " + rs.getString("Apelido2");
                float salario = rs.getFloat("Salario");
                String nomeDepartamento = rs.getString("NomeDepartamento");

                System.out.println(nss + " - " + nomeCompleto + ". Salario: " + salario + ". Departamento: " + nomeDepartamento);
            }
        }
    }

    public void insertarProxecto(Connection conn, Proxecto p) throws SQLException {
        // 1) Comprobar que el nombre es único
        existeProxecto(conn, p);

        // 2) Insertar
        String sqlInsert = "INSERT INTO PROXECTO (NumProxecto, NomeProxecto, Lugar, NumDepartControla) " + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sqlInsert)) {
            ps.setInt(1, p.getNumProxecto());
            ps.setString(2, p.getNomeProxecto());
            ps.setString(3, p.getLugar());
            ps.setInt(4, p.getNumDepartControla());

            int filas = ps.executeUpdate();
            System.out.println("Filas insertadas en PROXECTO: " + filas);
        }
    }

    public List<Departamento> departamentosProyectosAsignados(Connection conn) throws SQLException {
        List<Departamento> lista = new ArrayList<>();

        String sql = "SELECT DISTINCT NumDepartamento, NomeDepartamento, NSSDirector FROM DEPARTAMENTO D JOIN PROXECTO P WHERE D.NumDepartamento = P.NumDepartControla";

        return getListaDepartamentos(conn, lista, sql);
    }

    public List<Empregado> directoresDepProAsignados(Connection conn, List<String> nnss) throws SQLException {
        List<Empregado> lista = new ArrayList<>();

        for (String nss : nnss) {
            String sql = "SELECT * FROM EMPREGADO WHERE NSS = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, nss);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Empregado e = new Empregado(rs.getString("nss"), rs.getString("nome"), rs.getString("apelido1"), rs.getString("apelido2"));
                        lista.add(e);
                    }
                }
            }
        }

        return lista;

    }

    public List<Empregado> listarEmpregados(Connection conn) throws SQLException {
        List<Empregado> lista = new ArrayList<>();

        String sql = "SELECT * FROM EMPREGADO";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {

                // Calculamos la edad
                Date fecha = rs.getDate("DataNacemento");
                LocalDate nacimiento = fecha.toLocalDate();
                int edad = Period.between(nacimiento, LocalDate.now()).getYears();

                Empregado e = new Empregado(rs.getString("nss"), rs.getString("nome"), rs.getString("apelido1"), rs.getString("apelido2"), edad);
                lista.add(e);
            }
        }
        return lista;
    }

    public void listarEmpregadosFixosSalario(Connection conn, int salarioMin) {
        String sql = """
                        SELECT DISTINCT e.NSS, Nome, Apelido1, Apelido2, Salario, NomeDepartamento
                        FROM EMPREGADO e
                          JOIN DEPARTAMENTO d ON e.NumDepartamentoPertenece = d.NumDepartamento
                          LEFT JOIN EMPREGADOFIXO ef
                              ON e.NSS = ef.NSS
                        WHERE Salario > ?
                        ORDER BY Salario DESC
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, salarioMin);

            printEmpregadoFixoFullDatos(ps);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void listarEmpregadosFixosSalarioMaxScroll(Connection conn) {
        String sql = """
                SELECT DISTINCT e.NSS, Nome, Apelido1, Apelido2, Salario, NomeDepartamento
                FROM EMPREGADO e
                         JOIN DEPARTAMENTO d ON e.NumDepartamentoPertenece = d.NumDepartamento
                         LEFT JOIN EMPREGADOFIXO ef
                                   ON e.NSS = ef.NSS
                WHERE Salario = (
                    SELECT MAX(ef2.Salario)
                    FROM EMPREGADO e2
                             JOIN EMPREGADOFIXO ef2
                                  ON e2.NSS = ef2.NSS
                    WHERE e2.NumDepartamentoPertenece = e.NumDepartamentoPertenece
                    )
                ORDER BY d.NomeDepartamento;
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            printEmpregadoFixoFullDatosEX(ps);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void printEmpregadoFixoFullDatosEX(PreparedStatement ps) throws SQLException {
        try (ResultSet rs = ps.executeQuery()) {
            rs.afterLast();
            while (rs.previous()) {
                String nss = rs.getString("e.NSS");
                String nomeCompleto = rs.getString("Nome") + " " + rs.getString("Apelido1") + (rs.getString("Apelido2") == null ? "" : " " + rs.getString("Apelido2"));
                float salario = rs.getFloat("Salario");
                String nomeDepartamento = rs.getString("NomeDepartamento");

                System.out.println(nss + " - " + nomeCompleto + ". Salario: " + salario + ". Departamento: " + nomeDepartamento);
            }
        }
    }

    public static int cambiarDepartamentoProyecto(Connection conn, String nomDep, String nomProx) throws SQLException {

        String sql = """
                UPDATE PROXECTO
                SET NumDepartControla = (
                            SELECT NumDepartamento
                            FROM DEPARTAMENTO
                            WHERE NomeDepartamento = ?
                            )
                WHERE NomeProxecto = ?;
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nomDep);
            ps.setString(2, nomProx);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Se actualizó correctamente el proyecto " + nomProx + ", ahora asignado a " + nomDep);
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int incrementarSalariosDepartamento(Connection conn, int incremento, int numDepartamento) throws SQLException {
        String sql = """
                SELECT EF.NSS, EF.SALARIO
                FROM EMPREGADOFIXO EF
                JOIN EMPREGADO E ON EF.NSS = E.NSS
                WHERE E.NumDepartamentoPertenece = ?
        """;

        int afectados = 0;

        try (PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {

            ps.setInt(1, numDepartamento);
            conn.setAutoCommit(false);

            try  (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int salarioActual =  rs.getInt("Salario");
                    rs.updateInt("Salario", salarioActual+incremento);
                    rs.updateRow();
                    afectados++;
                }
            }

            conn.commit();
            return afectados;
        }
    }
}
