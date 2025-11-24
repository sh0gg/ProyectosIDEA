package examen;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

import examen.InterfazPrincipal.Usuario;

public class InterfazRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfDNI;
	private JTextField tfNombre;
	private JTextField tfApellidos;
	private JTextField tfEdad;
	private JCheckBox chkValPos;
	public boolean isRegistrado;
	public InterfazPrincipal intPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazRegistro frame = new InterfazRegistro();
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
	public InterfazRegistro() {
		isRegistrado = false;
		setTitle("David Besada Ramilo - Examen Interfaces - Interfaz Registro");
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menú");
		menuBar.add(mnMenu);

		JMenuItem mntmValidar = new JMenuItem("Validar datos");
		mntmValidar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validar();
			}
		});
		mntmValidar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
		mnMenu.add(mntmValidar);

		JMenuItem mntmRegistrar = new JMenuItem("Registrar");
		mntmRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validar())
					registrar();
			}
		});
		mntmRegistrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		mnMenu.add(mntmRegistrar);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(
				new TitledBorder(null, "Registro usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblDNI = new JLabel("DNI");
		GridBagConstraints gbc_lblDNI = new GridBagConstraints();
		gbc_lblDNI.anchor = GridBagConstraints.EAST;
		gbc_lblDNI.insets = new Insets(10, 10, 5, 5);
		gbc_lblDNI.gridx = 0;
		gbc_lblDNI.gridy = 0;
		contentPane.add(lblDNI, gbc_lblDNI);

		tfDNI = new JTextField();
		GridBagConstraints gbc_tfDNI = new GridBagConstraints();
		gbc_tfDNI.insets = new Insets(10, 0, 5, 10);
		gbc_tfDNI.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfDNI.gridx = 1;
		gbc_tfDNI.gridy = 0;
		contentPane.add(tfDNI, gbc_tfDNI);
		tfDNI.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 10, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 1;
		contentPane.add(lblNombre, gbc_lblNombre);

		tfNombre = new JTextField();
		GridBagConstraints gbc_tfNombre = new GridBagConstraints();
		gbc_tfNombre.anchor = GridBagConstraints.NORTH;
		gbc_tfNombre.insets = new Insets(0, 0, 5, 10);
		gbc_tfNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNombre.gridx = 1;
		gbc_tfNombre.gridy = 1;
		contentPane.add(tfNombre, gbc_tfNombre);
		tfNombre.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellidos");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.EAST;
		gbc_lblApellidos.insets = new Insets(0, 10, 5, 5);
		gbc_lblApellidos.gridx = 0;
		gbc_lblApellidos.gridy = 2;
		contentPane.add(lblApellidos, gbc_lblApellidos);

		tfApellidos = new JTextField();
		GridBagConstraints gbc_tfApellidos = new GridBagConstraints();
		gbc_tfApellidos.insets = new Insets(0, 0, 5, 10);
		gbc_tfApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfApellidos.gridx = 1;
		gbc_tfApellidos.gridy = 2;
		contentPane.add(tfApellidos, gbc_tfApellidos);
		tfApellidos.setColumns(10);

		JLabel lblEdad = new JLabel("Edad");
		GridBagConstraints gbc_lblEdad = new GridBagConstraints();
		gbc_lblEdad.anchor = GridBagConstraints.EAST;
		gbc_lblEdad.insets = new Insets(0, 10, 5, 5);
		gbc_lblEdad.gridx = 0;
		gbc_lblEdad.gridy = 3;
		contentPane.add(lblEdad, gbc_lblEdad);

		tfEdad = new JTextField();
		GridBagConstraints gbc_tfEdad = new GridBagConstraints();
		gbc_tfEdad.insets = new Insets(0, 0, 5, 10);
		gbc_tfEdad.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfEdad.gridx = 1;
		gbc_tfEdad.gridy = 3;
		contentPane.add(tfEdad, gbc_tfEdad);
		tfEdad.setColumns(10);

		chkValPos = new JCheckBox("Forzar validación positiva");
		GridBagConstraints gbc_chkValPos = new GridBagConstraints();
		gbc_chkValPos.insets = new Insets(0, 0, 5, 10);
		gbc_chkValPos.anchor = GridBagConstraints.EAST;
		gbc_chkValPos.gridx = 1;
		gbc_chkValPos.gridy = 4;
		contentPane.add(chkValPos, gbc_chkValPos);

		JButton bValidar = new JButton("Validar datos");
		bValidar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validar();
			}
		});
		GridBagConstraints gbc_bValidar = new GridBagConstraints();
		gbc_bValidar.fill = GridBagConstraints.HORIZONTAL;
		gbc_bValidar.gridwidth = 2;
		gbc_bValidar.insets = new Insets(0, 10, 5, 10);
		gbc_bValidar.gridx = 0;
		gbc_bValidar.gridy = 5;
		contentPane.add(bValidar, gbc_bValidar);

		JButton bRegistrar = new JButton("Registrar");
		bRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validar()) {
					registrar();
				}
			}
		});
		GridBagConstraints gbc_bRegistrar = new GridBagConstraints();
		gbc_bRegistrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_bRegistrar.gridwidth = 2;
		gbc_bRegistrar.insets = new Insets(0, 10, 0, 10);
		gbc_bRegistrar.gridx = 0;
		gbc_bRegistrar.gridy = 6;
		contentPane.add(bRegistrar, gbc_bRegistrar);

	}

	public void registrar() {
		int edad = Integer.parseInt(tfEdad.getText());
		Usuario u = new Usuario(tfDNI.getText(),tfNombre.getText(),tfApellidos.getText(),edad);
		ArrayList<Usuario> = intPrincipal.getUsuarios();
		intPrincipal.setUsuarios(null);
		
	}

	public boolean validar() {
		StringBuilder sb = new StringBuilder();
		if (chkValPos.isSelected()) {
			return true;
		} else if (tfDNI.getText().isBlank() || tfNombre.getText().isBlank() || tfApellidos.getText().isBlank() || tfEdad.getText().isBlank()) {
			sb.append("Todos los campos son obligatorios" + "\n");
		}
		
		JOptionPane.showMessageDialog(InterfazRegistro.this, sb.toString());	
		return false;
	}

}
