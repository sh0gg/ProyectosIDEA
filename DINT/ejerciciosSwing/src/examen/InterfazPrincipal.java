package examen;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class InterfazPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public ArrayList<Usuario> usuarios;
	private JComboBox cbUsuario;
	private JButton bEntra;
	private JButton bSale;
	private JTextArea lHistorial;
	private JTextArea lPersonasEnGimnasio;
	private JPanel pHistorial;
	private JPanel pPersonasEnGimnasio;
	
	ArrayList<Usuario> getUsuarios() {
		return this.usuarios;
	}
	
	void setUsuarios(ArrayList<Usuario> u) {
		this.usuarios = u;
	}

	public static class Usuario {
		private String dni;
		private String nombre;
		private String apellidos;
		private int edad;
		private boolean isDentro;

		public Usuario(String dni, String nombre, String apellidos, int edad) {
			super();
			this.dni = dni;
			this.nombre = nombre;
			this.apellidos = apellidos;
			this.edad = edad;
			this.isDentro = false;
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

		public boolean isDentro() {
			return isDentro;
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

		public int getEdad() {
			return edad;
		}

		public void setEdad(int edad) {
			this.edad = edad;
		}

		public void setDentro(boolean estado) {
			this.isDentro = estado;
		}

		@Override
		public String toString() {
			if (isDentro) {
				return "DENTRO - [DNI: " + dni + " - NOMBRE: " + nombre + " - APELLIDOS:" + apellidos + "]";
			} else {
				return "FUERA - [DNI: " + dni + " - NOMBRE: " + nombre + " - APELLIDOS:" + apellidos + "]";
			}
		}

	}

	// mis metodos

	void entrar() {
		int indexUsuario = cbUsuario.getSelectedIndex();

		usuarios.get(indexUsuario).setDentro(true);

		cbUsuario.removeAllItems();
		for (int i = 0; i < usuarios.size(); i++) {
			String u = usuarios.get(i).toString();
			cbUsuario.addItem(u);
		}
		bEntra.setEnabled(false);
		bSale.setEnabled(false);

		StringBuilder sb = new StringBuilder();
		sb.append(lHistorial.getText());
		sb.append(usuarios.get(indexUsuario).toString() + "\n");

		lHistorial.setText(sb.toString());

		pHistorial.revalidate();
		pHistorial.repaint();

		StringBuilder sb2 = new StringBuilder();
		for (Usuario u : usuarios) {
			if (u.isDentro()) {
				sb2.append(u.toString() + "\n");
			}
		}

		lPersonasEnGimnasio.setText(sb2.toString());

		cbUsuario.setSelectedIndex(-1);
	}

	void salir() {
		int indexUsuario = cbUsuario.getSelectedIndex();

		usuarios.get(indexUsuario).setDentro(false);

		cbUsuario.removeAllItems();
		for (int i = 0; i < usuarios.size(); i++) {
			String u = usuarios.get(i).toString();
			cbUsuario.addItem(u);
		}
		bEntra.setEnabled(false);
		bSale.setEnabled(false);

		StringBuilder sb = new StringBuilder();
		sb.append(lHistorial.getText());
		sb.append(usuarios.get(indexUsuario).toString() + "\n");

		lHistorial.setText(sb.toString());

		pHistorial.revalidate();
		pHistorial.repaint();

		StringBuilder sb2 = new StringBuilder();
		for (Usuario u : usuarios) {
			if (u.isDentro()) {
				sb2.append(u.toString() + "\n");
			}
		}

		lPersonasEnGimnasio.setText(sb2.toString());

		pPersonasEnGimnasio.revalidate();
		pPersonasEnGimnasio.repaint();

		cbUsuario.setSelectedIndex(-1);
	}

	void abrirReporte() {
		DlgReporte dlgReporte = new DlgReporte(usuarios, lHistorial.getRows());
		dlgReporte.setVisible(true);
	}
	
	void abrirRegistro() {
		InterfazRegistro frmRegistro = new InterfazRegistro();
		frmRegistro.setVisible(true);
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
		usuarios = new ArrayList<Usuario>();

		Usuario u0 = new Usuario("0000000a", "Pepe", "Fanecas Ronchas", 40);
		usuarios.add(u0);

		Usuario u1 = new Usuario("0000001a", "Maria", "Garcia Gomez", 21);
		usuarios.add(u1);

		setTitle("Gimnasio - David Besada Ramilo - Examen Interfaces");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel pControlAcceso = new JPanel();
		pControlAcceso.setBorder(
				new TitledBorder(null, "Control de acceso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pControlAcceso);
		GridBagLayout gbl_pControlAcceso = new GridBagLayout();
		gbl_pControlAcceso.columnWidths = new int[] { 0, 0, 0 };
		gbl_pControlAcceso.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_pControlAcceso.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_pControlAcceso.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		pControlAcceso.setLayout(gbl_pControlAcceso);

		cbUsuario = new JComboBox();
		GridBagConstraints gbc_cbUsuario = new GridBagConstraints();
		gbc_cbUsuario.gridwidth = 2;
		gbc_cbUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_cbUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbUsuario.gridx = 0;
		gbc_cbUsuario.gridy = 0;
		for (int i = 0; i < usuarios.size(); i++) {
			String u = usuarios.get(i).toString();
			cbUsuario.addItem(u);
		}
		cbUsuario.setSelectedIndex(-1);
		pControlAcceso.add(cbUsuario, gbc_cbUsuario);

		bEntra = new JButton("ENTRA");
		bEntra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entrar();
			}
		});
		bEntra.setEnabled(false);
		GridBagConstraints gbc_bEntra = new GridBagConstraints();
		gbc_bEntra.fill = GridBagConstraints.HORIZONTAL;
		gbc_bEntra.insets = new Insets(0, 0, 5, 5);
		gbc_bEntra.gridx = 0;
		gbc_bEntra.gridy = 1;
		pControlAcceso.add(bEntra, gbc_bEntra);

		bSale = new JButton("SALE");
		bSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		bSale.setEnabled(false);
		GridBagConstraints gbc_bSale = new GridBagConstraints();
		gbc_bSale.fill = GridBagConstraints.HORIZONTAL;
		gbc_bSale.insets = new Insets(0, 0, 5, 0);
		gbc_bSale.gridx = 1;
		gbc_bSale.gridy = 1;
		pControlAcceso.add(bSale, gbc_bSale);

		JButton bReporte = new JButton("REPORTE");
		bReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirReporte();
			}
		});
		GridBagConstraints gbc_bReporte = new GridBagConstraints();
		gbc_bReporte.fill = GridBagConstraints.HORIZONTAL;
		gbc_bReporte.gridwidth = 2;
		gbc_bReporte.insets = new Insets(0, 0, 10, 0);
		gbc_bReporte.gridx = 0;
		gbc_bReporte.gridy = 2;
		pControlAcceso.add(bReporte, gbc_bReporte);

		JButton bRegistro = new JButton("REGISTRO");
		bRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirRegistro();
			}
		});
		GridBagConstraints gbc_bRegistro = new GridBagConstraints();
		gbc_bRegistro.insets = new Insets(0, 0, 5, 0);
		gbc_bRegistro.fill = GridBagConstraints.HORIZONTAL;
		gbc_bRegistro.gridwidth = 2;
		gbc_bRegistro.gridx = 0;
		gbc_bRegistro.gridy = 3;
		pControlAcceso.add(bRegistro, gbc_bRegistro);

		pPersonasEnGimnasio = new JPanel();
		pPersonasEnGimnasio.setBorder(
				new TitledBorder(null, "Personas en el gimnasio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pPersonasEnGimnasio = new GridBagConstraints();
		gbc_pPersonasEnGimnasio.gridwidth = 2;
		gbc_pPersonasEnGimnasio.insets = new Insets(0, 0, 0, 5);
		gbc_pPersonasEnGimnasio.fill = GridBagConstraints.BOTH;
		gbc_pPersonasEnGimnasio.gridx = 0;
		gbc_pPersonasEnGimnasio.gridy = 4;
		pControlAcceso.add(pPersonasEnGimnasio, gbc_pPersonasEnGimnasio);
		pPersonasEnGimnasio.setLayout(new GridLayout(0, 1, 0, 0));

		lPersonasEnGimnasio = new JTextArea();
		lPersonasEnGimnasio.setEditable(false);
		pPersonasEnGimnasio.add(lPersonasEnGimnasio);

		pHistorial = new JPanel();
		pHistorial.setBorder(new TitledBorder(null, "Historial", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pHistorial);
		GridBagLayout gbl_pHistorial = new GridBagLayout();
		gbl_pHistorial.columnWidths = new int[] { 475, 0 };
		gbl_pHistorial.rowHeights = new int[] { 327, 0, 0 };
		gbl_pHistorial.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_pHistorial.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		pHistorial.setLayout(gbl_pHistorial);

		lHistorial = new JTextArea();
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		lHistorial.setEditable(false);
		gbc_textArea_1.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.gridx = 0;
		gbc_textArea_1.gridy = 0;
		pHistorial.add(lHistorial, gbc_textArea_1);

		JButton bLimpiarHistorial = new JButton("Limpiar historial");
		bLimpiarHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lHistorial.setText("");
			}
		});
		GridBagConstraints gbc_bLimpiarHistorial = new GridBagConstraints();
		gbc_bLimpiarHistorial.fill = GridBagConstraints.HORIZONTAL;
		gbc_bLimpiarHistorial.gridx = 0;
		gbc_bLimpiarHistorial.gridy = 1;
		pHistorial.add(bLimpiarHistorial, gbc_bLimpiarHistorial);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menú");
		menuBar.add(mnMenu);

		JMenu mnControlAcceso = new JMenu("Control de acceso");
		mnMenu.add(mnControlAcceso);

		JMenuItem mntmRegistro = new JMenuItem("Registro");
		mntmRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirRegistro();
			}
		});
		mntmRegistro.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		mnControlAcceso.add(mntmRegistro);

		JMenuItem mntmReporte = new JMenuItem("Reporte");
		mntmReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirReporte();
			}
		});
		mntmReporte.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_DOWN_MASK));
		mnControlAcceso.add(mntmReporte);

		JMenuItem mntmLimpiarHistorial = new JMenuItem("Limpiar historial");
		mntmLimpiarHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lHistorial.setText("");
			}
		});
		mntmLimpiarHistorial.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
		mnMenu.add(mntmLimpiarHistorial);

		cbUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbUsuario.getSelectedIndex() > -1) {
					String u = cbUsuario.getSelectedItem().toString();
					String uEstado = u.substring(0, 1);
					if (uEstado.equals("D")) {
						bEntra.setEnabled(false);
						bSale.setEnabled(true);
						pControlAcceso.revalidate();
						pControlAcceso.repaint();
					} else if (uEstado.equals("F")) {
						bEntra.setEnabled(true);
						bSale.setEnabled(false);
						pControlAcceso.revalidate();
						pControlAcceso.repaint();
					}
				}
			}
		});

	}
}
