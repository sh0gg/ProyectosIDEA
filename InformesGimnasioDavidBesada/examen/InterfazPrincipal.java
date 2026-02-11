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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class InterfazPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public ArrayList<Usuario> usuarios;
	private JComboBox cbUsuario;
	private JButton bEntra;
	private JButton bSale;
	private JButton bVerFicha;
	private JButton bDescargarFicha;
	private JTextArea lHistorial;
	private JTextArea lPersonasEnGimnasio;
	private JPanel pHistorial;
	private JPanel pPersonasEnGimnasio;
	private static Connection conn;

	ArrayList<Usuario> getUsuarios() {
		return this.usuarios;
	}

	void setUsuarios(ArrayList<Usuario> u) {
		this.usuarios = u;
	}

	public final static Connection getConnection(String bd, String user, String pass) throws SQLException {
		String url = "";

		url = "jdbc:mysql://localhost:3306/" + bd + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

		return DriverManager.getConnection(url, user, pass);
	}

	public void verInformeHistorial() {
		try {
			JasperPrint print = JasperFillManager.fillReport("src/reportes/INFORME2Tarea3DavidBesada.jasper", null,
					conn);
			JasperViewer.viewReport(print, false);
		} catch (JRException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al abrir historial: " + e.getMessage());
		}
	}

	public void descargarInformeHistorial() {
		try {
			JasperPrint print = JasperFillManager.fillReport("src/reportes/INFORME2Tarea3DavidBesada.jasper", null,
					conn);
			JasperExportManager.exportReportToPdfFile(print, "src/reportes/Historial_Completo.pdf");
			JOptionPane.showMessageDialog(this, "Historial exportado a PDF correctamente.");
		} catch (JRException e) {
			e.printStackTrace();
		}
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
		bVerFicha.setEnabled(false);
		bDescargarFicha.setEnabled(false);

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
		bVerFicha.setEnabled(false);
		bDescargarFicha.setEnabled(false);

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

	public void verFichaUsuario() {
		int index = cbUsuario.getSelectedIndex();
		if (index == -1) {
			JOptionPane.showMessageDialog(this, "Selecciona un usuario primero");
			return;
		}

		Usuario seleccionado = usuarios.get(index);

		try {
			// Creamos el mapa de parámetros
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("dni", seleccionado.getDni()); // Pasamos el DNI como filtro

			JasperPrint print = JasperFillManager.fillReport("src/reportes/INFORME3Tarea3DavidBesada.jasper",
					parametros, conn);
			JasperViewer.viewReport(print, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
	public void descargarFichaUsuario() {
	    int index = cbUsuario.getSelectedIndex();
	    if (index == -1) {
	        JOptionPane.showMessageDialog(this, "Selecciona un usuario primero");
	        return;
	    }

	    Usuario seleccionado = usuarios.get(index);

	    try {
	        Map<String, Object> parametros = new HashMap<>();
	        parametros.put("dni", seleccionado.getDni()); 

	        JasperPrint print = JasperFillManager.fillReport("src/reportes/INFORME3Tarea3DavidBesada.jasper",
	                parametros, conn);

	        String rutaPdf = "src/reportes/Ficha_" + seleccionado.getDni() + ".pdf";

	        JasperExportManager.exportReportToPdfFile(print, rutaPdf);

	        JOptionPane.showMessageDialog(this, "Ficha de " + seleccionado.getNombre() + " exportada correctamente en: " + rutaPdf);

	    } catch (JRException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error al generar el PDF: " + e.getMessage());
	    }
	}

	void abrirReporte() {
		// Contamos los movimientos del historial (puedes usar una variable global o
		// contar líneas del JTextArea)
		int numMovimientos = lHistorial.getText().split("\n").length;
		if (lHistorial.getText().isEmpty())
			numMovimientos = 0;

		DlgReporte dlg = new DlgReporte(this, true, usuarios, numMovimientos);
		dlg.setVisible(true);
	}

	void abrirRegistro() {
		InterfazRegistro ir = new InterfazRegistro(this); // Pasar 'this'
		ir.setVisible(true);
	}

	public void addUsuario(Usuario u) {
		usuarios.add(u);
		cbUsuario.addItem("F - " + u.getDni() + " - " + u.getNombre());
	}

	public void cargarUsuariosDesdeBD() {
		usuarios.clear();
		cbUsuario.removeAllItems();

		String sql = "SELECT * FROM usuarios";
		try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Usuario u = new Usuario(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getInt("edad"));
				usuarios.add(u);
				cbUsuario.addItem(u.toString());
			}
			cbUsuario.setSelectedIndex(-1);

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al cargar usuarios: " + e.getMessage());
		}
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					conn = getConnection("dint", "root", "abc123.,");
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
		gbl_pControlAcceso.columnWidths = new int[] { 99, 0, 0 };
		gbl_pControlAcceso.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_pControlAcceso.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_pControlAcceso.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		pControlAcceso.setLayout(gbl_pControlAcceso);

		bEntra = new JButton("ENTRA");
		bEntra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indexSeleccionado = cbUsuario.getSelectedIndex();
				if (indexSeleccionado == -1)
					return; // Seguridad

				Usuario user = usuarios.get(indexSeleccionado);
				entrar();

				String fechaHora = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
				String registroTexto = fechaHora + " - " + user.getNombre() + " " + user.getApellidos()
						+ " entra al gimnasio";

				String sql = "INSERT INTO historial (dni_usuario, movimiento, registro_texto) VALUES (?, ?, ?)";

				try (PreparedStatement ps = conn.prepareStatement(sql)) {
					ps.setString(1, user.getDni());
					ps.setString(2, "ENTRADA");
					ps.setString(3, registroTexto);
					ps.executeUpdate();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
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
				int indexSeleccionado = cbUsuario.getSelectedIndex();
				if (indexSeleccionado == -1)
					return;

				Usuario user = usuarios.get(indexSeleccionado);

				salir();

				String fechaHora = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
				String registroTexto = fechaHora + " - " + user.getNombre() + " " + user.getApellidos()
						+ " sale del gimnasio";

				String sql = "INSERT INTO historial (dni_usuario, movimiento, registro_texto) VALUES (?, ?, ?)";

				try (PreparedStatement ps = conn.prepareStatement(sql)) {
					ps.setString(1, user.getDni());
					ps.setString(2, "SALIDA");
					ps.setString(3, registroTexto);
					ps.executeUpdate();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
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

		bVerFicha = new JButton("Ver ficha");
		bVerFicha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verFichaUsuario();
			}
		});

		bVerFicha.setEnabled(false);
		GridBagConstraints gbc_bVerFicha = new GridBagConstraints();
		gbc_bVerFicha.fill = GridBagConstraints.HORIZONTAL;
		gbc_bVerFicha.insets = new Insets(0, 0, 5, 5);
		gbc_bVerFicha.gridx = 0;
		gbc_bVerFicha.gridy = 2;
		pControlAcceso.add(bVerFicha, gbc_bVerFicha);

		bDescargarFicha = new JButton("Descargar ficha");
		bDescargarFicha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				descargarFichaUsuario();
			}
		});
		bDescargarFicha.setEnabled(false);
		GridBagConstraints gbc_bDescargarFicha = new GridBagConstraints();
		gbc_bDescargarFicha.fill = GridBagConstraints.HORIZONTAL;
		gbc_bDescargarFicha.insets = new Insets(0, 0, 5, 0);
		gbc_bDescargarFicha.gridx = 1;
		gbc_bDescargarFicha.gridy = 2;
		pControlAcceso.add(bDescargarFicha, gbc_bDescargarFicha);
		GridBagConstraints gbc_bReporte = new GridBagConstraints();
		gbc_bReporte.fill = GridBagConstraints.HORIZONTAL;
		gbc_bReporte.gridwidth = 2;
		gbc_bReporte.insets = new Insets(0, 0, 10, 0);
		gbc_bReporte.gridx = 0;
		gbc_bReporte.gridy = 3;
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
		gbc_bRegistro.gridy = 4;
		pControlAcceso.add(bRegistro, gbc_bRegistro);

		pPersonasEnGimnasio = new JPanel();
		pPersonasEnGimnasio.setBorder(
				new TitledBorder(null, "Personas en el gimnasio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pPersonasEnGimnasio = new GridBagConstraints();
		gbc_pPersonasEnGimnasio.gridwidth = 2;
		gbc_pPersonasEnGimnasio.fill = GridBagConstraints.BOTH;
		gbc_pPersonasEnGimnasio.gridx = 0;
		gbc_pPersonasEnGimnasio.gridy = 5;
		pControlAcceso.add(pPersonasEnGimnasio, gbc_pPersonasEnGimnasio);
		pPersonasEnGimnasio.setLayout(new GridLayout(0, 1, 0, 0));

		lPersonasEnGimnasio = new JTextArea();
		lPersonasEnGimnasio.setEditable(false);
		pPersonasEnGimnasio.add(lPersonasEnGimnasio);

		pHistorial = new JPanel();
		pHistorial.setBorder(new TitledBorder(null, "Historial", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pHistorial);
		GridBagLayout gbl_pHistorial = new GridBagLayout();
		gbl_pHistorial.columnWidths = new int[] { 0, 150, 106, 0 };
		gbl_pHistorial.rowHeights = new int[] { 327, 0, 0 };
		gbl_pHistorial.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_pHistorial.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		pHistorial.setLayout(gbl_pHistorial);

		lHistorial = new JTextArea();
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.gridwidth = 3;
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

		JButton bDescargarHistorial = new JButton("Descargar historial");
		bDescargarHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				descargarInformeHistorial();
			}
		});
		GridBagConstraints gbc_bDescargarHistorial = new GridBagConstraints();
		gbc_bDescargarHistorial.fill = GridBagConstraints.HORIZONTAL;
		gbc_bDescargarHistorial.insets = new Insets(0, 0, 0, 5);
		gbc_bDescargarHistorial.gridx = 0;
		gbc_bDescargarHistorial.gridy = 1;
		pHistorial.add(bDescargarHistorial, gbc_bDescargarHistorial);

		JButton bVerHistorial = new JButton("Ver historial");
		bVerHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verInformeHistorial();
			}
		});
		GridBagConstraints gbc_bVerHistorial = new GridBagConstraints();
		gbc_bVerHistorial.fill = GridBagConstraints.HORIZONTAL;
		gbc_bVerHistorial.insets = new Insets(0, 0, 0, 5);
		gbc_bVerHistorial.gridx = 1;
		gbc_bVerHistorial.gridy = 1;
		pHistorial.add(bVerHistorial, gbc_bVerHistorial);
		GridBagConstraints gbc_bLimpiarHistorial = new GridBagConstraints();
		gbc_bLimpiarHistorial.fill = GridBagConstraints.HORIZONTAL;
		gbc_bLimpiarHistorial.gridx = 2;
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
		
		cbUsuario = new JComboBox();
		GridBagConstraints gbc_cbUsuario = new GridBagConstraints();
		cbUsuario.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int seleccionado = cbUsuario.getSelectedIndex();
		        
		        // 1. Si no hay selección (índice -1), desactivamos todo
		        if (seleccionado == -1) {
		            bVerFicha.setEnabled(false);
		            bDescargarFicha.setEnabled(false);
		            bEntra.setEnabled(false);
		            bSale.setEnabled(false);
		            return;
		        }

		        // 2. Si hay selección, habilitamos los botones de reporte
		        bVerFicha.setEnabled(true);
		        bDescargarFicha.setEnabled(true);

		        // 3. Obtenemos el texto del item para saber si está DENTRO o FUERA
		        String itemTexto = cbUsuario.getSelectedItem().toString();
		        String estado = itemTexto.substring(0, 1); // Tomamos la primera letra ('D' o 'F')
		        
		        if (estado.equals("D")) { // Caso: DENTRO
		            bEntra.setEnabled(false);
		            bSale.setEnabled(true);
		        } else if (estado.equals("F")) { // Caso: FUERA
		            bEntra.setEnabled(true);
		            bSale.setEnabled(false);
		        }
		        
		        // Refrescamos visualmente el panel
		        pControlAcceso.revalidate();
		        pControlAcceso.repaint();
		    }
		});
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

		cargarUsuariosDesdeBD();

	}
}
