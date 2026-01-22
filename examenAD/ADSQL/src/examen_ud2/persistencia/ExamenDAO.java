package examen_ud2.persistencia;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamenDAO {

    // -------- METADATOS ----------
    public record InfoBD(String producto, String version, String driver, String url, String usuario) {}

    public static InfoBD getInfoBD(Connection conn) throws SQLException {
        DatabaseMetaData md = conn.getMetaData();
        return new InfoBD(
                md.getDatabaseProductName(),
                md.getDatabaseProductVersion(),
                md.getDriverName(),
                md.getURL(),
                md.getUserName()
        );
    }

    public static boolean existeTabla(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData md = conn.getMetaData();
        try (ResultSet rs = md.getTables(null, "dbo", tableName, new String[]{"TABLE"})) {
            return rs.next();
        }
    }

    public static List<String> columnasTabla(Connection conn, String tableName) throws SQLException {
        List<String> cols = new ArrayList<>();
        DatabaseMetaData md = conn.getMetaData();
        try (ResultSet rs = md.getColumns(null, "dbo", tableName, null)) {
            while (rs.next()) {
                String n = rs.getString("COLUMN_NAME");
                String t = rs.getString("TYPE_NAME");
                int sz = rs.getInt("COLUMN_SIZE");
                cols.add(n + " " + t + "(" + sz + ")");
            }
        }
        return cols;
    }

    // -------- INSERTS (PreparedStatement + batch) ----------
    public static int insertDep(Connection conn, String nombre, String ciudad) throws SQLException {
        String sql = "INSERT INTO dbo.PR_EXAM_DEP(Nombre,Ciudad) VALUES(?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, ciudad);
            return ps.executeUpdate();
        }
    }

    public static int[] insertEmpBatch(Connection conn, List<EmpRow> empleados) throws SQLException {
        String sql = "INSERT INTO dbo.PR_EXAM_EMP(NSS,Nombre,Apellidos,Salario,Tipo,IdDep) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (EmpRow e : empleados) {
                ps.setString(1, e.nss);
                ps.setString(2, e.nombre);
                ps.setString(3, e.apellidos);
                ps.setBigDecimal(4, e.salario);
                ps.setString(5, e.tipo);
                ps.setInt(6, e.idDep);
                ps.addBatch();
            }
            return ps.executeBatch();
        }
    }

    public static int insertProy(Connection conn, String nombre, String lugar, int idDepControla) throws SQLException {
        String sql = "INSERT INTO dbo.PR_EXAM_PROY(Nombre,Lugar,IdDepControla) VALUES(?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, lugar);
            ps.setInt(3, idDepControla);
            return ps.executeUpdate();
        }
    }

    public static int insertAsig(Connection conn, String nss, int idProy, int horas) throws SQLException {
        String sql = "INSERT INTO dbo.PR_EXAM_ASIG(NSS,IdProy,Horas) VALUES(?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nss);
            ps.setInt(2, idProy);
            ps.setInt(3, horas);
            return ps.executeUpdate();
        }
    }

    // -------- CONSULTAS (SELECT) ----------
    public record EmpView(String nss, String nombre, String apellidos, BigDecimal salario, String tipo, String depNombre) {}

    public static List<EmpView> empleadosConDep(Connection conn) throws SQLException {
        String sql = """
            SELECT e.NSS, e.Nombre, e.Apellidos, e.Salario, e.Tipo, d.Nombre AS DepNombre
            FROM dbo.PR_EXAM_EMP e
            JOIN dbo.PR_EXAM_DEP d ON d.IdDep = e.IdDep
            ORDER BY d.Nombre, e.Apellidos
            """;
        List<EmpView> out = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new EmpView(
                        rs.getString("NSS"),
                        rs.getString("Nombre"),
                        rs.getString("Apellidos"),
                        rs.getBigDecimal("Salario"),
                        rs.getString("Tipo"),
                        rs.getString("DepNombre")
                ));
            }
        }
        return out;
    }

    public record DepAgg(String depNombre, int numEmp, BigDecimal salarioMedio) {}

    public static List<DepAgg> resumenPorDep(Connection conn) throws SQLException {
        String sql = """
            SELECT d.Nombre AS DepNombre,
                   COUNT(*) AS NumEmp,
                   AVG(CAST(e.Salario AS DECIMAL(10,2))) AS SalarioMedio
            FROM dbo.PR_EXAM_DEP d
            JOIN dbo.PR_EXAM_EMP e ON e.IdDep = d.IdDep
            GROUP BY d.Nombre
            ORDER BY NumEmp DESC
            """;
        List<DepAgg> out = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new DepAgg(
                        rs.getString("DepNombre"),
                        rs.getInt("NumEmp"),
                        rs.getBigDecimal("SalarioMedio")
                ));
            }
        }
        return out;
    }

    // -------- ACTUALIZACIONES (UPDATE/DELETE) ----------
    public static int updateSalarioDep(Connection conn, int idDep, BigDecimal inc) throws SQLException {
        String sql = "UPDATE dbo.PR_EXAM_EMP SET Salario = Salario + ? WHERE IdDep = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, inc);
            ps.setInt(2, idDep);
            return ps.executeUpdate();
        }
    }

    public static int deleteEmpleado(Connection conn, String nss) throws SQLException {
        String sql = "DELETE FROM dbo.PR_EXAM_EMP WHERE NSS = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nss);
            return ps.executeUpdate();
        }
    }

    // -------- RESULTSET UPDATABLE ----------
    public static int actualizarTipoConResultSet(Connection conn, String tipoFrom, String tipoTo) throws SQLException {
        String sql = "SELECT NSS, Tipo FROM dbo.PR_EXAM_EMP WHERE Tipo = ?";
        try (PreparedStatement ps = conn.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        )) {
            ps.setString(1, tipoFrom);

            int count = 0;
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rs.updateString("Tipo", tipoTo);
                    rs.updateRow();
                    count++;
                }
            }
            return count;
        }
    }

    public static int insertarEmpleadoConResultSet(Connection conn, EmpRow e) throws SQLException {
        String sql = "SELECT NSS,Nombre,Apellidos,Salario,Tipo,IdDep FROM dbo.PR_EXAM_EMP";
        try (PreparedStatement ps = conn.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        )) {
            try (ResultSet rs = ps.executeQuery()) {
                rs.moveToInsertRow();
                rs.updateString("NSS", e.nss);
                rs.updateString("Nombre", e.nombre);
                rs.updateString("Apellidos", e.apellidos);
                rs.updateBigDecimal("Salario", e.salario);
                rs.updateString("Tipo", e.tipo);
                rs.updateInt("IdDep", e.idDep);
                rs.insertRow();
                rs.moveToCurrentRow();
                return 1;
            }
        }
    }

    // -------- PROCEDIMIENTOS / FUNCIONES (CallableStatement) ----------
    public static int prCambioSalario(Connection conn, String nss, BigDecimal incremento) throws SQLException {
        String call = "{call dbo.pr_CambioSalario(?,?)}";
        try (CallableStatement cs = conn.prepareCall(call)) {
            cs.setString(1, nss);
            cs.setBigDecimal(2, incremento);
            return cs.executeUpdate();
        }
    }

    public record DatosProy(String nombre, String lugar, String dep) {}

    public static DatosProy prDatosProy(Connection conn, int idProy) throws SQLException {
        String call = "{call dbo.pr_DatosProy(?,?,?,?)}";
        try (CallableStatement cs = conn.prepareCall(call)) {
            cs.setInt(1, idProy);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.registerOutParameter(4, Types.VARCHAR);

            cs.execute();

            String n = cs.getString(2);
            String l = cs.getString(3);
            String d = cs.getString(4);

            if (n == null && l == null && d == null) return null;
            return new DatosProy(n, l, d);
        }
    }

    public static List<String> prDepConMinProyExecute(Connection conn, int minProy) throws SQLException {
        String call = "{call dbo.pr_DepConMinProy(?)}";
        try (CallableStatement cs = conn.prepareCall(call)) {
            cs.setInt(1, minProy);

            boolean hasRs = cs.execute();
            List<String> out = new ArrayList<>();

            if (hasRs) {
                try (ResultSet rs = cs.getResultSet()) {
                    while (rs.next()) {
                        out.add("[" + rs.getInt("IdDep") + "] " +
                                rs.getString("Nombre") + " -> " +
                                rs.getInt("NumProy") + " proy");
                    }
                }
            } else {
                out.add("No ResultSet; UpdateCount=" + cs.getUpdateCount());
            }
            return out;
        }
    }

    public static int fnNumEmpDep(Connection conn, String nombreDep) throws SQLException {
        String call = "{?=call dbo.fn_numEmpDep(?)}";
        try (CallableStatement cs = conn.prepareCall(call)) {
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setString(2, nombreDep);
            cs.execute();
            return cs.getInt(1);
        }
    }

    // -------- record auxiliar ----------
    public record EmpRow(String nss, String nombre, String apellidos, BigDecimal salario, String tipo, int idDep) {}
}
