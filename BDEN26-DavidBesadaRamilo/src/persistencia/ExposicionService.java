package persistencia;

import clases.Fotografia;
import util.GestorConexiones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static util.GestorConexiones.getConnection;
import static util.GestorConexiones.tablaExiste;

// DNI : 53612286e
// Nombre: David Besada

public class ExposicionService {


    public boolean crearTablaLaboratorio(Connection conn) throws SQLException {
        if (tablaExiste(conn, "FOTOGRAFO_LABORATORIO")) {
            System.out.println("La tabla FOTOGRAFO_LABORATORIO ya existe. Eliminando...");
            try (Statement st = conn.createStatement()) {
                st.executeUpdate("DROP TABLE FOTOGRAFO_LABORATORIO");
            }
        }
        if (tablaExiste(conn, "LABORATORIO")) {
            System.out.println("La tabla LABORATORIO ya existe. Eliminando...");
            try (Statement st = conn.createStatement()) {
                st.executeUpdate("DROP TABLE LABORATORIO");
            }
        }

        try (Statement st = conn.createStatement()) {
            String createTable = "CREATE TABLE LABORATORIO (" + "  ID INT NOT NULL," + "  NOMBRE VARCHAR(20) NOT NULL," + "  FECHA_INAUGURACION DATE NOT NULL," + "  CONSTRAINT PK_LABORATORIO PRIMARY KEY (ID))";

            System.out.println("Creando tabla LABORATORIO...");
            st.executeUpdate(createTable);

            st.executeUpdate("ALTER TABLE LABORATORIO " + "ADD CONSTRAINT UQ_NOME UNIQUE (NOMBRE)");
        } catch(SQLException ex) {
            System.out.println("Ha fallado al crear tabla LABORATORIO");
            return false;
        }
        return true;
    }

    public boolean crearTablaFotografoLaboratorio(Connection conn) throws SQLException {
        if (tablaExiste(conn, "FOTOGRAFO_LABORATORIO")) {
            System.out.println("La tabla FOTOGRAFO_LABORATORIO ya existe. Eliminando...");
            try (Statement st = conn.createStatement()) {
                st.executeUpdate("DROP TABLE FOTOGRAFO_LABORATORIO");
            }
        }

        try (Statement st = conn.createStatement()) {
            String createTable = "CREATE TABLE FOTOGRAFO_LABORATORIO (" + "  ID_FOTOGRAFO INT NOT NULL," + "  ID_LABORATORIO INT NOT NULL," + "  FECHA_INICIO DATE NOT NULL," + " FECHA_FIN DATE, " + " CONSTRAINT PK_FOTOGRAFO_LABORATORIO PRIMARY KEY (ID_FOTOGRAFO, ID_LABORATORIO, FECHA_INICIO))";

            System.out.println("Creando tabla FOTOGRAFO_LABORATORIO...");
            st.executeUpdate(createTable);

            st.executeUpdate("ALTER TABLE FOTOGRAFO_LABORATORIO " + "ADD CONSTRAINT CK_FECHA_FIN CHECK (FECHA_INICIO < FECHA_FIN)");
        } catch(SQLException ex) {
            System.out.println("Ha fallado al crear tabla FOTOGRAFO_LABORATORIO");
            return false;
        }
        return true;
    }

    public int addColeccionExposicion(Connection conn, String nombreFotografo, String nombreExposicion, List<Fotografia> coleccion) throws SQLException {

        int codigoFotografo = fotografoExiste(conn,nombreFotografo);
        int codigoExposicion = exposicionExiste(conn,nombreExposicion);
        if (codigoFotografo < 0) {
            return -1;
        }
        if (codigoExposicion < 0) {
            return -2;
        }

        ArrayList<String> sentencias = new ArrayList<>();
        for (Fotografia f : coleccion) {
            sentencias.add("INSERT INTO FOTOGRAFIA VALUES ('"+ f.getNombre() + "', '" + f.getMedidas() +"', '"+ Date.valueOf(f.getFecha()) +"', "+ codigoFotografo + ", " + codigoExposicion + ", '" + f.getColor() + "')");
        }

        // actualizar el numero de fotografias despues de añadirlas al artista
        int numFotografias = fnNEmpDepart(conn, nombreFotografo);
        sentencias.add("UPDATE FOTOGRAFO SET NUMFOTOGRAFIAS = " + numFotografias + " WHERE NOME = '" + nombreFotografo +"'");

        if (GestorConexiones.ejecutarLoteTransaccional(conn, sentencias)) {
            sentencias.clear();
            for (Fotografia f : coleccion) {
                int codigoFotografia = fotografiaExiste(conn, f.getNombre());
                if (Objects.equals(f.getTipo(), "DOCUMENTAL")){
                    sentencias.add("INSERT INTO DOCUMENTAL VALUES (" + codigoFotografia + ", '" + f.getTipoDocumental()+"')");
                } else {
                    sentencias.add("INSERT INTO ARTISTICA VALUES (" + codigoFotografia + ", '" + f.getEncuadre() + "', '" + f.getComposicion()+ "')");
                }
            }
            return 1;
        } else return 0;
    }

