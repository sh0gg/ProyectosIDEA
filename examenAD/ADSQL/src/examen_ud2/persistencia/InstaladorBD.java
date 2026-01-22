package examen_ud2.persistencia;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InstaladorBD {

    // ---------- TABLAS ----------
    public static void recrearTablas(Connection conn) throws SQLException {
        dropSiExiste(conn, "dbo.PR_EXAM_ASIG");
        dropSiExiste(conn, "dbo.PR_EXAM_PROY");
        dropSiExiste(conn, "dbo.PR_EXAM_EMP");
        dropSiExiste(conn, "dbo.PR_EXAM_DEP");

        crearDep(conn);
        crearEmp(conn);
        crearProy(conn);
        crearAsig(conn);
    }

    private static void crearDep(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE dbo.PR_EXAM_DEP(
                IdDep INT IDENTITY(1,1) NOT NULL,
                Nombre VARCHAR(60) NOT NULL,
                Ciudad VARCHAR(60) NULL,
                CONSTRAINT PK_PR_EXAM_DEP PRIMARY KEY (IdDep),
                CONSTRAINT UQ_PR_EXAM_DEP_Nombre UNIQUE (Nombre),
                CONSTRAINT CK_PR_EXAM_DEP_Ciudad CHECK (Ciudad IS NULL OR LEN(Ciudad) >= 2)
            )
            """;
        exec(conn, sql);
    }

    private static void crearEmp(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE dbo.PR_EXAM_EMP(
                NSS VARCHAR(20) NOT NULL,
                Nombre VARCHAR(60) NOT NULL,
                Apellidos VARCHAR(80) NOT NULL,
                Salario DECIMAL(10,2) NOT NULL,
                Tipo VARCHAR(10) NOT NULL,
                IdDep INT NOT NULL,
                CONSTRAINT PK_PR_EXAM_EMP PRIMARY KEY (NSS),
                CONSTRAINT CK_PR_EXAM_EMP_Salario CHECK (Salario >= 0),
                CONSTRAINT CK_PR_EXAM_EMP_Tipo CHECK (Tipo IN ('FIXO','TEMP')),
                CONSTRAINT FK_PR_EXAM_EMP_DEP FOREIGN KEY (IdDep)
                    REFERENCES dbo.PR_EXAM_DEP(IdDep)
                    ON UPDATE CASCADE
                    ON DELETE NO ACTION
            )
            """;
        exec(conn, sql);
    }

    private static void crearProy(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE dbo.PR_EXAM_PROY(
                IdProy INT IDENTITY(1,1) NOT NULL,
                Nombre VARCHAR(80) NOT NULL,
                Lugar VARCHAR(80) NOT NULL,
                IdDepControla INT NOT NULL,
                CONSTRAINT PK_PR_EXAM_PROY PRIMARY KEY (IdProy),
                CONSTRAINT UQ_PR_EXAM_PROY_Nombre UNIQUE (Nombre),
                CONSTRAINT FK_PR_EXAM_PROY_DEP FOREIGN KEY (IdDepControla)
                    REFERENCES dbo.PR_EXAM_DEP(IdDep)
            )
            """;
        exec(conn, sql);
    }

    private static void crearAsig(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE dbo.PR_EXAM_ASIG(
                NSS VARCHAR(20) NOT NULL,
                IdProy INT NOT NULL,
                Horas INT NOT NULL,
                CONSTRAINT PK_PR_EXAM_ASIG PRIMARY KEY (NSS, IdProy),
                CONSTRAINT CK_PR_EXAM_ASIG_Horas CHECK (Horas >= 0 AND Horas <= 300),
                CONSTRAINT FK_PR_EXAM_ASIG_EMP FOREIGN KEY (NSS)
                    REFERENCES dbo.PR_EXAM_EMP(NSS)
                    ON DELETE CASCADE,
                CONSTRAINT FK_PR_EXAM_ASIG_PROY FOREIGN KEY (IdProy)
                    REFERENCES dbo.PR_EXAM_PROY(IdProy)
                    ON DELETE CASCADE
            )
            """;
        exec(conn, sql);
    }

    private static void dropSiExiste(Connection conn, String fullName) throws SQLException {
        String sql = """
            IF OBJECT_ID('%s', 'U') IS NOT NULL
                DROP TABLE %s;
            """.formatted(fullName, fullName);
        exec(conn, sql);
    }

    // ---------- PROCEDIMIENTOS / FUNCIONES ----------
    public static void recrearRutinas(Connection conn) throws SQLException {
        dropProcSiExiste(conn, "dbo.pr_CambioSalario");
        dropProcSiExiste(conn, "dbo.pr_DatosProy");
        dropProcSiExiste(conn, "dbo.pr_DepConMinProy");
        dropFnSiExiste(conn, "dbo.fn_numEmpDep");

        crearPrCambioSalario(conn);
        crearPrDatosProy(conn);
        crearPrDepConMinProy(conn);
        crearFnNumEmpDep(conn);
    }

    private static void crearPrCambioSalario(Connection conn) throws SQLException {
        String sql = """
            CREATE PROCEDURE dbo.pr_CambioSalario
                @NSS VARCHAR(20),
                @Incremento DECIMAL(10,2)
            AS
            BEGIN
                UPDATE dbo.PR_EXAM_EMP
                SET Salario = Salario + @Incremento
                WHERE NSS = @NSS;
            END
            """;
        exec(conn, sql);
    }

    private static void crearPrDatosProy(Connection conn) throws SQLException {
        String sql = """
            CREATE PROCEDURE dbo.pr_DatosProy
                @IdProy INT,
                @Nombre VARCHAR(80) OUTPUT,
                @Lugar VARCHAR(80) OUTPUT,
                @Dep VARCHAR(60) OUTPUT
            AS
            BEGIN
                SELECT
                    @Nombre = p.Nombre,
                    @Lugar  = p.Lugar,
                    @Dep    = d.Nombre
                FROM dbo.PR_EXAM_PROY p
                JOIN dbo.PR_EXAM_DEP d ON d.IdDep = p.IdDepControla
                WHERE p.IdProy = @IdProy;
            END
            """;
        exec(conn, sql);
    }

    private static void crearPrDepConMinProy(Connection conn) throws SQLException {
        String sql = """
            CREATE PROCEDURE dbo.pr_DepConMinProy
                @MinProy INT
            AS
            BEGIN
                SELECT d.IdDep, d.Nombre, COUNT(*) AS NumProy
                FROM dbo.PR_EXAM_DEP d
                JOIN dbo.PR_EXAM_PROY p ON p.IdDepControla = d.IdDep
                GROUP BY d.IdDep, d.Nombre
                HAVING COUNT(*) >= @MinProy
                ORDER BY NumProy DESC;
            END
            """;
        exec(conn, sql);
    }

    private static void crearFnNumEmpDep(Connection conn) throws SQLException {
        String sql = """
            CREATE FUNCTION dbo.fn_numEmpDep(@NombreDep VARCHAR(60))
            RETURNS INT
            AS
            BEGIN
                DECLARE @n INT;
                SELECT @n = COUNT(*)
                FROM dbo.PR_EXAM_EMP e
                JOIN dbo.PR_EXAM_DEP d ON d.IdDep = e.IdDep
                WHERE d.Nombre = @NombreDep;

                RETURN ISNULL(@n, 0);
            END
            """;
        exec(conn, sql);
    }

    private static void dropProcSiExiste(Connection conn, String fullName) throws SQLException {
        String sql = """
            IF OBJECT_ID('%s','P') IS NOT NULL
                DROP PROCEDURE %s;
            """.formatted(fullName, fullName);
        exec(conn, sql);
    }

    private static void dropFnSiExiste(Connection conn, String fullName) throws SQLException {
        String sql = """
            IF OBJECT_ID('%s','FN') IS NOT NULL
                DROP FUNCTION %s;
            """.formatted(fullName, fullName);
        exec(conn, sql);
    }

    // ---------- helper ----------
    private static void exec(Connection conn, String sql) throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        }
    }
}
