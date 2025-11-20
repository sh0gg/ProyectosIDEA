package supabase;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.json.JSONObject;

public class InterfazTrabajadores extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfDNI;
    private JTextField tfNombre;
    private JTextField tfApellido1;
    private JTextField tfApellido2;
    private JTextField tfProfesion;
    private JTable table;
    private JButton bEliminarProvincia;
    private JButton bAddProvincia;
    private JComboBox<String> cbProvincias;
    private DefaultListModel<String> profesionesModel;
    private JList<String> lProfesiones;
    private DefaultTableModel modeloTrabajadores;
    private JButton bAddTrabajador;
    private JButton bEliminarTrabajador;
    private boolean modoOnline = false;
    private JTextArea taDetalle;
    private String emailSesion;
    private String accessToken;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InterfazTrabajadores frame = new InterfazTrabajadores();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public InterfazTrabajadores() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 659, 492);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setToolTipText("");
        setJMenuBar(menuBar);

        JMenu mnSesion = new JMenu("Sesión");
        menuBar.add(mnSesion);

        JMenuItem miDetallesSesion = new JMenuItem("Detalles de sesión");
        miDetallesSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (emailSesion == null || accessToken == null) {
                    JOptionPane.showMessageDialog(
                            InterfazTrabajadores.this,
                            "No hay sesión activa.",
                            "Sesión",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            InterfazTrabajadores.this,
                            "E-mail: " + emailSesion + "\n" +
                                    "Access token:\n" + accessToken,
                            "Detalles de sesión",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });
        mnSesion.add(miDetallesSesion);

        JMenuItem miCerrarSesion = new JMenuItem("Cerrar sesión");
        miCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                emailSesion = null;
                accessToken = null;
                dispose();
                Login login = new Login();
                login.setLocationRelativeTo(null);
                login.setVisible(true);
            }
        });
        mnSesion.add(miCerrarSesion);

        JMenuItem miSalir = new JMenuItem("Salir de la aplicación");
        miSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnSesion.add(miSalir);

        JMenu mnAcciones = new JMenu("Acciones");
        menuBar.add(mnAcciones);

        JMenuItem miValidar = new JMenuItem("Validación datos del trabajador");
        miValidar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validarDatosTrabajador(true, true);
            }
        });
        mnAcciones.add(miValidar);

        JMenuItem miLimpiar = new JMenuItem("Limpiar datos del trabajador");
        miLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarDatosTrabajador();
            }
        });
        mnAcciones.add(miLimpiar);

        JMenuItem miAddTrabajador = new JMenuItem("Añadir trabajador");
        miAddTrabajador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (bAddTrabajador != null) {
                    bAddTrabajador.doClick();
                }
            }
        });
        mnAcciones.add(miAddTrabajador);

        JMenuItem miEliminarTrabajador = new JMenuItem("Eliminar trabajador");
        miEliminarTrabajador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (bEliminarTrabajador != null) {
                    bEliminarTrabajador.doClick();
                }
            }
        });
        mnAcciones.add(miEliminarTrabajador);

        JMenu mnModo = new JMenu("Modo");
        menuBar.add(mnModo);

        JRadioButtonMenuItem rbOffline = new JRadioButtonMenuItem("Offline", true);
        JRadioButtonMenuItem rbOnline = new JRadioButtonMenuItem("Online");

        ButtonGroup bgModo = new ButtonGroup();
        bgModo.add(rbOffline);
        bgModo.add(rbOnline);

        mnModo.add(rbOffline);
        mnModo.add(rbOnline);

        rbOffline.addActionListener(e -> activarModoOffline());
        rbOnline.addActionListener(e -> activarModoOnline());

        getContentPane().setLayout(new GridLayout(0, 2, 0, 0));

        JPanel pRegistro = new JPanel();
        getContentPane().add(pRegistro);
        GridBagLayout gbl_pRegistro = new GridBagLayout();
        gbl_pRegistro.columnWidths = new int[]{274, 0};
        gbl_pRegistro.rowHeights = new int[]{95, 57, 185, 24, 0};
        gbl_pRegistro.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_pRegistro.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        pRegistro.setLayout(gbl_pRegistro);

        JPanel pIDTrabajador = new JPanel();
        pIDTrabajador.setBorder(new TitledBorder(null, "Identificación del trabajador",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GridBagConstraints gbc_pIDTrabajador = new GridBagConstraints();
        gbc_pIDTrabajador.fill = GridBagConstraints.BOTH;
        gbc_pIDTrabajador.insets = new Insets(0, 0, 5, 0);
        gbc_pIDTrabajador.gridx = 0;
        gbc_pIDTrabajador.gridy = 0;
        pRegistro.add(pIDTrabajador, gbc_pIDTrabajador);
        GridBagLayout gbl_pIDTrabajador = new GridBagLayout();
        gbl_pIDTrabajador.columnWidths = new int[]{78, 0, 0};
        gbl_pIDTrabajador.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_pIDTrabajador.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_pIDTrabajador.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        pIDTrabajador.setLayout(gbl_pIDTrabajador);

        JLabel lblDNI = new JLabel("DNI");
        GridBagConstraints gbc_lblDNI = new GridBagConstraints();
        gbc_lblDNI.insets = new Insets(0, 0, 5, 5);
        gbc_lblDNI.gridx = 0;
        gbc_lblDNI.gridy = 0;
        pIDTrabajador.add(lblDNI, gbc_lblDNI);

        tfDNI = new JTextField();
        GridBagConstraints gbc_tfDNI = new GridBagConstraints();
        gbc_tfDNI.insets = new Insets(0, 0, 5, 0);
        gbc_tfDNI.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfDNI.gridx = 1;
        gbc_tfDNI.gridy = 0;
        pIDTrabajador.add(tfDNI, gbc_tfDNI);
        tfDNI.setColumns(10);
        tfDNI.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                actualizarEstadoBotonTrabajador();
            }
        });

        JLabel lblNombre = new JLabel("Nombre");
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombre.gridx = 0;
        gbc_lblNombre.gridy = 1;
        pIDTrabajador.add(lblNombre, gbc_lblNombre);

        tfNombre = new JTextField();
        GridBagConstraints gbc_tfNombre = new GridBagConstraints();
        gbc_tfNombre.insets = new Insets(0, 0, 5, 0);
        gbc_tfNombre.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfNombre.gridx = 1;
        gbc_tfNombre.gridy = 1;
        pIDTrabajador.add(tfNombre, gbc_tfNombre);
        tfNombre.setColumns(10);
        tfNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                actualizarEstadoBotonTrabajador();
            }
        });

        JLabel lblApellido1 = new JLabel("Apellido 1");
        GridBagConstraints gbc_lblApellido1 = new GridBagConstraints();
        gbc_lblApellido1.insets = new Insets(0, 0, 5, 5);
        gbc_lblApellido1.gridx = 0;
        gbc_lblApellido1.gridy = 2;
        pIDTrabajador.add(lblApellido1, gbc_lblApellido1);

        tfApellido1 = new JTextField();
        GridBagConstraints gbc_tfApellido1 = new GridBagConstraints();
        gbc_tfApellido1.insets = new Insets(0, 0, 5, 0);
        gbc_tfApellido1.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfApellido1.gridx = 1;
        gbc_tfApellido1.gridy = 2;
        pIDTrabajador.add(tfApellido1, gbc_tfApellido1);
        tfApellido1.setColumns(10);
        tfApellido1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                actualizarEstadoBotonTrabajador();
            }
        });

        JLabel lblApellido2 = new JLabel("Apellido 2");
        GridBagConstraints gbc_lblApellido2 = new GridBagConstraints();
        gbc_lblApellido2.insets = new Insets(0, 0, 0, 5);
        gbc_lblApellido2.gridx = 0;
        gbc_lblApellido2.gridy = 3;
        pIDTrabajador.add(lblApellido2, gbc_lblApellido2);

        tfApellido2 = new JTextField();
        GridBagConstraints gbc_tfApellido2 = new GridBagConstraints();
        gbc_tfApellido2.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfApellido2.gridx = 1;
        gbc_tfApellido2.gridy = 3;
        pIDTrabajador.add(tfApellido2, gbc_tfApellido2);
        tfApellido2.setColumns(10);
        tfApellido2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                actualizarEstadoBotonTrabajador();
            }
        });

        JPanel pProvincia = new JPanel();
        pProvincia.setBorder(
                new TitledBorder(null, "Provincia del trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GridBagConstraints gbc_pProvincia = new GridBagConstraints();
        gbc_pProvincia.fill = GridBagConstraints.BOTH;
        gbc_pProvincia.insets = new Insets(0, 0, 5, 0);
        gbc_pProvincia.gridx = 0;
        gbc_pProvincia.gridy = 1;
        pRegistro.add(pProvincia, gbc_pProvincia);
        GridBagLayout gbl_pProvincia = new GridBagLayout();
        gbl_pProvincia.columnWidths = new int[]{0, 0, 0};
        gbl_pProvincia.rowHeights = new int[]{0, 0, 0};
        gbl_pProvincia.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
        gbl_pProvincia.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        pProvincia.setLayout(gbl_pProvincia);

        cbProvincias = new JComboBox<>();
        cbProvincias.setEditable(true);
        GridBagConstraints gbc_cbProvincias = new GridBagConstraints();
        gbc_cbProvincias.insets = new Insets(0, 0, 5, 5);
        gbc_cbProvincias.fill = GridBagConstraints.HORIZONTAL;
        gbc_cbProvincias.gridx = 0;
        gbc_cbProvincias.gridy = 0;
        pProvincia.add(cbProvincias, gbc_cbProvincias);
        cbProvincias.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarEstadoBotonTrabajador();
            }
        });

        bEliminarProvincia = new JButton("Eliminar provincia");
        GridBagConstraints gbc_bEliminarProvincia = new GridBagConstraints();
        gbc_bEliminarProvincia.insets = new Insets(0, 0, 5, 0);
        gbc_bEliminarProvincia.gridx = 1;
        gbc_bEliminarProvincia.gridy = 0;
        bEliminarProvincia.setEnabled(false);
        pProvincia.add(bEliminarProvincia, gbc_bEliminarProvincia);

        bEliminarProvincia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String provinciaSeleccionada = (String) cbProvincias.getSelectedItem();
                if (provinciaSeleccionada != null) {

                    if (modoOnline) {
                        String filtro = "provincia=eq." + provinciaSeleccionada;
                        String respuesta = SupabaseDB.delete("Provincias", filtro);
                        if (respuesta == null || respuesta.toLowerCase().contains("error")) {
                            JOptionPane.showMessageDialog(
                                    InterfazTrabajadores.this,
                                    "No se ha podido eliminar la provincia en la base de datos.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }
                    }

                    cbProvincias.removeItem(provinciaSeleccionada);
                    actualizarEstadoBtnEliminarProvincia();
                }
            }
        });

        bAddProvincia = new JButton("Añadir provincia");
        bAddProvincia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DialogoProvincias dialogo = new DialogoProvincias(InterfazTrabajadores.this);
                dialogo.setLocationRelativeTo(InterfazTrabajadores.this);
                dialogo.setVisible(true);
            }
        });
        GridBagConstraints gbc_bAddProvincia = new GridBagConstraints();
        gbc_bAddProvincia.fill = GridBagConstraints.HORIZONTAL;
        gbc_bAddProvincia.gridwidth = 2;
        gbc_bAddProvincia.gridx = 0;
        gbc_bAddProvincia.gridy = 1;
        pProvincia.add(bAddProvincia, gbc_bAddProvincia);

        JPanel pProfesion = new JPanel();
        pProfesion.setBorder(new TitledBorder(null, "Profesión del trabajador", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        GridBagConstraints gbc_pProfesion = new GridBagConstraints();
        gbc_pProfesion.fill = GridBagConstraints.BOTH;
        gbc_pProfesion.insets = new Insets(0, 0, 5, 0);
        gbc_pProfesion.gridx = 0;
        gbc_pProfesion.gridy = 2;
        pRegistro.add(pProfesion, gbc_pProfesion);
        GridBagLayout gbl_pProfesion = new GridBagLayout();
        gbl_pProfesion.columnWidths = new int[]{0, 0, 0};
        gbl_pProfesion.rowHeights = new int[]{0, 0, 0, 0};
        gbl_pProfesion.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
        gbl_pProfesion.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
        pProfesion.setLayout(gbl_pProfesion);

        profesionesModel = new DefaultListModel<>();
        lProfesiones = new JList<>(profesionesModel);
        JScrollPane scrollProfesiones = new JScrollPane(lProfesiones);
        GridBagConstraints gbc_lProfesiones = new GridBagConstraints();
        gbc_lProfesiones.gridwidth = 2;
        gbc_lProfesiones.insets = new Insets(0, 0, 5, 0);
        gbc_lProfesiones.fill = GridBagConstraints.BOTH;
        gbc_lProfesiones.gridx = 0;
        gbc_lProfesiones.gridy = 0;
        pProfesion.add(scrollProfesiones, gbc_lProfesiones);

        lProfesiones.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                actualizarEstadoBotonTrabajador();
            }
        });

        tfProfesion = new JTextField();
        GridBagConstraints gbc_tfProfesion = new GridBagConstraints();
        gbc_tfProfesion.insets = new Insets(0, 0, 0, 5);
        gbc_tfProfesion.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfProfesion.gridx = 0;
        gbc_tfProfesion.gridy = 2;
        pProfesion.add(tfProfesion, gbc_tfProfesion);
        tfProfesion.setColumns(10);

        JButton bAddProfesion = new JButton("Añadir profesión");
        bAddProfesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String profesion = tfProfesion.getText().trim();
                if (profesion.isEmpty() || !profesion.matches(".*[A-Za-z0-9ÁÉÍÓÚáéíóúÑñ].*")) {
                    JOptionPane.showMessageDialog(
                            InterfazTrabajadores.this,
                            "La profesión debe contener algún carácter alfanumérico.",
                            "Profesión no válida",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                DefaultListModel<String> model = (DefaultListModel<String>) lProfesiones.getModel();
                if (model.contains(profesion)) {
                    JOptionPane.showMessageDialog(
                            InterfazTrabajadores.this,
                            "La profesión \"" + profesion + "\" ya está en la lista.",
                            "Profesión duplicada",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                if (modoOnline) {
                    try {
                        JSONObject datos = new JSONObject();
                        datos.put("profesion", profesion);
                        String respuesta = SupabaseDB.insert("Profesiones", datos);
                        if (respuesta == null || respuesta.toLowerCase().contains("error")) {
                            JOptionPane.showMessageDialog(
                                    InterfazTrabajadores.this,
                                    "No se ha podido guardar la profesión en la base de datos.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(
                                InterfazTrabajadores.this,
                                "Error de conexión al guardar la profesión.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                }

                model.addElement(profesion);
                tfProfesion.setText("");
                actualizarEstadoBotonTrabajador();
            }
        });

        GridBagConstraints gbc_bAddProfesion = new GridBagConstraints();
        gbc_bAddProfesion.fill = GridBagConstraints.HORIZONTAL;
        gbc_bAddProfesion.gridx = 1;
        gbc_bAddProfesion.gridy = 2;
        pProfesion.add(bAddProfesion, gbc_bAddProfesion);

        JButton bEliminarProfesion = new JButton("Eliminar profesión");
        bEliminarProfesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idx = lProfesiones.getSelectedIndex();
                if (idx != -1) {
                    String profesion = lProfesiones.getSelectedValue();

                    if (modoOnline && profesion != null) {
                        String filtro = "profesion=eq." + profesion;
                        String respuesta = SupabaseDB.delete("Profesiones", filtro);
                        if (respuesta == null || respuesta.toLowerCase().contains("error")) {
                            JOptionPane.showMessageDialog(
                                    InterfazTrabajadores.this,
                                    "No se ha podido eliminar la profesión en la base de datos.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }
                    }

                    profesionesModel.remove(idx);
                    actualizarEstadoBotonTrabajador();
                }
            }
        });
        GridBagConstraints gbc_bEliminarProfesion = new GridBagConstraints();
        gbc_bEliminarProfesion.fill = GridBagConstraints.HORIZONTAL;
        gbc_bEliminarProfesion.gridwidth = 2;
        gbc_bEliminarProfesion.gridx = 0;
        gbc_bEliminarProfesion.gridy = 1;
        pProfesion.add(bEliminarProfesion, gbc_bEliminarProfesion);

        bAddTrabajador = new JButton("Añadir trabajador");
        bAddTrabajador.setEnabled(false);
        GridBagConstraints gbc_bAddTrabajador = new GridBagConstraints();
        gbc_bAddTrabajador.insets = new Insets(5, 5, 5, 5);
        gbc_bAddTrabajador.fill = GridBagConstraints.BOTH;
        gbc_bAddTrabajador.gridx = 0;
        gbc_bAddTrabajador.gridy = 3;
        pRegistro.add(bAddTrabajador, gbc_bAddTrabajador);

        bAddTrabajador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!validarDatosTrabajador(true, false)) {
                    return;
                }

                String dni = tfDNI.getText().trim().toUpperCase();
                if (existeDNIEnTabla(dni)) {
                    JOptionPane.showMessageDialog(
                            InterfazTrabajadores.this,
                            "Ya existe un trabajador con ese DNI.",
                            "DNI duplicado",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                String nombre = tfNombre.getText().trim();
                String apellido1 = tfApellido1.getText().trim();
                String apellido2 = tfApellido2.getText().trim();
                String nombreCompleto = nombre + " " + apellido1 + " " + apellido2;
                String provincia = (String) cbProvincias.getSelectedItem();
                String profesion = (String) lProfesiones.getSelectedValue();

                if (modoOnline) {
                    try {
                        JSONObject datos = new JSONObject();
                        datos.put("dni", dni);
                        datos.put("nombre", nombre);
                        datos.put("apellido1", apellido1);
                        datos.put("apellido2", apellido2);
                        datos.put("provincia", provincia);
                        datos.put("profesion", profesion);

                        String respuesta = SupabaseDB.insert("Trabajador", datos);
                        if (respuesta == null || respuesta.toLowerCase().contains("error")) {
                            JOptionPane.showMessageDialog(
                                    InterfazTrabajadores.this,
                                    "No se ha podido guardar el trabajador en la base de datos.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(
                                InterfazTrabajadores.this,
                                "Error de conexión al guardar el trabajador en Supabase.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                }

                modeloTrabajadores.addRow(new Object[]{dni, nombreCompleto, provincia, profesion});
                limpiarDatosTrabajador();
            }
        });

        JPanel pDatos = new JPanel();
        getContentPane().add(pDatos);
        GridBagLayout gbl_pDatos = new GridBagLayout();
        gbl_pDatos.columnWidths = new int[]{274, 0};
        gbl_pDatos.rowHeights = new int[]{195, 117, 0};
        gbl_pDatos.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_pDatos.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
        pDatos.setLayout(gbl_pDatos);

        JPanel pTabla = new JPanel();
        pTabla.setBorder(
                new TitledBorder(null, "Trabajadores disponibles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GridBagConstraints gbc_pTabla = new GridBagConstraints();
        gbc_pTabla.fill = GridBagConstraints.BOTH;
        gbc_pTabla.insets = new Insets(0, 0, 5, 0);
        gbc_pTabla.gridx = 0;
        gbc_pTabla.gridy = 0;
        pDatos.add(pTabla, gbc_pTabla);
        GridBagLayout gbl_pTabla = new GridBagLayout();
        gbl_pTabla.columnWidths = new int[]{274, 0};
        gbl_pTabla.rowHeights = new int[]{97, 21, 0};
        gbl_pTabla.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_pTabla.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
        pTabla.setLayout(gbl_pTabla);

        modeloTrabajadores = new DefaultTableModel(
                new Object[]{"DNI", "Nombre completo", "Provincia", "Profesión"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(modeloTrabajadores);

        JScrollPane scrollTabla = new JScrollPane(table);
        GridBagConstraints gbc_table = new GridBagConstraints();
        gbc_table.fill = GridBagConstraints.BOTH;
        gbc_table.insets = new Insets(0, 0, 5, 0);
        gbc_table.gridx = 0;
        gbc_table.gridy = 0;
        pTabla.add(scrollTabla, gbc_table);

        bEliminarTrabajador = new JButton("Eliminar trabajador");
        bEliminarTrabajador.setEnabled(false);
        GridBagConstraints gbc_bEliminarTrabajador = new GridBagConstraints();
        gbc_bEliminarTrabajador.fill = GridBagConstraints.BOTH;
        gbc_bEliminarTrabajador.gridx = 0;
        gbc_bEliminarTrabajador.gridy = 1;
        pTabla.add(bEliminarTrabajador, gbc_bEliminarTrabajador);

        bEliminarTrabajador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    String dni = (String) modeloTrabajadores.getValueAt(row, 0);

                    if (modoOnline) {
                        String filtro = "dni=eq." + dni;
                        String respuesta = SupabaseDB.delete("Trabajador", filtro);
                        if (respuesta == null || respuesta.toLowerCase().contains("error")) {
                            JOptionPane.showMessageDialog(
                                    InterfazTrabajadores.this,
                                    "No se ha podido eliminar el trabajador en la base de datos.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }
                    }

                    modeloTrabajadores.removeRow(row);
                    taDetalle.setText("");
                    bEliminarTrabajador.setEnabled(false);
                }
            }
        });

        JPanel pDatosTrabajador = new JPanel();
        pDatosTrabajador.setBorder(
                new TitledBorder(null, "Detalles del trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GridBagConstraints gbc_pDatosTrabajador = new GridBagConstraints();
        gbc_pDatosTrabajador.fill = GridBagConstraints.BOTH;
        gbc_pDatosTrabajador.gridx = 0;
        gbc_pDatosTrabajador.gridy = 1;
        pDatos.add(pDatosTrabajador, gbc_pDatosTrabajador);
        pDatosTrabajador.setLayout(new GridLayout(0, 1, 0, 0));

        taDetalle = new JTextArea();
        taDetalle.setEditable(false);
        pDatosTrabajador.add(taDetalle);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    bEliminarTrabajador.setEnabled(true);
                    String dni = (String) modeloTrabajadores.getValueAt(row, 0);
                    String nombreCompleto = (String) modeloTrabajadores.getValueAt(row, 1);
                    String provincia = (String) modeloTrabajadores.getValueAt(row, 2);
                    String profesion = (String) modeloTrabajadores.getValueAt(row, 3);
                    taDetalle.setText(
                            "DNI: " + dni + "\n" +
                                    "Nombre: " + nombreCompleto + "\n" +
                                    "Provincia: " + provincia + "\n" +
                                    "Profesión: " + profesion
                    );
                } else {
                    bEliminarTrabajador.setEnabled(false);
                    taDetalle.setText("");
                }
            }
        });
    }


    public void establecerProvincia(String provincia) {
        if (provincia == null || provincia.trim().isEmpty())
            return;
        provincia = provincia.trim();
        boolean provinciaExiste = false;
        for (int i = 0; i < cbProvincias.getItemCount(); i++) {
            if (cbProvincias.getItemAt(i).equals(provincia)) {
                provinciaExiste = true;
                break;
            }
        }
        if (provinciaExiste) {
            JOptionPane.showMessageDialog(this, provincia + " ya está seleccionada");
        } else {
            cbProvincias.addItem(provincia);
            cbProvincias.setSelectedItem(provincia);
            actualizarEstadoBtnEliminarProvincia();

            if (modoOnline) {
                try {
                    JSONObject datos = new JSONObject();
                    datos.put("provincia", provincia);
                    String respuesta = SupabaseDB.insert("Provincias", datos);
                    if (respuesta == null || respuesta.toLowerCase().contains("error")) {
                        JOptionPane.showMessageDialog(
                                this,
                                "No se ha podido guardar la provincia en la base de datos.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                            this,
                            "Error de conexión al guardar la provincia.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    }

    private void actualizarEstadoBtnEliminarProvincia() {
        if (cbProvincias.getItemCount() > 0) {
            bEliminarProvincia.setEnabled(true);
        } else {
            bEliminarProvincia.setEnabled(false);
        }
    }

    private void actualizarEstadoBotonTrabajador() {
        boolean camposTextoOk =
                !tfDNI.getText().trim().isEmpty() &&
                        !tfNombre.getText().trim().isEmpty() &&
                        !tfApellido1.getText().trim().isEmpty() &&
                        !tfApellido2.getText().trim().isEmpty();

        boolean provinciaSeleccionada = cbProvincias.getSelectedItem() != null;
        boolean profesionSeleccionada = (lProfesiones.getSelectedIndex() != -1);

        bAddTrabajador.setEnabled(camposTextoOk && provinciaSeleccionada && profesionSeleccionada);
    }

    private boolean validarDatosTrabajador(boolean mostrarMensajesError, boolean mostrarMensajeCorrecto) {
        String dni = tfDNI.getText().trim().toUpperCase();
        String nombre = tfNombre.getText().trim();
        String apellido1 = tfApellido1.getText().trim();
        String apellido2 = tfApellido2.getText().trim();
        String provincia = (String) cbProvincias.getSelectedItem();
        String profesion = (String) lProfesiones.getSelectedValue();

        if (dni.isEmpty() || nombre.isEmpty() || apellido1.isEmpty() || apellido2.isEmpty()
                || provincia == null || profesion == null) {

            if (mostrarMensajesError) {
                JOptionPane.showMessageDialog(
                        this,
                        "Todos los campos del trabajador deben estar rellenos.",
                        "Datos incompletos",
                        JOptionPane.WARNING_MESSAGE
                );
            }
            return false;
        }

        if (!dni.matches("\\d{8}[A-Z]")) {
            if (mostrarMensajesError) {
                JOptionPane.showMessageDialog(
                        this,
                        "El DNI debe tener 8 dígitos seguidos de una letra mayúscula.",
                        "DNI no válido",
                        JOptionPane.WARNING_MESSAGE
                );
            }
            return false;
        }

        if (!nombre.matches("[A-Za-zÁÉÍÓÚáéíóúÑñ ]+")
                || !apellido1.matches("[A-Za-zÁÉÍÓÚáéíóúÑñ ]+")
                || !apellido2.matches("[A-Za-zÁÉÍÓÚáéíóúÑñ ]+")) {

            if (mostrarMensajesError) {
                JOptionPane.showMessageDialog(
                        this,
                        "Nombre y apellidos solo pueden contener letras y espacios.",
                        "Nombre no válido",
                        JOptionPane.WARNING_MESSAGE
                );
            }
            return false;
        }

        if (mostrarMensajeCorrecto) {
            JOptionPane.showMessageDialog(
                    this,
                    "Los datos del trabajador son correctos.",
                    "Validación correcta",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

        return true;
    }

    private boolean existeDNIEnTabla(String dni) {
        for (int i = 0; i < modeloTrabajadores.getRowCount(); i++) {
            String dniTabla = (String) modeloTrabajadores.getValueAt(i, 0);
            if (dniTabla != null && dniTabla.equalsIgnoreCase(dni)) {
                return true;
            }
        }
        return false;
    }

    private void limpiarDatosTrabajador() {
        tfDNI.setText("");
        tfNombre.setText("");
        tfApellido1.setText("");
        tfApellido2.setText("");
        cbProvincias.setSelectedItem(null);
        lProfesiones.clearSelection();
        taDetalle.setText("");
        actualizarEstadoBotonTrabajador();
    }

    public void setSesion(String email, String accessToken) {
        this.emailSesion = email;
        this.accessToken = accessToken;
        if (email != null && !email.isEmpty()) {
            setTitle("Gestión de trabajadores - " + email);
        }
    }


    private void activarModoOffline() {
        modoOnline = false;
        JOptionPane.showMessageDialog(
                this,
                "Modo OFFLINE activado.\nLos datos solo se guardan en memoria.",
                "Modo offline",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void activarModoOnline() {
        modoOnline = true;
        cargarProvinciasDesdeSupabase();
        cargarProfesionesDesdeSupabase();
        cargarTrabajadoresDesdeSupabase();
        JOptionPane.showMessageDialog(
                this,
                "Modo ONLINE activado.\nDatos sincronizados con Supabase.",
                "Modo online",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void cargarProvinciasDesdeSupabase() {
        String respuesta = SupabaseDB.get("Provincias", null);
        if (respuesta == null || respuesta.toLowerCase().contains("error")) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se han podido cargar las provincias desde Supabase.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        cbProvincias.removeAllItems();
        String[] provincias = extraerCamposMultiples(respuesta, "provincia");
        for (String p : provincias) {
            cbProvincias.addItem(p);
        }
        cbProvincias.setSelectedItem(null);
        actualizarEstadoBtnEliminarProvincia();
    }

    private void cargarProfesionesDesdeSupabase() {
        String respuesta = SupabaseDB.get("Profesiones", null);
        if (respuesta == null || respuesta.toLowerCase().contains("error")) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se han podido cargar las profesiones desde Supabase.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        profesionesModel.clear();
        String[] profesiones = extraerCamposMultiples(respuesta, "profesion");
        for (String p : profesiones) {
            profesionesModel.addElement(p);
        }
    }

    private void cargarTrabajadoresDesdeSupabase() {
        String respuesta = SupabaseDB.get("Trabajador", null);
        if (respuesta == null || respuesta.toLowerCase().contains("error")) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se han podido cargar los trabajadores desde Supabase.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        modeloTrabajadores.setRowCount(0);

        String[] dnis = extraerCamposMultiples(respuesta, "dni");
        String[] nombres = extraerCamposMultiples(respuesta, "nombre");
        String[] ap1 = extraerCamposMultiples(respuesta, "apellido1");
        String[] ap2 = extraerCamposMultiples(respuesta, "apellido2");
        String[] provincias = extraerCamposMultiples(respuesta, "provincia");
        String[] profesiones = extraerCamposMultiples(respuesta, "profesion");

        int filas = dnis.length;
        for (int i = 0; i < filas; i++) {
            String nombreCompleto = nombres[i] + " " + ap1[i] + " " + ap2[i];
            String provincia = (i < provincias.length) ? provincias[i] : "";
            String profesion = (i < profesiones.length) ? profesiones[i] : "";
            modeloTrabajadores.addRow(new Object[]{dnis[i], nombreCompleto, provincia, profesion});
        }
    }


    private String[] extraerCamposMultiples(String json, String campo) {
        java.util.ArrayList<String> lista = new java.util.ArrayList<>();
        if (json == null) return new String[0];

        String pattern = "\"" + campo + "\":\"";
        int index = json.indexOf(pattern);

        while (index != -1) {
            int start = index + pattern.length();
            int end = json.indexOf("\"", start);
            if (end == -1) break;
            String valor = json.substring(start, end);
            lista.add(valor);
            index = json.indexOf(pattern, end);
        }
        return lista.toArray(new String[0]);
    }
}
