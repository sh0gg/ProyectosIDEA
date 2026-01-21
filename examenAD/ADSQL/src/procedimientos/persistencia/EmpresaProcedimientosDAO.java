package procedimientos.persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaProcedimientosDAO {

    // EJ1: UPDATE por procedimiento
    public static int prCambioDomicilio(Connection conn,
                                        String nss, String rua, int numero, String piso,
                                        String codPostal, String localidade) throws SQLException {
        String call = "{call dbo.pr_CambioDomicilio(?,?,?,?,?,?)}";
        try (CallableStatement cs = conn.prepareCall(call)) {
            cs.setString(1, nss);
            cs.setString(2, rua);
            cs.setInt(3, numero);
            cs.setString(4, piso);
            cs.setString(5, codPostal);
            cs.setString(6, localidade);
            return cs.executeUpdate();
        }
    }

    // EJ2: OUT params
    public static class DatosProxecto {
        private final String nome;
        private final String lugar;
        private final String departamento;

        public DatosProxecto(String nome, String lugar, String departamento) {
            this.nome = nome;
            this.lugar = lugar;
            this.departamento = departamento;
        }

        public String getNome() { return nome; }
        public String getLugar() { return lugar; }
        public String getDepartamento() { return departamento; }
    }

    public static DatosProxecto prDatosProxectos(Connection conn, int numProx) throws SQLException {
        String call = "{call dbo.pr_DatosProxectos(?,?,?,?)}";
        try (CallableStatement cs = conn.prepareCall(call)) {

            cs.setInt(1, numProx);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.registerOutParameter(4, Types.VARCHAR);

            cs.execute();

            String nome = cs.getString(2);
            String lugar = cs.getString(3);
            String dep = cs.getString(4);

            // Si no encontró fila, normalmente quedarán null
            if (nome == null && lugar == null && dep == null) return null;

            return new DatosProxecto(nome, lugar, dep);
        }
    }

    // EJ3: devuelve ResultSet con execute()
    public static List<String> prDepartControlaProxec(Connection conn, int minProx) throws SQLException {
        String call = "{call dbo.pr_DepartControlaProxec(?)}";
        try (CallableStatement cs = conn.prepareCall(call)) {

            cs.setInt(1, minProx);

            boolean hasRs = cs.execute();
            List<String> out = new ArrayList<>();

            if (hasRs) {
                try (ResultSet rs = cs.getResultSet()) {
                    while (rs.next()) {
                        int numDep = rs.getInt("NumDepartamento");
                        String nomeDep = rs.getString("NomeDepartamento");
                        int numProxectos = rs.getInt("NumProxectos");
                        out.add("[" + numDep + "] " + nomeDep + " -> " + numProxectos + " proxectos");
                    }
                }
            } else {
                out.add("No devolveu ResultSet. UpdateCount=" + cs.getUpdateCount());
            }

            return out;
        }
    }

    // EJ4: función
    public static int fnNEmpDepart(Connection conn, String nomeDep) throws SQLException {
        String call = "{?=call dbo.fn_nEmpDepart(?)}";
        try (CallableStatement cs = conn.prepareCall(call)) {
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setString(2, nomeDep);
            cs.execute();
            return cs.getInt(1);
        }
    }
}