    public int addColeccionExposicion(Connection conn, String nombreExposicion, List<Fotografia> coleccion) throws SQLException {

        int codigoExposicion = exposicionExiste(conn,nombreExposicion);
        if (codigoExposicion < 0) {
            return -1;
        }

        ArrayList<String> sentencias = new ArrayList<>();
        for (Fotografia f : coleccion) {
            sentencias.add("INSERT INTO FOTOGRAFIA VALUES ('"+ f.getNombre() + "', '" + f.getMedidas() +"', '"+ Date.valueOf(f.getFecha()) +"', "+ f.getId_fotografo() + ", " + codigoExposicion + ", '" + f.getColor() + "')");
        }

        if (GestorConexiones.ejecutarLoteTransaccional(conn, sentencias)) {
            return 1;
        } else return 0;
    }

    public static int fnNEmpDepart(Connection conn, String nomeDep) throws SQLException {
        String call = "{?=call dbo.fn_CalcularFotografias(?)}";
        try (CallableStatement cs = conn.prepareCall(call)) {
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setString(2, nomeDep);
            cs.execute();
            return cs.getInt(1);
        }
    }

    private int fotografiaExiste(Connection conn, String nombreFotografia) throws SQLException {
        String sqlCheck = "SELECT CODIGO FROM FOTOGRAFIA WHERE NOME = ?";

        try (PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
            psCheck.setString(1, nombreFotografia);

            try (ResultSet rs = psCheck.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    private int exposicionExiste(Connection conn, String nombreExposicion) throws SQLException {
        String sqlCheck = "SELECT CODIGO FROM EXPOSICION WHERE NOME = ?";

        try (PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
            psCheck.setString(1, nombreExposicion);

            try (ResultSet rs = psCheck.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    private int fotografoExiste(Connection conn, String nombreFotografo) throws SQLException{
        String sqlCheck = "SELECT CODIGO FROM FOTOGRAFO WHERE NOME = ?";

        try (PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
            psCheck.setString(1, nombreFotografo);

            try (ResultSet rs = psCheck.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    public void crearFnCalcularFotografias(Connection conn) throws SQLException {
        ejecutarDropFnSiExiste(conn, "dbo.fn_CalcularFotografias");

        String create = """
            CREATE FUNCTION dbo.fn_CalcularFotografias(@NomeFotografo VARCHAR(20))
            RETURNS INT
            AS
            BEGIN
                DECLARE @n INT;
                SELECT @n = COUNT(*)
                FROM FOTOGRAFIA ft
                INNER JOIN FOTOGRAFO f ON ft.COD_FOTOGRAFO = f.CODIGO
                WHERE f.NOME = @NomeFotografo;

                RETURN ISNULL(@n, 0);
            END
            """;

        ejecutarSQL(conn, create);
    }

    private static void ejecutarDropProcSiExiste(Connection conn, String fullName) throws SQLException {
        String drop = """
            IF OBJECT_ID('%s', 'P') IS NOT NULL
                DROP PROCEDURE %s;
            """.formatted(fullName, fullName);

        ejecutarSQL(conn, drop);
    }

    private static void ejecutarDropFnSiExiste(Connection conn, String fullName) throws SQLException {
        String drop = """
            IF OBJECT_ID('%s', 'FN') IS NOT NULL
                DROP FUNCTION %s;
            """.formatted(fullName, fullName);

        ejecutarSQL(conn, drop);
    }

    private static void ejecutarSQL(Connection conn, String sql) throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        }
    }

    public void crearPrObtenerLocalidadProvincia(Connection conn) throws SQLException {
        ejecutarDropProcSiExiste(conn, "dbo.pr_ObtenerLocalidadProvincia");

        String create = """
            CREATE PROCEDURE dbo.pr_ObtenerLocalidadProvincia
                @NomeExposicion STRING
            AS
            BEGIN
                SELECT L.NOME + '(' + P.NOME + ')' AS NOME
                FROM EXPOSICION E
                INNER JOIN LOCALIDADE L ON L.CODIGO = E.COD_LOCALIDADE
                INNER JOIN PROVINCIA P ON P.CODIGO = L.COD_PROVINCIA
                WHERE E.NOME = @NomeExposicion;
            END
            """;

        ejecutarSQL(conn, create);
    }

    public void crearPrDatosFotosExposicion(Connection conn) throws  SQLException {
        ejecutarDropProcSiExiste(conn, "dbo.pr_DatosFotosExposicion");

        String create = """
            CREATE PROCEDURE dbo.pr_ObtenerLocalidadProvincia
                @NomeExposicion STRING
            AS
            BEGIN
                SELECT ('ARTISTICA O DOCUMENTAL ' + FT.NOME + ' -' + F.NOME + '-') AS STRING
                FROM EXPOSICION E
                INNER JOIN FOTOGRAFIA FT ON FT.COD_EXPOSICION = E.CODIGO
                INNER JOIN FOTOGRAFO F ON FT.COD_FOTOGRAFO = F.CODIGO
                WHERE E.NOME = @NomeExposicion;
            END
            """;

        ejecutarSQL(conn, create);
    }

    public static List<String> prDatosFotosExposicion(Connection conn, String nombreExpo) throws SQLException {
        String call = "{call dbo.pr_DatosFotosExposicion(?)}";
        try (CallableStatement cs = conn.prepareCall(call)) {

            cs.setString(1, nombreExpo);

            boolean hasRs = cs.execute();
            List<String> out = new ArrayList<>();

            if (hasRs) {
                try (ResultSet rs = cs.getResultSet()) {
                    while (rs.next()) {

                        String texto = rs.getString(1);
                        out.add(texto);
                    }
                }
            } else {
                out.add("No devolveu ResultSet. UpdateCount=" + cs.getUpdateCount());
            }

            return out;
        }
    }

    public static String prObtenerLocalidadProvincia(Connection conn, String nombreExpo) throws SQLException {
        String call = "{call dbo.pr_ObtenerLocalidadProvincia(?)}";
        try (CallableStatement cs = conn.prepareCall(call)) {

            cs.setString(1, nombreExpo);

            boolean hasRs = cs.execute();
            String out = "";

            if (hasRs) {
                try (ResultSet rs = cs.getResultSet()) {
                    while (rs.next()) {

                        String texto = rs.getString(1);
                        out = texto;
                    }
                }
            } else {
                out = "No devolveu ResultSet. UpdateCount=" + cs.getUpdateCount();
            }

            return out;
        }
    }

    public int moverFotosExpo(Connection conn, String nombreExp1, String nombreExp2) throws SQLException {
        String sqlCheck = "SELECT NOME, MEDIDAS, DATA, COD_FOTOGRAFO, COLOR FROM FOTOGRAFIA FT INNER JOIN EXPOSICION E ON FT.COD_EXPOSICION = E.CODIGO WHERE E.NOME = ?;";

        List<Fotografia> fotos = new ArrayList<>();
        try (PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
            psCheck.setString(1, nombreExp1);

            try (ResultSet rs = psCheck.executeQuery()) {
                rs.next();
                fotos.add(new Fotografia(rs.getString(1), rs.getString(2), rs.getDate(3).toString(), (Character) rs.getObject(4)));
            }
        }
        int resultado = addColeccionExposicion(conn, nombreExp2, fotos);
        return resultado;
    }

    public boolean borrarExposicion(Connection conn, String nombreExpo) throws SQLException {
        String sqlCheck = "DELETE FROM EXPOSICION WHERE NOME = ?";
        int resultado = 0;
        try (PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
            psCheck.setString(1, nombreExpo);
            resultado = psCheck.executeUpdate();
        }
        if (resultado != 1) {
            return false;
        }
        return true;
    }
}
