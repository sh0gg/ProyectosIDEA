package procedimientos.persistencia;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InstaladorProcedimientosSQLServer {

    /**
     * Crea (recrea) procedimientos y función en SQL Server usando JDBC.
     * IMPORTANTE: En SQL Server, CREATE PROCEDURE/FUNCTION debe ir "solo" en el batch,
     * así que ejecutamos DROP con un Statement y después CREATE con otro Statement.
     */
    public static void instalarTodo(Connection conn) throws SQLException {
        instalarPrCambioDomicilio(conn);
        instalarPrDatosProxectos(conn);
        instalarPrDepartControlaProxec(conn);
        instalarFnNEmpDepart(conn);
    }

    public static void instalarPrCambioDomicilio(Connection conn) throws SQLException {
        ejecutarDropProcSiExiste(conn, "dbo.pr_CambioDomicilio");

        String create = """
            CREATE PROCEDURE dbo.pr_CambioDomicilio
                @NSS         VARCHAR(20),
                @Rua         VARCHAR(100),
                @Numero      INT,
                @Piso        VARCHAR(10),
                @CodPostal   VARCHAR(10),
                @Localidade  VARCHAR(100)
            AS
            BEGIN
                UPDATE EMPREGADO
                SET Rua=@Rua,
                    Numero=@Numero,
                    Piso=@Piso,
                    CodPostal=@CodPostal,
                    Localidade=@Localidade
                WHERE NSS=@NSS;
            END
            """;

        ejecutarSQL(conn, create);
    }

    public static void instalarPrDatosProxectos(Connection conn) throws SQLException {
        ejecutarDropProcSiExiste(conn, "dbo.pr_DatosProxectos");

        String create = """
            CREATE PROCEDURE dbo.pr_DatosProxectos
                @NumProx INT,
                @Nome VARCHAR(200) OUTPUT,
                @Lugar VARCHAR(200) OUTPUT,
                @Departamento VARCHAR(200) OUTPUT
            AS
            BEGIN
                SELECT
                    @Nome = p.NomeProxecto,
                    @Lugar = p.Lugar,
                    @Departamento = d.NomeDepartamento
                FROM PROXECTO p
                JOIN DEPARTAMENTO d ON d.NumDepartamento = p.NumDepartControla
                WHERE p.NumProxecto = @NumProx;
            END
            """;

        ejecutarSQL(conn, create);
    }

    public static void instalarPrDepartControlaProxec(Connection conn) throws SQLException {
        ejecutarDropProcSiExiste(conn, "dbo.pr_DepartControlaProxec");

        String create = """
            CREATE PROCEDURE dbo.pr_DepartControlaProxec
                @MinProx INT
            AS
            BEGIN
                SELECT d.NumDepartamento, d.NomeDepartamento, COUNT(*) AS NumProxectos
                FROM DEPARTAMENTO d
                JOIN PROXECTO p ON p.NumDepartControla = d.NumDepartamento
                GROUP BY d.NumDepartamento, d.NomeDepartamento
                HAVING COUNT(*) >= @MinProx
                ORDER BY NumProxectos DESC;
            END
            """;

        ejecutarSQL(conn, create);
    }

    public static void instalarFnNEmpDepart(Connection conn) throws SQLException {
        ejecutarDropFnSiExiste(conn, "dbo.fn_nEmpDepart");

        String create = """
            CREATE FUNCTION dbo.fn_nEmpDepart(@NomeDep VARCHAR(200))
            RETURNS INT
            AS
            BEGIN
                DECLARE @n INT;
                SELECT @n = COUNT(*)
                FROM EMPREGADO e
                JOIN DEPARTAMENTO d ON d.NumDepartamento = e.NumDepartamentoPertenece
                WHERE d.NomeDepartamento = @NomeDep;

                RETURN ISNULL(@n, 0);
            END
            """;

        ejecutarSQL(conn, create);
    }

    // ---------------- helpers ----------------

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
}
