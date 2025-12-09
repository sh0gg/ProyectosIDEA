package logica;

import clases.Departamento;
import clases.Proxecto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Operaciones sencillas sobre BDEMPRESA25:
 * - listar departamentos
 * - insertar proyecto comprobando que el nombre es único :contentReference[oaicite:2]{index=2}
 */
public class EmpresaService {

    public List<Departamento> listarDepartamentos(Connection conn) throws SQLException {
        List<Departamento> lista = new ArrayList<>();

        String sql = "SELECT NumDepartamento, NomeDepartamento, NSSDirector FROM DEPARTAMENTO";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Departamento d = new Departamento(
                        rs.getInt("NumDepartamento"),
                        rs.getString("NomeDepartamento"),
                        rs.getString("NSSDirector")
                );
                lista.add(d);
            }
        }

        return lista;
    }

    public void insertarProxecto(Connection conn, Proxecto p) throws SQLException {
        // 1) Comprobar que el nombre es único
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

        // 2) Insertar
        String sqlInsert = "INSERT INTO PROXECTO (NumProxecto, NomeProxecto, Lugar, NumDepartControla) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sqlInsert)) {
            ps.setInt(1, p.getNumProxecto());
            ps.setString(2, p.getNomeProxecto());
            ps.setString(3, p.getLugar());
            ps.setInt(4, p.getNumDepartControla());

            int filas = ps.executeUpdate();
            System.out.println("Filas insertadas en PROXECTO: " + filas);
        }
    }
}
