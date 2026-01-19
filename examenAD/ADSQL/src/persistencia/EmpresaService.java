package persistencia;

import clases.*;

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
    // =========================================================
    // ACTUALIZACIONES - EJ1 (Familiar)
    // =========================================================

    public static boolean existeFamiliarParaEmpleado(Connection conn, String nssEmpregado, String nssFamiliar) throws SQLException {
        String sql = "SELECT COUNT(*) FROM FAMILIAR_EMPREGADO WHERE NSS_EMPREGADO = ? AND NSS_FAMILIAR = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nssEmpregado);
            ps.setString(2, nssFamiliar);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    public static int siguienteNumFamiliar(Connection conn, String nssEmpregado) throws SQLException {
        String sql = "SELECT COALESCE(MAX(NUM_FAMILIAR), 0) + 1 FROM FAMILIAR_EMPREGADO WHERE NSS_EMPREGADO = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nssEmpregado);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    public static int insertarFamiliar(Connection conn, Familiar f) throws SQLException {
        String sql = """
        INSERT INTO FAMILIAR_EMPREGADO
        (NSS_EMPREGADO, NUM_FAMILIAR, NSS_FAMILIAR, NOME, APELIDO1, APELIDO2, DATANACEMENTO, PARENTESCO, SEXO)
        VALUES (?,?,?,?,?,?,?,?,?)
    """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getNssEmpregado());
            ps.setInt(2, f.getNumFamiliar());
            ps.setString(3, f.getNssFamiliar());
            ps.setString(4, f.getNome());
            ps.setString(5, f.getApelido1());
            ps.setString(6, f.getApelido2());

            if (f.getDataNacemento() != null) ps.setDate(7, java.sql.Date.valueOf(f.getDataNacemento()));
            else ps.setNull(7, Types.DATE);

            ps.setString(8, f.getParentesco());
            ps.setString(9, f.getSexo()); // char(1) en BD => setString("H"/"M")

            return ps.executeUpdate();
        }
    }

    // =========================================================
    // ACTUALIZACIONES - EJ2 (Vehiculos)
    // =========================================================

    public static boolean existeMatricula(Connection conn, String matricula) throws SQLException {
        String sql = "SELECT COUNT(*) FROM VEHICULO WHERE MATRICULA = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, matricula);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    /**
     * Inserta en VEHICULO y devuelve el ID autogenerado (IDENTITY en SQLServer).
     * tipoVehiculo: 'P' propio, 'R' renting
     */
    public static int insertarVehiculoCabecera(Connection conn, Vehiculo v, char tipoVehiculo) throws SQLException {
        String sql = """
        INSERT INTO VEHICULO (MATRICULA, MARCA, MODELO, TIPO_COMBUSTIBLE, TIPO_VEHICULO)
        VALUES (?,?,?,?,?)
    """;

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, v.getMatricula());
            ps.setString(2, v.getMarca());
            ps.setString(3, v.getModelo());
            ps.setString(4, v.getTipoCombustible());
            ps.setString(5, String.valueOf(tipoVehiculo));

            int filas = ps.executeUpdate();
            if (filas == 0) return -1;

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        return -1;
    }

    public static int insertarVehiculoPropio(Connection conn, int idVehiculo, VehiculoPropio vp) throws SQLException {
        String sql = "INSERT INTO VEHICULO_PROPIO (ID, DATA_COMPRA, PREZO_COMPRA) VALUES (?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVehiculo);

            if (vp.getDataCompra() != null) ps.setDate(2, java.sql.Date.valueOf(vp.getDataCompra()));
            else ps.setNull(2, Types.DATE);

            ps.setDouble(3, vp.getPrezoCompra());
            return ps.executeUpdate();
        }
    }

    public static int insertarVehiculoRenting(Connection conn, int idVehiculo, VehiculoRenting vr) throws SQLException {
        String sql = "INSERT INTO VEHICULO_RENTING (ID, DATA_INICIO, PREZO_MENSUAL, MESES_CONTRATADOS) VALUES (?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVehiculo);

            if (vr.getDataInicio() != null) ps.setDate(2, java.sql.Date.valueOf(vr.getDataInicio()));
            else ps.setNull(2, Types.DATE);

            ps.setDouble(3, vr.getPrezoMensual());
            ps.setInt(4, vr.getMesesContratados());
            return ps.executeUpdate();
        }
    }


    // =========================================================
    // ACTUALIZACIONES - EJ3
    // =========================================================

    public static Integer getNumDepartamentoPorNombre(Connection conn, String nomeDep) throws SQLException {
        String sql = "SELECT NumDepartamento FROM DEPARTAMENTO WHERE NomeDepartamento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nomeDep);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
                return null;
            }
        }
    }

    public static boolean existeProyectoPorNombre(Connection conn, String nomeProxecto) throws SQLException {
        return existeProxecto(conn, nomeProxecto);
    }

    public static int actualizarDepartamentoControlaProyecto(Connection conn, int numDep, String nomeProxecto) throws SQLException {
        String sql = "UPDATE PROXECTO SET NumDepartControla = ? WHERE NomeProxecto = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numDep);
            ps.setString(2, nomeProxecto);
            return ps.executeUpdate();
        }
    }

    // =========================================================
    // ACTUALIZACIONES - EJ4
    // =========================================================

    public static Proxecto getProyectoPorNumero(Connection conn, int numProx) throws SQLException {
        String sql = "SELECT NumProxecto, NomeProxecto, Lugar, NumDepartControla FROM PROXECTO WHERE NumProxecto = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numProx);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                Proxecto p = new Proxecto();
                p.setNumProxecto(rs.getInt("NumProxecto"));
                p.setNomeProxecto(rs.getString("NomeProxecto"));
                p.setLugar(rs.getString("Lugar"));
                p.setNumDepartControla(rs.getInt("NumDepartControla"));
                return p;
            }
        }
    }

    public static List<String> getEmpleadosAsignadosAProyecto(Connection conn, int numProx) throws SQLException {
        String sql = """
        SELECT e.NSS, e.Nome, e.Apelido1, e.Apelido2
        FROM EMPREGADO e
        JOIN EMPREGADO_PROXECTO ep ON ep.NSS = e.NSS
        WHERE ep.NumProxecto = ?
        ORDER BY e.NSS
    """;

        List<String> lista = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numProx);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String nss = rs.getString("NSS");
                    String nome = rs.getString("Nome");
                    String ap1 = rs.getString("Apelido1");
                    String ap2 = rs.getString("Apelido2");
                    String completo = nome + " " + ap1 + (ap2 == null ? "" : " " + ap2);
                    lista.add(nss + " - " + completo);
                }
            }
        }
        return lista;
    }

    public static int borrarAsignacionesProyecto(Connection conn, int numProx) throws SQLException {

        String sql = "DELETE FROM EMPREGADO_PROXECTO WHERE NumProxecto = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numProx);
            return ps.executeUpdate();
        }
    }

    public static int borrarProyecto(Connection conn, int numProx) throws SQLException {
        String sql = "DELETE FROM PROXECTO WHERE NumProxecto = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numProx);
            return ps.executeUpdate();
        }
    }


    // =========================================================
    // ACTUALIZACIONES - EJ5
    // =========================================================

    public static PreparedStatement prepararStatementIncrementoSalarioFijos(Connection conn) throws SQLException {
        String sql = "UPDATE EMPREGADOFIXO SET Salario = Salario + ? WHERE NSS = ?";
        return conn.prepareStatement(sql);
    }

    // =========================================================
    // ACTUALIZACIONES - EJ6
    // =========================================================

    public static boolean existeProyectoPorNumeroONombre(Connection conn, int numProx, String nomeProx) throws SQLException {
        String sql = "SELECT COUNT(*) FROM PROXECTO WHERE NumProxecto = ? OR NomeProxecto = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numProx);
            ps.setString(2, nomeProx);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    public static boolean existeDepartamentoPorNumero(Connection conn, int numDep) throws SQLException {
        String sql = "SELECT COUNT(*) FROM DEPARTAMENTO WHERE NumDepartamento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numDep);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    public static void insertarProyectoConResultSet(Connection conn, Proxecto p) throws SQLException {
        String sql = "SELECT NumProxecto, NomeProxecto, Lugar, NumDepartControla FROM PROXECTO";

        try (Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = st.executeQuery(sql)) {

            rs.moveToInsertRow();
            rs.updateInt("NumProxecto", p.getNumProxecto());
            rs.updateString("NomeProxecto", p.getNomeProxecto());
            rs.updateString("Lugar", p.getLugar());
            rs.updateInt("NumDepartControla", p.getNumDepartControla());
            rs.insertRow();
            rs.moveToCurrentRow();
        }
    }

    // =========================================================
    // ACTUALIZACIONES - EJ7
    // =========================================================

    public static ResultSet getEmpleadosFijosDeDepartamentoUpdatable(Connection conn, int numDep) throws SQLException {
        // ResultSet dinámico + updatable
        String sql = """
        SELECT ef.NSS, ef.Salario
        FROM EMPREGADOFIXO ef
        JOIN EMPREGADO e ON e.NSS = ef.NSS
        WHERE e.NumDepartamentoPertenece = ?
    """;

        PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ps.setInt(1, numDep);
        return ps.executeQuery(); // OJO: cerrar rs y statement en lógica
    }

    // =========================================================
    // ACTUALIZACIONES - EJ8
    // =========================================================

    public static ResultSet getEmpleadosConMasDeNProyectos(Connection conn, int n) throws SQLException {

        String sql = """
        SELECT e.NSS,
               (e.Nome + ' ' + e.Apelido1 + COALESCE(' ' + e.Apelido2, '')) AS NomeCompleto,
               e.Localidade,
               ef.Salario
        FROM EMPREGADO e
        JOIN EMPREGADOFIXO ef ON ef.NSS = e.NSS
        JOIN EMPREGADO_PROXECTO ep ON ep.NSS = e.NSS
        GROUP BY e.NSS, e.Nome, e.Apelido1, e.Apelido2, e.Localidade, ef.Salario
        HAVING COUNT(DISTINCT ep.NumProxecto) > ?
        ORDER BY e.NSS
    """;

        PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setInt(1, n);
        return ps.executeQuery();
    }

}
