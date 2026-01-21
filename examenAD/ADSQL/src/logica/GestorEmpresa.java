package logica;

import clases.*;
import persistencia.EmpresaService;
import util.TipoSGBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

                String createSQLite = "CREATE TABLE FAMILIAR_EMPREGADO (" + "  NSS_EMPREGADO TEXT NOT NULL," + "  NUM_FAMILIAR INTEGER NOT NULL," + "  NSS_FAMILIAR TEXT NOT NULL," + "  NOME TEXT NOT NULL," + "  APELIDO1 TEXT," + "  APELIDO2 TEXT," + "  DATANACEMENTO DATE," + "  PARENTESCO TEXT," + "  SEXO TEXT NOT NULL DEFAULT 'M'," +

                        // PK compuesta
                        "  PRIMARY KEY (NSS_EMPREGADO, NUM_FAMILIAR)," +

                        // UNIQUE
                        "  UNIQUE (NSS_FAMILIAR)," +

                        // CHECK
                        "  CHECK (SEXO IN ('H','M'))," +

                        // FK
                        "  FOREIGN KEY (NSS_EMPREGADO) REFERENCES EMPREGADO(NSS)" + ")";

                System.out.println("Creando tabla FAMILIAR_EMPREGADO (SQLite)...");
                st.executeUpdate(createSQLite);

            } else {

                // ===== MySQL / SQL Server =====
                String createTable = "CREATE TABLE FAMILIAR_EMPREGADO (" + "  NSS_EMPREGADO VARCHAR(15) NOT NULL," + "  NUM_FAMILIAR INT NOT NULL," + "  NSS_FAMILIAR VARCHAR(15) NOT NULL," + "  NOME VARCHAR(25) NOT NULL," + "  APELIDO1 VARCHAR(25)," + "  APELIDO2 VARCHAR(25)," + "  DATANACEMENTO DATE," + "  PARENTESCO VARCHAR(20)," + "  SEXO CHAR(1) DEFAULT 'M' NOT NULL," + "  CONSTRAINT PK_FAMILIAR_EMP PRIMARY KEY (NSS_EMPREGADO, NUM_FAMILIAR)" + ")";

                System.out.println("Creando tabla FAMILIAR_EMPREGADO...");
                st.executeUpdate(createTable);

                st.executeUpdate("ALTER TABLE FAMILIAR_EMPREGADO " + "ADD CONSTRAINT UQ_FAMILIAR_NSS UNIQUE (NSS_FAMILIAR)");

                st.executeUpdate("ALTER TABLE FAMILIAR_EMPREGADO " + "ADD CONSTRAINT CK_FAMILIAR_SEXO CHECK (SEXO IN ('H','M'))");

                st.executeUpdate("ALTER TABLE FAMILIAR_EMPREGADO " + "ADD CONSTRAINT FK_FAMILIAR_EMPREGADO " + "FOREIGN KEY (NSS_EMPREGADO) REFERENCES EMPREGADO(NSS)");
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

                ddl.add("CREATE TABLE VEHICULO (" + "  ID INT AUTO_INCREMENT NOT NULL," + "  MATRICULA VARCHAR(10) NOT NULL," + "  MARCA VARCHAR(25) NOT NULL," + "  MODELO VARCHAR(25) NOT NULL," + "  TIPO_COMBUSTIBLE VARCHAR(15) NOT NULL," + "  TIPO_VEHICULO CHAR(1) NOT NULL," +     // 'P' propio, 'R' renting
                        "  CONSTRAINT PK_VEHICULO PRIMARY KEY (ID)," + "  CONSTRAINT UQ_VEHICULO_MATRICULA UNIQUE (MATRICULA)," + "  CONSTRAINT CK_VEHICULO_TIPO CHECK (TIPO_VEHICULO IN ('P','R'))" + ") ENGINE=InnoDB");

                ddl.add("CREATE TABLE VEHICULO_PROPIO (" + "  ID INT NOT NULL," + "  DATA_COMPRA DATE," + "  PREZO_COMPRA DECIMAL(10,2)," + "  CONSTRAINT PK_VEH_PROPIO PRIMARY KEY (ID)," + "  CONSTRAINT FK_VEH_PROPIO_VEHICULO FOREIGN KEY (ID) REFERENCES VEHICULO(ID)" + ") ENGINE=InnoDB");

                ddl.add("CREATE TABLE VEHICULO_RENTING (" + "  ID INT NOT NULL," + "  DATA_INICIO DATE," + "  PREZO_MENSUAL DECIMAL(10,2)," + "  MESES_CONTRATADOS INT," + "  CONSTRAINT PK_VEH_RENTING PRIMARY KEY (ID)," + "  CONSTRAINT FK_VEH_RENTING_VEHICULO FOREIGN KEY (ID) REFERENCES VEHICULO(ID)" + ") ENGINE=InnoDB");
                break;

            case SQLSERVER:
                ddl.add("IF OBJECT_ID('VEHICULO_RENTING','U') IS NOT NULL DROP TABLE VEHICULO_RENTING");
                ddl.add("IF OBJECT_ID('VEHICULO_PROPIO','U') IS NOT NULL DROP TABLE VEHICULO_PROPIO");
                ddl.add("IF OBJECT_ID('VEHICULO','U') IS NOT NULL DROP TABLE VEHICULO");

                ddl.add("CREATE TABLE VEHICULO (" + "  ID INT IDENTITY(1,1) NOT NULL," + "  MATRICULA VARCHAR(10) NOT NULL," + "  MARCA VARCHAR(25) NOT NULL," + "  MODELO VARCHAR(25) NOT NULL," + "  TIPO_COMBUSTIBLE VARCHAR(15) NOT NULL," + "  TIPO_VEHICULO CHAR(1) NOT NULL," + "  CONSTRAINT PK_VEHICULO PRIMARY KEY (ID)," + "  CONSTRAINT UQ_VEHICULO_MATRICULA UNIQUE (MATRICULA)," + "  CONSTRAINT CK_VEHICULO_TIPO CHECK (TIPO_VEHICULO IN ('P','R'))" + ")");

                ddl.add("CREATE TABLE VEHICULO_PROPIO (" + "  ID INT NOT NULL," + "  DATA_COMPRA DATE," + "  PREZO_COMPRA DECIMAL(10,2)," + "  CONSTRAINT PK_VEH_PROPIO PRIMARY KEY (ID)," + "  CONSTRAINT FK_VEH_PROPIO_VEHICULO FOREIGN KEY (ID) REFERENCES VEHICULO(ID)" + ")");

                ddl.add("CREATE TABLE VEHICULO_RENTING (" + "  ID INT NOT NULL," + "  DATA_INICIO DATE," + "  PREZO_MENSUAL DECIMAL(10,2)," + "  MESES_CONTRATADOS INT," + "  CONSTRAINT PK_VEH_RENTING PRIMARY KEY (ID)," + "  CONSTRAINT FK_VEH_RENTING_VEHICULO FOREIGN KEY (ID) REFERENCES VEHICULO(ID)" + ")");
                break;

            case SQLITE:
                // En SQLite las FKs deben ir en el CREATE TABLE. :contentReference[oaicite:5]{index=5}
                ddl.add("DROP TABLE IF EXISTS VEHICULO_RENTING");
                ddl.add("DROP TABLE IF EXISTS VEHICULO_PROPIO");
                ddl.add("DROP TABLE IF EXISTS VEHICULO");

                ddl.add("PRAGMA foreign_keys = ON");

                ddl.add("CREATE TABLE VEHICULO (" + "  ID INTEGER PRIMARY KEY AUTOINCREMENT," + "  MATRICULA TEXT NOT NULL UNIQUE," + "  MARCA TEXT NOT NULL," + "  MODELO TEXT NOT NULL," + "  TIPO_COMBUSTIBLE TEXT NOT NULL," + "  TIPO_VEHICULO TEXT NOT NULL," + "  CONSTRAINT CK_VEHICULO_TIPO CHECK (TIPO_VEHICULO IN ('P','R'))" + ")");

                ddl.add("CREATE TABLE VEHICULO_PROPIO (" + "  ID INTEGER NOT NULL PRIMARY KEY," + "  DATA_COMPRA DATE," + "  PREZO_COMPRA REAL," + "  FOREIGN KEY (ID) REFERENCES VEHICULO(ID)" + ")");

                ddl.add("CREATE TABLE VEHICULO_RENTING (" + "  ID INTEGER NOT NULL PRIMARY KEY," + "  DATA_INICIO DATE," + "  PREZO_MENSUAL REAL," + "  MESES_CONTRATADOS INTEGER," + "  FOREIGN KEY (ID) REFERENCES VEHICULO(ID)" + ")");
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

    // =========================================================
    // ACTUALIZACIONES - EJ1 (Lógica)
    // =========================================================
    public int insertarFamiliar(Connection conn, Familiar f) {
        try {
            // 1) no permitir duplicado (mismo familiar para mismo empleado)
            if (EmpresaService.existeFamiliarParaEmpleado(conn, f.getNssEmpregado(), f.getNssFamiliar())) {
                System.out.println("ERROR: Ese familiar ya está registrado para ese empleado.");
                return 0;
            }

            // 2) num familiar secuencial por empleado
            int siguiente = EmpresaService.siguienteNumFamiliar(conn, f.getNssEmpregado());
            f.setNumFamiliar(siguiente);

            // 3) insertar
            int filas = EmpresaService.insertarFamiliar(conn, f);
            if (filas > 0) {
                System.out.println("Familiar insertado correctamente. NumFamiliar = " + f.getNumFamiliar());
            }
            return filas;

        } catch (SQLException e) {
            System.out.println("ERROR SQL insertando familiar: " + e.getMessage());
            return 0;
        }
    }

    // =========================================================
    // ACTUALIZACIONES - EJ2 (Lógica)
    // =========================================================
    public boolean insertarVehiculo(Connection conn, Vehiculo v) {
        boolean ok = false;
        boolean autoCommitPrevio = true;

        try {
            // matrícula única
            if (EmpresaService.existeMatricula(conn, v.getMatricula())) {
                System.out.println("ERROR: La matrícula ya existe: " + v.getMatricula());
                return false;
            }

            autoCommitPrevio = conn.getAutoCommit();
            conn.setAutoCommit(false);

            char tipo;
            if (v instanceof VehiculoPropio) tipo = 'P';
            else if (v instanceof VehiculoRenting) tipo = 'R';
            else {
                System.out.println("ERROR: El vehículo debe ser Propio o Renting.");
                return false;
            }

            int id = EmpresaService.insertarVehiculoCabecera(conn, v, tipo);
            if (id <= 0) {
                System.out.println("ERROR: No se pudo obtener el ID del vehículo insertado.");
                conn.rollback();
                return false;
            }

            int filasDetalle;
            if (v instanceof VehiculoPropio vp) {
                filasDetalle = EmpresaService.insertarVehiculoPropio(conn, id, vp);
            } else {
                VehiculoRenting vr = (VehiculoRenting) v;
                filasDetalle = EmpresaService.insertarVehiculoRenting(conn, id, vr);
            }

            if (filasDetalle <= 0) {
                System.out.println("ERROR: No se insertó el detalle del vehículo.");
                conn.rollback();
                return false;
            }

            conn.commit();
            ok = true;
            System.out.println("Vehículo insertado OK. ID = " + id);

        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ignore) {}
            System.out.println("ERROR SQL insertando vehículo (rollback): " + e.getMessage());
        } finally {
            try { conn.setAutoCommit(autoCommitPrevio); } catch (SQLException ignore) {}
        }

        return ok;
    }

    /**
     * EJERCICIO 3
     * Cambiar el departamento que controla un proyecto
     */
    public int cambiarDepartamentoProyecto(Connection conn, String nomDep, String nomProx) {
        try {
            Integer numDep = EmpresaService.getNumDepartamentoPorNombre(conn, nomDep);
            if (numDep == null) {
                System.out.println("ERROR: El departamento no existe: " + nomDep);
                return 0;
            }

            if (!EmpresaService.existeProyectoPorNombre(conn, nomProx)) {
                System.out.println("ERROR: El proyecto no existe: " + nomProx);
                return 0;
            }

            int filas = EmpresaService.actualizarDepartamentoControlaProyecto(conn, numDep, nomProx);
            if (filas > 0) {
                System.out.println("Departamento controlador actualizado correctamente.");
            } else {
                System.out.println("No se realizó ningún cambio.");
            }

            return filas;

        } catch (SQLException e) {
            System.out.println("ERROR SQL en Ejercicio 3: " + e.getMessage());
            return 0;
        }
    }

    /**
     * EJERCICIO 4
     * Eliminar un proyecto mostrando sus datos y empleados asignados
     * (operación controlada con transacción)
     */
    public boolean eliminarProyecto(Connection conn, int numProx) {
        boolean autoCommitPrevio = true;

        try {
            Proxecto p = EmpresaService.getProyectoPorNumero(conn, numProx);
            if (p == null) {
                System.out.println("ERROR: No existe el proyecto con número " + numProx);
                return false;
            }

            System.out.println("Proyecto a eliminar:");
            System.out.println(p);

            List<String> empleados = EmpresaService.getEmpleadosAsignadosAProyecto(conn, numProx);
            System.out.println("Empleados asignados al proyecto:");
            if (empleados.isEmpty()) {
                System.out.println("(ninguno)");
            } else {
                empleados.forEach(System.out::println);
            }

            autoCommitPrevio = conn.getAutoCommit();
            conn.setAutoCommit(false);

            // Borramos primero asignaciones (si hay FK)
            EmpresaService.borrarAsignacionesProyecto(conn, numProx);

            int filas = EmpresaService.borrarProyecto(conn, numProx);
            if (filas <= 0) {
                conn.rollback();
                System.out.println("ERROR: No se pudo borrar el proyecto.");
                return false;
            }

            conn.commit();
            System.out.println("Proyecto eliminado correctamente.");
            return true;

        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ignore) {}
            System.out.println("ERROR SQL en Ejercicio 4 (rollback): " + e.getMessage());
            return false;

        } finally {
            try { conn.setAutoCommit(autoCommitPrevio); } catch (SQLException ignore) {}
        }
    }

    /**
     * EJERCICIO 5
     * Incrementar salario de empleados fijos usando batch + transacción
     */
    public int incrementarSalarioFijosBatch(Connection conn, double incremento, List<String> nssList) {
        boolean autoCommitPrevio = true;

        if (nssList == null || nssList.isEmpty()) {
            System.out.println("ERROR: La lista de NSS está vacía.");
            return 0;
        }

        try (PreparedStatement ps = EmpresaService.prepararStatementIncrementoSalarioFijos(conn)) {

            autoCommitPrevio = conn.getAutoCommit();
            conn.setAutoCommit(false);

            for (String nss : nssList) {
                ps.setDouble(1, incremento);
                ps.setString(2, nss);
                ps.addBatch();
            }

            int[] res = ps.executeBatch();

            int actualizados = 0;
            for (int r : res) {
                if (r > 0) actualizados += r;
                else if (r == Statement.SUCCESS_NO_INFO) actualizados += 1;
            }

            conn.commit();
            System.out.println("Incremento realizado correctamente. Empleados actualizados: " + actualizados);
            return actualizados;

        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ignore) {}
            System.out.println("ERROR SQL en Ejercicio 5 (rollback): " + e.getMessage());
            return 0;

        } finally {
            try { conn.setAutoCommit(autoCommitPrevio); } catch (SQLException ignore) {}
        }
    }

    /**
     * EJERCICIO 6
     * Insertar un proyecto usando ResultSet dinámico
     */
    public boolean insertarProyectoResultSet(Connection conn, Proxecto p) {
        try {
            if (EmpresaService.existeProyectoPorNumeroONombre(
                    conn, p.getNumProxecto(), p.getNomeProxecto())) {
                System.out.println("ERROR: Ya existe un proyecto con ese número o nombre.");
                return false;
            }

            if (!EmpresaService.existeDepartamentoPorNumero(conn, p.getNumDepartControla())) {
                System.out.println("ERROR: No existe el departamento " + p.getNumDepartControla());
                return false;
            }

            EmpresaService.insertarProyectoConResultSet(conn, p);
            System.out.println("Proyecto insertado correctamente (ResultSet dinámico).");
            return true;

        } catch (SQLException e) {
            System.out.println("ERROR SQL en Ejercicio 6: " + e.getMessage());
            return false;
        }
    }

    /**
     * EJERCICIO 7
     * Incrementar salario de empleados de un departamento
     * usando ResultSet dinámico y transacción
     */
    public int incrementarSalariosDepartamentoResultSet(Connection conn, double incremento, int numDep) {
        boolean autoCommitPrevio = true;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            autoCommitPrevio = conn.getAutoCommit();
            conn.setAutoCommit(false);

            rs = EmpresaService.getEmpleadosFijosDeDepartamentoUpdatable(conn, numDep);
            ps = (PreparedStatement) rs.getStatement();

            int afectados = 0;
            while (rs.next()) {
                double salarioActual = rs.getDouble("Salario");
                rs.updateDouble("Salario", salarioActual + incremento);
                rs.updateRow();
                afectados++;
            }

            conn.commit();
            System.out.println("Incremento aplicado correctamente. Empleados afectados: " + afectados);
            return afectados;

        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ignore) {}
            System.out.println("ERROR SQL en Ejercicio 7 (rollback): " + e.getMessage());
            return 0;

        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException ignore) {}
            try { if (ps != null) ps.close(); } catch (SQLException ignore) {}
            try { conn.setAutoCommit(autoCommitPrevio); } catch (SQLException ignore) {}
        }
    }

    /**
     * EJERCICIO 8
     * Empleados con más de N proyectos.
     * ResultSet scrollable y de solo lectura
     */
    public void empleadosConMasDeNProyectos(Connection conn, int n) {
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            rs = EmpresaService.getEmpleadosConMasDeNProyectos(conn, n);
            ps = (PreparedStatement) rs.getStatement();

            if (!rs.next()) {
                System.out.println("No hay empleados con más de " + n + " proyectos.");
                return;
            }

            // 1) Primera fila
            rs.first();
            System.out.println("Primera fila: " + filaEmpleadoEj8(rs));

            // 2) Última fila
            rs.last();
            System.out.println("Última fila: " + filaEmpleadoEj8(rs));

            // 3) Antepenúltima fila
            int ultima = rs.getRow();
            if (ultima >= 3) {
                rs.absolute(ultima - 2);
                System.out.println("Antepenúltima fila: " + filaEmpleadoEj8(rs));
            } else {
                System.out.println("No hay antepenúltima (menos de 3 filas).");
            }

            // 4) Recorrido inverso
            System.out.println("Recorrido inverso (de última a primera):");
            rs.last();
            do {
                System.out.println(filaEmpleadoEj8(rs));
            } while (rs.previous());

        } catch (SQLException e) {
            System.out.println("ERROR SQL en Ejercicio 8: " + e.getMessage());

        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException ignore) {}
            try { if (ps != null) ps.close(); } catch (SQLException ignore) {}
        }
    }

    /**
     * Método auxiliar para Ejercicio 8
     */
    private String filaEmpleadoEj8(ResultSet rs) throws SQLException {
        return rs.getString("NSS") + " | " +
                rs.getString("NomeCompleto") + " | " +
                rs.getString("Localidade") + " | Salario=" +
                rs.getDouble("Salario");
    }

    // ================= CONSULTAS =================

    public List<Departamento> getDepartamentosConProyectosAsignados(Connection conn) throws SQLException {
        EmpresaService es = new EmpresaService();
        return es.departamentosProyectosAsignados(conn);
    }

    public List<Empregado> getDirectoresDeDepsConProyectosAsignados(Connection conn) throws SQLException {
        EmpresaService es = new EmpresaService();
        return es.directoresDepProAsignados(conn);
    }

    public List<Empregado> getListaEmpregados(Connection conn) throws SQLException {
        EmpresaService es = new EmpresaService();
        return es.listarEmpregados(conn);
    }

    public List<Empregado> getEmpregadosDeDepartamento(Connection conn, String nomDep) throws SQLException {
        EmpresaService es = new EmpresaService();

        if (!es.existeDepartamento(conn, nomDep)) {
            throw new IllegalArgumentException("El departamento no existe");
        }
        return es.listarEmpregadosDepartamentos(conn, nomDep);
    }

    public List<Empregado> getEmpregadosFixosProxectoLocalidade(
            Connection conn, String nomProx, String nomLoc) throws SQLException {

        EmpresaService es = new EmpresaService();

        if (!es.existeProxecto(conn, nomProx)) {
            throw new IllegalArgumentException("El proyecto no existe");
        }
        if (!EmpresaService.existeLocalidade(conn, nomLoc)) {
            throw new IllegalArgumentException("La localidad no existe");
        }
        return EmpresaService.listarEmpregadosFixosLocalidade(conn, nomProx, nomLoc);
    }

    public Map<String, List<Empregado>> getEmpregadosAgrupadosPorDepartamento(Connection conn) throws SQLException, ClassNotFoundException {
        EmpresaService es = new EmpresaService();
        Map<String, List<Empregado>> res = new HashMap<>();

        List<String> deps = EmpresaService.listaDepartamentos(conn);
        for (String d : deps) {
            res.put(d, es.listarEmpregadosDepartamentos(conn, d));
        }
        return res;
    }

    public List<DepartamentoNumEmpleados> getDepartamentosConMasDeNEmpleados(Connection conn, int n) throws SQLException {
        return EmpresaService.listaDepartamentosNumEmpleados(conn, n);
    }

    public List<Empregado> getEmpregadosFixosConSalarioMayor(Connection conn, int salarioMin) throws SQLException {
        EmpresaService es = new EmpresaService();
        return es.listarEmpregadosFixosSalario(conn, salarioMin);
    }

    public List<Empregado> getMaxSalarioFijosPorDepartamentoDesdeUltimo(Connection conn) throws SQLException {
        EmpresaService es = new EmpresaService();
        return es.listarEmpregadosFixosSalarioMaxScroll(conn);
    }

    public List<DepartamentoNumProyectos> getDepartamentosConMaxNumProyectos(Connection conn) throws SQLException {
        return EmpresaService.listaDepartamentosMaxProyectos(conn);
    }



}
