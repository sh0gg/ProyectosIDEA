package simulacro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;

public class InterfazPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfDNI;
	private JTextField tfNombre;
	private JTextField tfApellidos;
	private JTable table;
	private DefaultTableModel modeloAlumnos;
	private Runnable toggleEmptyView;

	public class Alumno {
		public String dni;
		public String nombre;
		public String apellidos;

		public Alumno(String dni, String nombre, String apellidos) {
			dni = this.dni;
			nombre = this.nombre;
			apellidos = this.apellidos;
		}

		public String getDni() {
			return dni;
		}

		public void setDni(String dni) {
			this.dni = dni;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellidos() {
			return apellidos;
		}

		public void setApellidos(String apellidos) {
			this.apellidos = apellidos;
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazPrincipal frame = new InterfazPrincipal();
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
	public InterfazPrincipal() {

		setTitle("Gestión alumnos - David Besada Ramilo - Simulacro 19/11");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 380);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		setJMenuBar(menuBar);

		JMenu mnAlumos = new JMenu("Sesión");
		menuBar.add(mnAlumos);

		JMenuItem mntmAddAlumno = new JMenuItem("Añadir alumno");
		mntmAddAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAlumno();
			}
		});
		mntmAddAlumno.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK));
		mnAlumos.add(mntmAddAlumno);

		JMenu mnResultados = new JMenu("Acciones");
		menuBar.add(mnResultados);

		JMenuItem mntmActualizar = new JMenuItem("Actualizar");
		mntmActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarAlumno();
			}
		});
		mntmActualizar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK));
		mnResultados.add(mntmActualizar);

		JMenuItem mntmEliminar = new JMenuItem("Eliminar");
		mntmEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarAlumno();
			}
		});
		mntmEliminar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_DOWN_MASK));
		mnResultados.add(mntmEliminar);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel pGestionAlumnos = new JPanel();
		pGestionAlumnos.setBorder(
				new TitledBorder(null, "Gesti\u00F3n alumnos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pGestionAlumnos);
		GridBagLayout gbl_pGestionAlumnos = new GridBagLayout();
		gbl_pGestionAlumnos.columnWidths = new int[] { 0, 0, 0 };
		gbl_pGestionAlumnos.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_pGestionAlumnos.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_pGestionAlumnos.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pGestionAlumnos.setLayout(gbl_pGestionAlumnos);

		JLabel lblDNI = new JLabel("DNI");
		GridBagConstraints gbc_lblDNI = new GridBagConstraints();
		gbc_lblDNI.anchor = GridBagConstraints.WEST;
		gbc_lblDNI.insets = new Insets(20, 20, 5, 5);
		gbc_lblDNI.gridx = 0;
		gbc_lblDNI.gridy = 0;
		pGestionAlumnos.add(lblDNI, gbc_lblDNI);

		tfDNI = new JTextField();
		GridBagConstraints gbc_tfDNI = new GridBagConstraints();
		gbc_tfDNI.insets = new Insets(20, 0, 5, 20);
		gbc_tfDNI.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfDNI.gridx = 1;
		gbc_tfDNI.gridy = 0;
		pGestionAlumnos.add(tfDNI, gbc_tfDNI);
		tfDNI.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 20, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 1;
		pGestionAlumnos.add(lblNombre, gbc_lblNombre);

		tfNombre = new JTextField();
		GridBagConstraints gbc_tfNombre = new GridBagConstraints();
		gbc_tfNombre.insets = new Insets(0, 0, 5, 20);
		gbc_tfNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNombre.gridx = 1;
		gbc_tfNombre.gridy = 1;
		pGestionAlumnos.add(tfNombre, gbc_tfNombre);
		tfNombre.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellidos");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.WEST;
		gbc_lblApellidos.insets = new Insets(0, 20, 5, 5);
		gbc_lblApellidos.gridx = 0;
		gbc_lblApellidos.gridy = 2;
		pGestionAlumnos.add(lblApellidos, gbc_lblApellidos);

		tfApellidos = new JTextField();
		GridBagConstraints gbc_tfApellidos = new GridBagConstraints();
		gbc_tfApellidos.insets = new Insets(0, 0, 5, 20);
		gbc_tfApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfApellidos.gridx = 1;
		gbc_tfApellidos.gridy = 2;
		pGestionAlumnos.add(tfApellidos, gbc_tfApellidos);
		tfApellidos.setColumns(10);

		JButton bAddAlumno = new JButton("Añadir alumno");
		bAddAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAlumno();
			}
		});
		GridBagConstraints gbc_bAddAlumno = new GridBagConstraints();
		gbc_bAddAlumno.fill = GridBagConstraints.HORIZONTAL;
		gbc_bAddAlumno.gridwidth = 2;
		gbc_bAddAlumno.insets = new Insets(0, 20, 20, 20);
		gbc_bAddAlumno.gridx = 0;
		gbc_bAddAlumno.gridy = 3;
		pGestionAlumnos.add(bAddAlumno, gbc_bAddAlumno);

		JPanel pResultados = new JPanel();
		pResultados.setBorder(new TitledBorder(null, "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pResultados);
		GridBagLayout gbl_pResultados = new GridBagLayout();
		gbl_pResultados.columnWidths = new int[] { 0, 0, 0 };
		gbl_pResultados.rowHeights = new int[] { 0, 0, 0 };
		gbl_pResultados.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_pResultados.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		pResultados.setLayout(gbl_pResultados);

		table = new JTable();

		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "DNI", "Nombre", "Apellidos" }) {
			@Override
			public boolean isCellEditable(int r, int c) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return Object.class;
			}
		});

		modeloAlumnos = (DefaultTableModel) table.getModel();
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		JScrollPane scroller = new JScrollPane(table);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 2;
		gbc_table.insets = new Insets(0, 5, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		pResultados.add(scroller, gbc_table);

		JButton bActualizar = new JButton("Actualizar");
		bActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarAlumno();
			}
		});
		GridBagConstraints gbc_bActualizar = new GridBagConstraints();
		gbc_bActualizar.fill = GridBagConstraints.HORIZONTAL;
		gbc_bActualizar.insets = new Insets(0, 0, 0, 5);
		gbc_bActualizar.gridx = 0;
		gbc_bActualizar.gridy = 1;
		pResultados.add(bActualizar, gbc_bActualizar);

		JButton bEliminar = new JButton("Eliminar");
		bEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarAlumno();
			}
		});
		GridBagConstraints gbc_bEliminar = new GridBagConstraints();
		gbc_bEliminar.fill = GridBagConstraints.HORIZONTAL;
		gbc_bEliminar.gridx = 1;
		gbc_bEliminar.gridy = 1;
		pResultados.add(bEliminar, gbc_bEliminar);

		toggleEmptyView = () -> {
			boolean empty = modeloAlumnos.getRowCount() == 0;
			scroller.setVisible(!empty);
			pResultados.revalidate();
			pResultados.repaint();
		};

		toggleEmptyView.run();
		
	}

	public void addAlumno() {

		String dni = tfDNI.getText().trim();
		String nombre = tfNombre.getText().trim();
		String apellidos = tfApellidos.getText().trim();

		if (dni.isEmpty() || nombre.isEmpty() || apellidos.isEmpty()) {
			JOptionPane.showMessageDialog(InterfazPrincipal.this, "Debe cubrir todos los campos");
			return;
		}

		Alumno a = new Alumno(dni, nombre, apellidos);

		insertarAlumno(a);
		
		toggleEmptyView.run();
		tfDNI.setText("");
		tfNombre.setText("");
		tfApellidos.setText("");

	}

	public void insertarAlumno(Alumno a) {
		if (modeloAlumnos == null)
			return;
		modeloAlumnos.setRowCount(modeloAlumnos.getRowCount() + 1);
		int r = modeloAlumnos.getRowCount() - 1;
		modeloAlumnos.setValueAt(a.getDni(), r, 0);
		modeloAlumnos.setValueAt(a.getNombre(), r, 1);
		modeloAlumnos.setValueAt(a.getApellidos(), r, 2);
	}

	public void actualizarAlumno() {

		int selectedRow = table.getSelectedRow();

		if (modeloAlumnos.getRowCount() == 0) {
			JOptionPane.showMessageDialog(InterfazPrincipal.this, "No hay alumnos registrados");
			return;
		}

		if (selectedRow < 0 || selectedRow > modeloAlumnos.getRowCount()) {
			JOptionPane.showMessageDialog(InterfazPrincipal.this, "Debes seleccionar un alumno");
			return;
		}

		// Alumno a = (Alumno) modeloAlumnos.getValueAt(selectedRow, 0); ESTO ES LO QUE NO ME ESTÁ FUNCIONANDO (Comentado con el profe en clase)
		
		Alumno a = new Alumno("34343434a","Pepe","Solla");

		DlgActualizar dialogo = new DlgActualizar(a);
		dialogo.setVisible(true);

	}

	public void eliminarAlumno() {

		int selectedRow = table.getSelectedRow();

		if (modeloAlumnos.getRowCount() == 0) {
			JOptionPane.showMessageDialog(InterfazPrincipal.this, "No hay alumnos registrados");
			return;
		}

		if (selectedRow < 0 || selectedRow > modeloAlumnos.getRowCount()) {
			JOptionPane.showMessageDialog(InterfazPrincipal.this, "Debes seleccionar un alumno");
			return;
		}

		modeloAlumnos.removeRow(selectedRow);
		
	}

}
