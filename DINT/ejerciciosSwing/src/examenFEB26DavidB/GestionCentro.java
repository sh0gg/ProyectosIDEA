package examenFEB26DavidB;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionCentro {

    private JFrame frame;
    private JTextField tf_alumno_nombre;
    private JTextField tf_alumno_dni;
    private JTextField tf_alumno_apellido;
    private JTextField tf_curso_id;
    private JTextField tf_curso_nombre;
    private JTextField tf_curso_sesiones;
    private JTextField tf_nota_ev1;
    private JTextField tf_nota_ev2;
    private JTextField tf_nota_ev3;
    private JTextField tf_faltas;

    private JComboBox<String> cb_curso_id_asignar;
    private JComboBox<String> cb_dni_asignar;
    private JComboBox<String> cb_curso_id_evaluar;
    private JComboBox<String> cb_dni_evaluar;

    private JTable table_cursos;
    private JTable table_alumnos;
    private JTable table_curso_alumno;

    private javax.swing.table.DefaultTableModel modelCursos;
    private javax.swing.table.DefaultTableModel modelAlumnos;
    private javax.swing.table.DefaultTableModel modelCursoAlumno;

    
    
    // --- Conexión a la base de datos ---
    private Connection getConnection() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/examen_final_dint_centro",
                "jasper", // usuario
                "abc123."      // contraseña
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GestionCentro window = new GestionCentro();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GestionCentro() {
        initialize();
        cargarCombos(); // Cargar combos al iniciar
        actualizarTablas(); // Actualizar tablas al iniciar
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1136, 587);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // --- Labels y TextFields ---
        JLabel lblCurso = new JLabel("Registro de cursos");
        lblCurso.setBounds(12, 33, 186, 17);
        frame.getContentPane().add(lblCurso);

        JLabel lblAlumno = new JLabel("Registro de alumnos");
        lblAlumno.setBounds(12, 123, 186, 17);
        frame.getContentPane().add(lblAlumno);

        JLabel lb_curso_id = new JLabel("ID");
        lb_curso_id.setBounds(12, 54, 88, 17);
        frame.getContentPane().add(lb_curso_id);

        tf_curso_id = new JTextField();
        tf_curso_id.setBounds(106, 52, 114, 21);
        frame.getContentPane().add(tf_curso_id);

        JLabel lb_curso_nombre = new JLabel("Nombre");
        lb_curso_nombre.setBounds(12, 74, 88, 17);
        frame.getContentPane().add(lb_curso_nombre);

        tf_curso_nombre = new JTextField();
        tf_curso_nombre.setBounds(106, 72, 114, 21);
        frame.getContentPane().add(tf_curso_nombre);

        JLabel lb_curso_sesiones = new JLabel("Nº sesiones");
        lb_curso_sesiones.setBounds(12, 94, 88, 17);
        frame.getContentPane().add(lb_curso_sesiones);

        tf_curso_sesiones = new JTextField();
        tf_curso_sesiones.setBounds(106, 92, 114, 21);
        frame.getContentPane().add(tf_curso_sesiones);

        JLabel lb_alumno_dni = new JLabel("DNI");
        lb_alumno_dni.setBounds(12, 150, 88, 17);
        frame.getContentPane().add(lb_alumno_dni);

        tf_alumno_dni = new JTextField();
        tf_alumno_dni.setBounds(106, 148, 114, 21);
        frame.getContentPane().add(tf_alumno_dni);

        JLabel lb_alumno_nombre = new JLabel("Nombre");
        lb_alumno_nombre.setBounds(12, 170, 88, 17);
        frame.getContentPane().add(lb_alumno_nombre);

        tf_alumno_nombre = new JTextField();
        tf_alumno_nombre.setBounds(106, 168, 114, 21);
        frame.getContentPane().add(tf_alumno_nombre);

        JLabel lb_alumno_apellido = new JLabel("Apellidos");
        lb_alumno_apellido.setBounds(12, 190, 88, 17);
        frame.getContentPane().add(lb_alumno_apellido);

        tf_alumno_apellido = new JTextField();
        tf_alumno_apellido.setBounds(106, 188, 114, 21);
        frame.getContentPane().add(tf_alumno_apellido);

        JLabel lblAsignar = new JLabel("Asignar curso / alumno");
        lblAsignar.setBounds(12, 240, 149, 17);
        frame.getContentPane().add(lblAsignar);

        JLabel lb_curso_asignar = new JLabel("Curso ID");
        lb_curso_asignar.setBounds(12, 264, 88, 17);
        frame.getContentPane().add(lb_curso_asignar);

        cb_curso_id_asignar = new JComboBox<>();
        cb_curso_id_asignar.setBounds(106, 259, 115, 26);
        frame.getContentPane().add(cb_curso_id_asignar);

        JLabel lb_dni_asignar = new JLabel("DNI");
        lb_dni_asignar.setBounds(12, 293, 88, 17);
        frame.getContentPane().add(lb_dni_asignar);

        cb_dni_asignar = new JComboBox<>();
        cb_dni_asignar.setBounds(106, 286, 114, 26);
        frame.getContentPane().add(cb_dni_asignar);

        JLabel lblEvaluar = new JLabel("Evaluar alumno");
        lblEvaluar.setBounds(12, 336, 149, 17);
        frame.getContentPane().add(lblEvaluar);

        JLabel lb_curso_evaluar = new JLabel("Curso ID");
        lb_curso_evaluar.setBounds(12, 362, 88, 17);
        frame.getContentPane().add(lb_curso_evaluar);

        cb_curso_id_evaluar = new JComboBox<>();
        cb_curso_id_evaluar.setBounds(106, 357, 115, 26);
        frame.getContentPane().add(cb_curso_id_evaluar);

        JLabel lb_dni_evaluar = new JLabel("DNI");
        lb_dni_evaluar.setBounds(12, 391, 88, 17);
        frame.getContentPane().add(lb_dni_evaluar);

        cb_dni_evaluar = new JComboBox<>();
        cb_dni_evaluar.setBounds(106, 384, 114, 26);
        frame.getContentPane().add(cb_dni_evaluar);

        JLabel lb_nota_ev1 = new JLabel("Nota EV1");
        lb_nota_ev1.setBounds(12, 422, 88, 17);
        frame.getContentPane().add(lb_nota_ev1);

        tf_nota_ev1 = new JTextField();
        tf_nota_ev1.setBounds(106, 420, 114, 21);
        frame.getContentPane().add(tf_nota_ev1);

        JLabel lb_nota_ev2 = new JLabel("Nota EV2");
        lb_nota_ev2.setBounds(12, 442, 88, 17);
        frame.getContentPane().add(lb_nota_ev2);

        tf_nota_ev2 = new JTextField();
        tf_nota_ev2.setBounds(106, 440, 114, 21);
        frame.getContentPane().add(tf_nota_ev2);

        JLabel lb_nota_ev3 = new JLabel("Nota EV3");
        lb_nota_ev3.setBounds(12, 462, 88, 17);
        frame.getContentPane().add(lb_nota_ev3);

        tf_nota_ev3 = new JTextField();
        tf_nota_ev3.setBounds(106, 460, 114, 21);
        frame.getContentPane().add(tf_nota_ev3);

        JLabel lb_faltas = new JLabel("Num. faltas");
        lb_faltas.setBounds(12, 482, 88, 17);
        frame.getContentPane().add(lb_faltas);

        tf_faltas = new JTextField();
        tf_faltas.setBounds(106, 480, 114, 21);
        frame.getContentPane().add(tf_faltas);

        // --- Botones ---
        JButton btn_registrar_curso = new JButton("Registrar Curso");
        btn_registrar_curso.setBounds(232, 49, 167, 62);
        frame.getContentPane().add(btn_registrar_curso);

        JButton btn_registrar_alumno = new JButton("Registrar alumno");
        btn_registrar_alumno.setBounds(232, 145, 167, 62);
        frame.getContentPane().add(btn_registrar_alumno);

        JButton btn_asignar_curso_alumno = new JButton("Asignar curso/alumno");
        btn_asignar_curso_alumno.setBounds(233, 259, 166, 62);
        frame.getContentPane().add(btn_asignar_curso_alumno);

        JButton btn_evaluar_alumno = new JButton("Evaluar alumno");
        btn_evaluar_alumno.setBounds(232, 386, 167, 62);
        frame.getContentPane().add(btn_evaluar_alumno);

        // --- Tablas ---
        JScrollPane scrollCursos = new JScrollPane();
        scrollCursos.setBounds(448, 33, 584, 107);
        frame.getContentPane().add(scrollCursos);

        table_cursos = new JTable();
        modelCursos = new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Nombre", "Nº Sesiones"}
        );
        table_cursos.setModel(modelCursos);
        scrollCursos.setViewportView(table_cursos);

        JScrollPane scrollAlumnos = new JScrollPane();
        scrollAlumnos.setBounds(448, 170, 584, 107);
        frame.getContentPane().add(scrollAlumnos);

        table_alumnos = new JTable();
        modelAlumnos = new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {"DNI", "Nombre", "Apellidos"}
        );
        table_alumnos.setModel(modelAlumnos);
        scrollAlumnos.setViewportView(table_alumnos);

        JScrollPane scrollCursoAlumno = new JScrollPane();
        scrollCursoAlumno.setBounds(448, 308, 584, 224);
        frame.getContentPane().add(scrollCursoAlumno);

        table_curso_alumno = new JTable();
        modelCursoAlumno = new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {"Curso ID", "DNI Alumno", "Nota EV1", "Nota EV2", "Nota EV3", "Faltas"}
        );
        table_curso_alumno.setModel(modelCursoAlumno);
        scrollCursoAlumno.setViewportView(table_curso_alumno);

        // --- Acciones botones ---

        // Registrar Curso
        btn_registrar_curso.addActionListener(e -> {
            String id = tf_curso_id.getText();
            String nombre = tf_curso_nombre.getText();
            int sesiones;
            try {
                sesiones = Integer.parseInt(tf_curso_sesiones.getText());
            } catch (NumberFormatException ex) {
                System.out.println("Número de sesiones inválido");
                return;
            }
            try (Connection con = getConnection()) {
                String sql = "INSERT INTO cursos (id, nombre, num_total_sesiones) VALUES (?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, id);
                pst.setString(2, nombre);
                pst.setInt(3, sesiones);
                pst.executeUpdate();
                System.out.println("Curso registrado correctamente");
                actualizarTablas();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // Registrar Alumno
        btn_registrar_alumno.addActionListener(e -> {
            String dni = tf_alumno_dni.getText();
            String nombre = tf_alumno_nombre.getText();
            String apellidos = tf_alumno_apellido.getText();
            try (Connection con = getConnection()) {
                String sql = "INSERT INTO alumnos (dni, nombre, apellidos) VALUES (?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, dni);
                pst.setString(2, nombre);
                pst.setString(3, apellidos);
                pst.executeUpdate();
                System.out.println("Alumno registrado correctamente");
                actualizarTablas();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // Asignar Curso/Alumno
        btn_asignar_curso_alumno.addActionListener(e -> {
            String cursoId = (String) cb_curso_id_asignar.getSelectedItem();
            String dniAlumno = (String) cb_dni_asignar.getSelectedItem();
            if (cursoId == null || dniAlumno == null) return;
            try (Connection con = getConnection()) {
                String sql = "INSERT INTO curso_alumno (id_curso, dni_alumno) VALUES (?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, cursoId);
                pst.setString(2, dniAlumno);
                pst.executeUpdate();
                System.out.println("Curso asignado correctamente");
                actualizarTablas();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // Evaluar Alumno
        btn_evaluar_alumno.addActionListener(e -> {
            String cursoId = (String) cb_curso_id_evaluar.getSelectedItem();
            String dniAlumno = (String) cb_dni_evaluar.getSelectedItem();
            if (cursoId == null || dniAlumno == null) return;
            int nota1, nota2, nota3, faltas;
            try {
                nota1 = Integer.parseInt(tf_nota_ev1.getText());
                nota2 = Integer.parseInt(tf_nota_ev2.getText());
                nota3 = Integer.parseInt(tf_nota_ev3.getText());
                faltas = Integer.parseInt(tf_faltas.getText());
            } catch (NumberFormatException ex) {
                System.out.println("Datos de evaluación inválidos");
                return;
            }
            try (Connection con = getConnection()) {
                String sql = "UPDATE curso_alumno SET nota_ev1=?, nota_ev2=?, nota_ev3=?, faltas=? WHERE id_curso=? AND dni_alumno=?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, nota1);
                pst.setInt(2, nota2);
                pst.setInt(3, nota3);
                pst.setInt(4, faltas);
                pst.setString(5, cursoId);
                pst.setString(6, dniAlumno);
                pst.executeUpdate();
                System.out.println("Alumno evaluado correctamente");
                actualizarTablas();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // --- Combo curso_id_asignar para filtrar alumnos no asignados ---
        cb_curso_id_asignar.addActionListener(e -> {
            String curso = (String) cb_curso_id_asignar.getSelectedItem();
            if (curso != null) {
                cargarAlumnosNoAsignados(curso);
            }
        });
        
        tf_curso_id.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (tf_curso_id.getText().isEmpty() || tf_curso_nombre.getText().isEmpty() || tf_curso_sesiones.getText().isEmpty()) {
            		btn_registrar_curso.setEnabled(false);
            	} else {
            		btn_registrar_curso.setEnabled(false);
            	}
        	}
        });
    }

    private void actualizarTablas() {
        try (Connection con = getConnection()) {

            // --- Cursos ---
            modelCursos.setRowCount(0);
            ResultSet rsCursos = con.createStatement().executeQuery("SELECT * FROM cursos");
            while (rsCursos.next()) {
                modelCursos.addRow(new Object[] {
                    rsCursos.getString("id"),
                    rsCursos.getString("nombre"),
                    rsCursos.getInt("num_total_sesiones")
                });
            }

            // --- Alumnos ---
            modelAlumnos.setRowCount(0);
            ResultSet rsAlumnos = con.createStatement().executeQuery("SELECT * FROM alumnos");
            while (rsAlumnos.next()) {
                modelAlumnos.addRow(new Object[] {
                    rsAlumnos.getString("dni"),
                    rsAlumnos.getString("nombre"),
                    rsAlumnos.getString("apellidos")
                });
            }

            // --- Curso_Alumno ---
            modelCursoAlumno.setRowCount(0);
            ResultSet rsCA = con.createStatement().executeQuery("SELECT * FROM curso_alumno");
            while (rsCA.next()) {
                modelCursoAlumno.addRow(new Object[] {
                    rsCA.getString("id_curso"),
                    rsCA.getString("dni_alumno"),
                    rsCA.getInt("nota_ev1"),
                    rsCA.getInt("nota_ev2"),
                    rsCA.getInt("nota_ev3"),
                    rsCA.getInt("faltas")
                });
            }

            // Actualizar combos
            cargarCombos();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void cargarCombos() {
        try (Connection con = getConnection()) {
            // Cursos
            ResultSet rsCursos = con.createStatement().executeQuery("SELECT id FROM cursos");
            cb_curso_id_asignar.removeAllItems();
            cb_curso_id_evaluar.removeAllItems();
            while (rsCursos.next()) {
                String id = rsCursos.getString("id");
                cb_curso_id_asignar.addItem(id);
                cb_curso_id_evaluar.addItem(id);
            }

            // Alumnos (evaluar)
            ResultSet rsAlumnos = con.createStatement().executeQuery("SELECT dni FROM alumnos");
            cb_dni_evaluar.removeAllItems();
            while (rsAlumnos.next()) {
                cb_dni_evaluar.addItem(rsAlumnos.getString("dni"));
            }

            // cb_dni_asignar se llena según curso seleccionado
            String curso = (String) cb_curso_id_asignar.getSelectedItem();
            if (curso != null) {
                cargarAlumnosNoAsignados(curso);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void cargarAlumnosNoAsignados(String cursoId) {
        try (Connection con = getConnection()) {
            cb_dni_asignar.removeAllItems();
            String sql = "SELECT dni FROM alumnos WHERE dni NOT IN " +
                         "(SELECT dni_alumno FROM curso_alumno WHERE id_curso = ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cursoId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cb_dni_asignar.addItem(rs.getString("dni"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

