package supabase;

import java.awt.EventQueue;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class InterfazTrabajadores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfDNI;
	private JTextField tfNombre;
	private JTextField tfApellido1;
	private JTextField tfApellido2;
	private JTextField tfProfesion;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazTrabajadores frame = new InterfazTrabajadores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfazTrabajadores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		setJMenuBar(menuBar);
		
		JMenu mnSesion = new JMenu("Sesión");
		menuBar.add(mnSesion);
		
		JMenu mnAcciones = new JMenu("Acciones");
		menuBar.add(mnAcciones);
		buttonGroup.add(rdbtnmntmNewRadioItem);
		buttonGroup.add(rdbtnmntmNewRadioItem_1);
		
		JMenu mnModo = new JMenu("Modo");
		menuBar.add(mnModo);
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel pRegistro = new JPanel();
		getContentPane().add(pRegistro);
		GridBagLayout gbl_pRegistro = new GridBagLayout();
		gbl_pRegistro.columnWidths = new int[]{274, 0};
		gbl_pRegistro.rowHeights = new int[]{95, 57, 166, 24, 0};
		gbl_pRegistro.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pRegistro.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pRegistro.setLayout(gbl_pRegistro);
		
		JPanel pIDTrabajador = new JPanel();
		pIDTrabajador.setBorder(new TitledBorder(null, "Identificaci\u00F3n del trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		JPanel pProvincia = new JPanel();
		pProvincia.setBorder(new TitledBorder(null, "Provincia del trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		JComboBox cbProvincias = new JComboBox();
		cbProvincias.setEditable(true);
		GridBagConstraints gbc_cbProvincias = new GridBagConstraints();
		gbc_cbProvincias.insets = new Insets(0, 0, 5, 5);
		gbc_cbProvincias.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbProvincias.gridx = 0;
		gbc_cbProvincias.gridy = 0;
		pProvincia.add(cbProvincias, gbc_cbProvincias);
		
		JButton bEliminarProvincia = new JButton("Eliminar provincia");
		GridBagConstraints gbc_bEliminarProvincia = new GridBagConstraints();
		gbc_bEliminarProvincia.insets = new Insets(0, 0, 5, 0);
		gbc_bEliminarProvincia.gridx = 1;
		gbc_bEliminarProvincia.gridy = 0;
		pProvincia.add(bEliminarProvincia, gbc_bEliminarProvincia);
		
		JButton bAddProvincia = new JButton("Añadir provincia");
		GridBagConstraints gbc_bAddProvincia = new GridBagConstraints();
		gbc_bAddProvincia.fill = GridBagConstraints.HORIZONTAL;
		gbc_bAddProvincia.gridwidth = 2;
		gbc_bAddProvincia.insets = new Insets(0, 0, 0, 5);
		gbc_bAddProvincia.gridx = 0;
		gbc_bAddProvincia.gridy = 1;
		pProvincia.add(bAddProvincia, gbc_bAddProvincia);
		
		JPanel pProfesion = new JPanel();
		pProfesion.setBorder(new TitledBorder(null, "Profesi\u00F3n del trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		JList<? extends E> lProfesion = new JList();
		GridBagConstraints gbc_lProfesion = new GridBagConstraints();
		gbc_lProfesion.gridheight = 2;
		gbc_lProfesion.insets = new Insets(0, 0, 5, 5);
		gbc_lProfesion.fill = GridBagConstraints.BOTH;
		gbc_lProfesion.gridx = 0;
		gbc_lProfesion.gridy = 0;
		pProfesion.add(list, gbc_lProfesion);
		
		JButton bEliminarProfesion = new JButton("Eliminar profesión");
		GridBagConstraints gbc_bEliminarProfesion = new GridBagConstraints();
		gbc_bEliminarProfesion.anchor = GridBagConstraints.NORTH;
		gbc_bEliminarProfesion.insets = new Insets(0, 0, 5, 0);
		gbc_bEliminarProfesion.gridx = 1;
		gbc_bEliminarProfesion.gridy = 0;
		pProfesion.add(bEliminarProfesion, gbc_bEliminarProfesion);
		
		tfProfesion = new JTextField();
		GridBagConstraints gbc_tfProfesion = new GridBagConstraints();
		gbc_tfProfesion.insets = new Insets(0, 0, 0, 5);
		gbc_tfProfesion.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfProfesion.gridx = 0;
		gbc_tfProfesion.gridy = 2;
		pProfesion.add(tfProfesion, gbc_tfProfesion);
		tfProfesion.setColumns(10);
		
		JButton bAddProfesion = new JButton("Añadir profesión");
		GridBagConstraints gbc_bAddProfesion = new GridBagConstraints();
		gbc_bAddProfesion.fill = GridBagConstraints.HORIZONTAL;
		gbc_bAddProfesion.gridx = 1;
		gbc_bAddProfesion.gridy = 2;
		pProfesion.add(bAddProfesion, gbc_bAddProfesion);
		
		JButton bAddTrabajador = new JButton("Añadir trabajador");
		GridBagConstraints gbc_bAddTrabajador = new GridBagConstraints();
		gbc_bAddTrabajador.fill = GridBagConstraints.BOTH;
		gbc_bAddTrabajador.gridx = 0;
		gbc_bAddTrabajador.gridy = 3;
		pRegistro.add(bAddTrabajador, gbc_bAddTrabajador);
		
		JPanel pDatos = new JPanel();
		getContentPane().add(pDatos);
		GridBagLayout gbl_pDatos = new GridBagLayout();
		gbl_pDatos.columnWidths = new int[]{274, 0};
		gbl_pDatos.rowHeights = new int[]{195, 117, 0};
		gbl_pDatos.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pDatos.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		pDatos.setLayout(gbl_pDatos);
		
		JPanel pTabla = new JPanel();
		pTabla.setBorder(new TitledBorder(null, "Trabajadores disponibles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pTabla = new GridBagConstraints();
		gbc_pTabla.fill = GridBagConstraints.BOTH;
		gbc_pTabla.insets = new Insets(0, 0, 5, 0);
		gbc_pTabla.gridx = 0;
		gbc_pTabla.gridy = 0;
		pDatos.add(pTabla, gbc_pTabla);
		GridBagLayout gbl_pTabla = new GridBagLayout();
		gbl_pTabla.columnWidths = new int[]{274, 0};
		gbl_pTabla.rowHeights = new int[]{97, 21, 0};
		gbl_pTabla.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pTabla.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		pTabla.setLayout(gbl_pTabla);
		
		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.insets = new Insets(0, 0, 5, 0);
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		pTabla.add(table, gbc_table);
		
		JButton bEliminarTrabajador = new JButton("Eliminar trabajador");
		GridBagConstraints gbc_bEliminarTrabajador = new GridBagConstraints();
		gbc_bEliminarTrabajador.fill = GridBagConstraints.BOTH;
		gbc_bEliminarTrabajador.gridx = 0;
		gbc_bEliminarTrabajador.gridy = 1;
		pTabla.add(bEliminarTrabajador, gbc_bEliminarTrabajador);
		
		JPanel pDatosTrabajador = new JPanel();
		pDatosTrabajador.setBorder(new TitledBorder(null, "Detalles del trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pDatosTrabajador = new GridBagConstraints();
		gbc_pDatosTrabajador.fill = GridBagConstraints.BOTH;
		gbc_pDatosTrabajador.gridx = 0;
		gbc_pDatosTrabajador.gridy = 1;
		pDatos.add(pDatosTrabajador, gbc_pDatosTrabajador);
		pDatosTrabajador.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextArea textArea = new JTextArea();
		pDatosTrabajador.add(textArea);

	}
	}

}
