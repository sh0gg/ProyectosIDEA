package ejercicio1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class CombinarLayout extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfDocNor;
	private JPasswordField pswdContra;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CombinarLayout frame = new CombinarLayout();
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
	public CombinarLayout() {
		setTitle("David Besada - Combinar Layout");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 0));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 255, 0));
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setHgap(100);
		panel.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 255, 0));
		panel.add(panel_4);
		panel_4.setLayout(new GridLayout(2, 3, 5, 5));
		
		JLabel lblDocNor = new JLabel("Documento");
		lblDocNor.setBackground(new Color(0, 255, 0));
		panel_4.add(lblDocNor);
		
		JLabel lblPass = new JLabel("Contraseña");
		lblPass.setBackground(new Color(0, 255, 0));
		panel_4.add(lblPass);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 255, 0));
		panel_4.add(panel_5);
		
		tfDocNor = new JTextField();
		tfDocNor.setColumns(10);
		panel_4.add(tfDocNor);
		
		pswdContra = new JPasswordField();
		panel_4.add(pswdContra);
		
		JButton btnLogin = new JButton("Iniciar Sesión");
		panel_4.add(btnLogin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 255, 0));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblInfo = new JLabel("Desarrollado por: David Besada - DAM2 2025/26");
		panel_1.add(lblInfo);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 64, 128));
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(0, 64, 128));
		FlowLayout flowLayout_1 = (FlowLayout) panel_6.getLayout();
		flowLayout_1.setVgap(50);
		panel_2.add(panel_6, BorderLayout.WEST);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(CombinarLayout.class.getResource("/ejercicio1/logo2021.jpg")));
		panel_6.add(lblImagen);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(0, 64, 128));
		panel_2.add(panel_8, BorderLayout.NORTH);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(0, 64, 128));
		panel_2.add(panel_9, BorderLayout.SOUTH);
		
		JPanel panel_10 = new JPanel();
		panel_2.add(panel_10, BorderLayout.CENTER);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(128, 0, 64));
		panel_10.add(panel_7, BorderLayout.CENTER);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{10, 0, 0, 0, 0, 0, 0};
		gbl_panel_7.rowHeights = new int[]{10, 0, 0, 0, 0, 0, 87, 8, 0};
		gbl_panel_7.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
		
		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		panel_7.add(lblNombre, gbc_lblNombre);
		
		textField = new JTextField();
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panel_7.add(textField, gbc_textField);
		
		JLabel lblDocumento = new JLabel("Documento");
		GridBagConstraints gbc_lblDocumento = new GridBagConstraints();
		gbc_lblDocumento.anchor = GridBagConstraints.WEST;
		gbc_lblDocumento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDocumento.gridx = 3;
		gbc_lblDocumento.gridy = 1;
		panel_7.add(lblDocumento, gbc_lblDocumento);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 4;
		gbc_textField_1.gridy = 1;
		panel_7.add(textField_1, gbc_textField_1);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.WEST;
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidos.gridx = 1;
		gbc_lblApellidos.gridy = 2;
		panel_7.add(lblApellidos, gbc_lblApellidos);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 2;
		panel_7.add(textField_2, gbc_textField_2);
		
		JLabel lblTelf = new JLabel("Telefono");
		GridBagConstraints gbc_lblTelf = new GridBagConstraints();
		gbc_lblTelf.anchor = GridBagConstraints.WEST;
		gbc_lblTelf.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelf.gridx = 3;
		gbc_lblTelf.gridy = 2;
		panel_7.add(lblTelf, gbc_lblTelf);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.gridx = 4;
		gbc_textField_3.gridy = 2;
		panel_7.add(textField_3, gbc_textField_3);
		
		JLabel lblDireccion = new JLabel("Direccion");
		GridBagConstraints gbc_lblDireccion = new GridBagConstraints();
		gbc_lblDireccion.anchor = GridBagConstraints.WEST;
		gbc_lblDireccion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDireccion.gridx = 1;
		gbc_lblDireccion.gridy = 3;
		panel_7.add(lblDireccion, gbc_lblDireccion);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridwidth = 3;
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.gridx = 2;
		gbc_textField_4.gridy = 3;
		panel_7.add(textField_4, gbc_textField_4);
		
		JLabel lblCurso = new JLabel("Curso");
		GridBagConstraints gbc_lblCurso = new GridBagConstraints();
		gbc_lblCurso.anchor = GridBagConstraints.WEST;
		gbc_lblCurso.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurso.gridx = 1;
		gbc_lblCurso.gridy = 4;
		panel_7.add(lblCurso, gbc_lblCurso);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridwidth = 3;
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.gridx = 2;
		gbc_textField_5.gridy = 4;
		panel_7.add(textField_5, gbc_textField_5);
		
		JLabel lblDescCorta = new JLabel("Descripcion corta");
		GridBagConstraints gbc_lblDescCorta = new GridBagConstraints();
		gbc_lblDescCorta.anchor = GridBagConstraints.WEST;
		gbc_lblDescCorta.gridwidth = 2;
		gbc_lblDescCorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescCorta.gridx = 1;
		gbc_lblDescCorta.gridy = 5;
		panel_7.add(lblDescCorta, gbc_lblDescCorta);
		
		JTextArea taDescr = new JTextArea();
		GridBagConstraints gbc_taDescr = new GridBagConstraints();
		gbc_taDescr.fill = GridBagConstraints.BOTH;
		gbc_taDescr.gridwidth = 4;
		gbc_taDescr.insets = new Insets(0, 0, 5, 5);
		gbc_taDescr.gridx = 1;
		gbc_taDescr.gridy = 6;
		panel_7.add(taDescr, gbc_taDescr);
		
		JPanel panel_11 = new JPanel();
		panel_10.add(panel_11, BorderLayout.NORTH);
		
		JLabel lblRegistro = new JLabel("REGISTRO");
		lblRegistro.setBackground(Color.WHITE);
		panel_11.add(lblRegistro);
		
		JPanel panel_12 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_12.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_10.add(panel_12, BorderLayout.SOUTH);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnRegistrar.setBackground(Color.WHITE);
		panel_12.add(btnRegistrar);

	}

}
