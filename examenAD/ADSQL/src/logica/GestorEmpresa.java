package logica;

import util.TipoSGBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Crea las tablas FAMILIAR_EMPREGADO y las tablas de vehículos
 * usando sentencias DDL desde Java (Statement, batch, metadatos...). :contentReference[oaicite:4]{index=4}
 */
public class GestorEmpresa {

    // =========================================================
    // FAMILIARES DOS EMPREGADOS (tabla única, sentencias 1 a 1)
    // =========================================================
    public void crearTablaFamiliares(Connection conn, TipoSGBD tipo) throws SQLException {

        if (tablaExiste(conn, null, "FAMILIAR_EMPREGADO")) {
            System.out.println("La tabla FAMILIAR_EMPREGADO ya existe. Eliminando...");
            try (Statement st = conn.createStatement()) {
                st.executeUpdate("DROP TABLE FAMILIAR_EMPREGADO");
            }
        }

        try (Statement st = conn.createStatement()) {

            if (tipo == TipoSGBD.SQLITE) {

                // IMPORTANTE: activar claves foráneas en SQLite
                st.execute("PRAGMA foreign_keys = ON");

                String createSQLite =
                        "CREATE TABLE FAMILIAR_EMPREGADO (" +
                                "  NSS_EMPREGADO TEXT NOT NULL," +
                                "  NUM_FAMILIAR INTEGER NOT NULL," +
                                "  NSS_FAMILIAR TEXT NOT NULL," +
                                "  NOME TEXT NOT NULL," +
                                "  APELIDO1 TEXT," +
                                "  APELIDO2 TEXT," +
                                "  DATANACEMENTO DATE," +
                                "  PARENTESCO TEXT," +
                                "  SEXO TEXT NOT NULL DEFAULT 'M'," +

                                // PK compuesta
                                "  PRIMARY KEY (NSS_EMPREGADO, NUM_FAMILIAR)," +

                                // UNIQUE
                                "  UNIQUE (NSS_FAMILIAR)," +

                                // CHECK
                                "  CHECK (SEXO IN ('H','M'))," +

                                // FK
                                "  FOREIGN KEY (NSS_EMPREGADO) REFERENCES EMPREGADO(NSS)" +
                                ")";

                System.out.println("Creando tabla FAMILIAR_EMPREGADO (SQLite)...");
                st.executeUpdate(createSQLite);

            } else {

                // ===== MySQL / SQL Server =====
                String createTable =
                        "CREATE TABLE FAMILIAR_EMPREGADO (" +
                                "  NSS_EMPREGADO VARCHAR(15) NOT NULL," +
                                "  NUM_FAMILIAR INT NOT NULL," +
                                "  NSS_FAMILIAR VARCHAR(15) NOT NULL," +
                                "  NOME VARCHAR(25) NOT NULL," +
                                "  APELIDO1 VARCHAR(25)," +
                                "  APELIDO2 VARCHAR(25)," +
                                "  DATANACEMENTO DATE," +
                                "  PARENTESCO VARCHAR(20)," +
                                "  SEXO CHAR(1) DEFAULT 'M' NOT NULL," +
                                "  CONSTRAINT PK_FAMILIAR_EMP PRIMARY KEY (NSS_EMPREGADO, NUM_FAMILIAR)" +
                                ")";

                System.out.println("Creando tabla FAMILIAR_EMPREGADO...");
                st.executeUpdate(createTable);

                st.executeUpdate(
                        "ALTER TABLE FAMILIAR_EMPREGADO " +
                                "ADD CONSTRAINT UQ_FAMILIAR_NSS UNIQUE (NSS_FAMILIAR)");

                st.executeUpdate(
                        "ALTER TABLE FAMILIAR_EMPREGADO " +
                                "ADD CONSTRAINT CK_FAMILIAR_SEXO CHECK (SEXO IN ('H','M'))");

                st.executeUpdate(
                        "ALTER TABLE FAMILIAR_EMPREGADO " +
                                "ADD CONSTRAINT FK_FAMILIAR_EMPREGADO " +
                                "FOREIGN KEY (NSS_EMPREGADO) REFERENCES EMPREGADO(NSS)");
            }

            System.out.println("Tabla FAMILIAR_EMPREGADO creada correctamente.");
        }
    }


    // =========================================================
    // VEHÍCULOS (varias tablas, ejecutadas como lote)
    // =========================================================
    public void crearTaboasVehiculos(Connection conn, TipoSGBD tipo) throws SQLException {
        // Preparamos la lista de sentencias DDL específicas del SGBD
        List<String> sentencias = obtenerDDLVehiculos(tipo);

        try (Statement st = conn.createStatement()) {
            for (String sql : sentencias) {
                System.out.println("Añadiendo al lote: " + sql);
                st.addBatch(sql);
            }

            int[] resultados = st.executeBatch();
            System.out.println("Lote de vehículos ejecutado. Sentencias: " + resultados.length);
        }
    }

    private List<String> obtenerDDLVehiculos(TipoSGBD tipo) {
        List<String> ddl = new ArrayList<>();

        switch (tipo) {
            case MYSQL:
                ddl.add("DROP TABLE IF EXISTS VEHICULO_PROPIO");
                ddl.add("DROP TABLE IF EXISTS VEHICULO_RENTING");
                ddl.add("DROP TABLE IF EXISTS VEHICULO");

                ddl.add(
                        "CREATE TABLE VEHICULO (" +
                                "  ID INT AUTO_INCREMENT NOT NULL," +
                                "  MATRICULA VARCHAR(10) NOT NULL," +
                                "  MARCA VARCHAR(25) NOT NULL," +
                                "  MODELO VARCHAR(25) NOT NULL," +
                                "  TIPO_COMBUSTIBLE VARCHAR(15) NOT NULL," +
                                "  TIPO_VEHICULO CHAR(1) NOT NULL," +     // 'P' propio, 'R' renting
                                "  CONSTRAINT PK_VEHICULO PRIMARY KEY (ID)," +
                                "  CONSTRAINT UQ_VEHICULO_MATRICULA UNIQUE (MATRICULA)," +
                                "  CONSTRAINT CK_VEHICULO_TIPO CHECK (TIPO_VEHICULO IN ('P','R'))" +
                                ") ENGINE=InnoDB"
                );

                ddl.add(
                        "CREATE TABLE VEHICULO_PROPIO (" +
                                "  ID INT NOT NULL," +
                                "  DATA_COMPRA DATE," +
                                "  PREZO_COMPRA DECIMAL(10,2)," +
                                "  CONSTRAINT PK_VEH_PROPIO PRIMARY KEY (ID)," +
                                "  CONSTRAINT FK_VEH_PROPIO_VEHICULO FOREIGN KEY (ID) REFERENCES VEHICULO(ID)" +
                                ") ENGINE=InnoDB"
                );

                ddl.add(
                        "CREATE TABLE VEHICULO_RENTING (" +
                                "  ID INT NOT NULL," +
                                "  DATA_INICIO DATE," +
                                "  PREZO_MENSUAL DECIMAL(10,2)," +
                                "  MESES_CONTRATADOS INT," +
                                "  CONSTRAINT PK_VEH_RENTING PRIMARY KEY (ID)," +
                                "  CONSTRAINT FK_VEH_RENTING_VEHICULO FOREIGN KEY (ID) REFERENCES VEHICULO(ID)" +
                                ") ENGINE=InnoDB"
                );
                break;

            case SQLSERVER:
                ddl.add("IF OBJECT_ID('VEHICULO_RENTING','U') IS NOT NULL DROP TABLE VEHICULO_RENTING");
                ddl.add("IF OBJECT_ID('VEHICULO_PROPIO','U') IS NOT NULL DROP TABLE VEHICULO_PROPIO");
                ddl.add("IF OBJECT_ID('VEHICULO','U') IS NOT NULL DROP TABLE VEHICULO");

                ddl.add(
                        "CREATE TABLE VEHICULO (" +
                                "  ID INT IDENTITY(1,1) NOT NULL," +
                                "  MATRICULA VARCHAR(10) NOT NULL," +
                                "  MARCA VARCHAR(25) NOT NULL," +
                                "  MODELO VARCHAR(25) NOT NULL," +
                                "  TIPO_COMBUSTIBLE VARCHAR(15) NOT NULL," +
                                "  TIPO_VEHICULO CHAR(1) NOT NULL," +
                                "  CONSTRAINT PK_VEHICULO PRIMARY KEY (ID)," +
                                "  CONSTRAINT UQ_VEHICULO_MATRICULA UNIQUE (MATRICULA)," +
                                "  CONSTRAINT CK_VEHICULO_TIPO CHECK (TIPO_VEHICULO IN ('P','R'))" +
                                ")"
                );

                ddl.add(
                        "CREATE TABLE VEHICULO_PROPIO (" +
                                "  ID INT NOT NULL," +
                                "  DATA_COMPRA DATE," +
                                "  PREZO_COMPRA DECIMAL(10,2)," +
                                "  CONSTRAINT PK_VEH_PROPIO PRIMARY KEY (ID)," +
                                "  CONSTRAINT FK_VEH_PROPIO_VEHICULO FOREIGN KEY (ID) REFERENCES VEHICULO(ID)" +
                                ")"
                );

                ddl.add(
                        "CREATE TABLE VEHICULO_RENTING (" +
                                "  ID INT NOT NULL," +
                                "  DATA_INICIO DATE," +
                                "  PREZO_MENSUAL DECIMAL(10,2)," +
                                "  MESES_CONTRATADOS INT," +
                                "  CONSTRAINT PK_VEH_RENTING PRIMARY KEY (ID)," +
                                "  CONSTRAINT FK_VEH_RENTING_VEHICULO FOREIGN KEY (ID) REFERENCES VEHICULO(ID)" +
                                ")"
                );
                break;

            case SQLITE:
                // En SQLite las FKs deben ir en el CREATE TABLE. :contentReference[oaicite:5]{index=5}
                ddl.add("DROP TABLE IF EXISTS VEHICULO_RENTING");
                ddl.add("DROP TABLE IF EXISTS VEHICULO_PROPIO");
                ddl.add("DROP TABLE IF EXISTS VEHICULO");

                ddl.add("PRAGMA foreign_keys = ON");

                ddl.add(
                        "CREATE TABLE VEHICULO (" +
                                "  ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "  MATRICULA TEXT NOT NULL UNIQUE," +
                                "  MARCA TEXT NOT NULL," +
                                "  MODELO TEXT NOT NULL," +
                                "  TIPO_COMBUSTIBLE TEXT NOT NULL," +
                                "  TIPO_VEHICULO TEXT NOT NULL," +
                                "  CONSTRAINT CK_VEHICULO_TIPO CHECK (TIPO_VEHICULO IN ('P','R'))" +
                                ")"
                );

                ddl.add(
                        "CREATE TABLE VEHICULO_PROPIO (" +
                                "  ID INTEGER NOT NULL PRIMARY KEY," +
                                "  DATA_COMPRA DATE," +
                                "  PREZO_COMPRA REAL," +
                                "  FOREIGN KEY (ID) REFERENCES VEHICULO(ID)" +
                                ")"
                );

                ddl.add(
                        "CREATE TABLE VEHICULO_RENTING (" +
                                "  ID INTEGER NOT NULL PRIMARY KEY," +
                                "  DATA_INICIO DATE," +
                                "  PREZO_MENSUAL REAL," +
                                "  MESES_CONTRATADOS INTEGER," +
                                "  FOREIGN KEY (ID) REFERENCES VEHICULO(ID)" +
                                ")"
                );
                break;
        }

        return ddl;
    }

    // =========================================================
    // Utilidad: comprobar si existe una tabla vía metadatos
    // =========================================================
    private boolean tablaExiste(Connection conn, String esquema, String nombreTabla) throws SQLException {
        DatabaseMetaData md = conn.getMetaData();
        try (ResultSet rs = md.getTables(null, esquema, nombreTabla, new String[]{"TABLE"})) {
            return rs.next();
        }
    }

}
