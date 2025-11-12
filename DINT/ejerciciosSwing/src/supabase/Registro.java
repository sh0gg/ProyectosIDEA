package supabase;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class Registro extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfMail;
	private JPasswordField pfContraseña;
	private JPasswordField pfContraseñaRepetir;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
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
	public Registro() {
		setTitle("SuperAplicación - Registro");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 390, 268);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pDatos = new JPanel();
		contentPane.add(pDatos, BorderLayout.CENTER);
		pDatos.setLayout(new BorderLayout(0, 0));
		
		JPanel pTitulo = new JPanel();
		pDatos.add(pTitulo, BorderLayout.NORTH);
		pTitulo.setLayout(new GridLayout(1, 0, 10, 10));
		
		JLabel lblTitulo = new JLabel("Registro de nuevo usuario");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		pTitulo.add(lblTitulo);
		
		JPanel pCajas = new JPanel();
		pDatos.add(pCajas, BorderLayout.CENTER);
		GridBagLayout gbl_pCajas = new GridBagLayout();
		gbl_pCajas.columnWidths = new int[]{0, 0, 0};
		gbl_pCajas.rowHeights = new int[]{0, 0, 0, 0};
		gbl_pCajas.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_pCajas.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		pCajas.setLayout(gbl_pCajas);
		
		JLabel lblMail = new JLabel("E-mail");
		GridBagConstraints gbc_lblMail = new GridBagConstraints();
		gbc_lblMail.anchor = GridBagConstraints.EAST;
		gbc_lblMail.insets = new Insets(30, 35, 0, 5);
		gbc_lblMail.gridx = 0;
		gbc_lblMail.gridy = 0;
		pCajas.add(lblMail, gbc_lblMail);
		
		tfMail = new JTextField();
		GridBagConstraints gbc_tfMail = new GridBagConstraints();
		gbc_tfMail.insets = new Insets(30, 0, 0, 25);
		gbc_tfMail.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfMail.gridx = 1;
		gbc_tfMail.gridy = 0;
		pCajas.add(tfMail, gbc_tfMail);
		tfMail.setColumns(10);
		
		JLabel lblContraseña = new JLabel("Contraseña");
		GridBagConstraints gbc_lblContraseña = new GridBagConstraints();
		gbc_lblContraseña.anchor = GridBagConstraints.EAST;
		gbc_lblContraseña.insets = new Insets(20, 0, 5, 5);
		gbc_lblContraseña.gridx = 0;
		gbc_lblContraseña.gridy = 1;
		pCajas.add(lblContraseña, gbc_lblContraseña);
		
		pfContraseña = new JPasswordField();
		GridBagConstraints gbc_pfContraseña = new GridBagConstraints();
		gbc_pfContraseña.insets = new Insets(20, 0, 5, 25);
		gbc_pfContraseña.fill = GridBagConstraints.HORIZONTAL;
		gbc_pfContraseña.gridx = 1;
		gbc_pfContraseña.gridy = 1;
		pCajas.add(pfContraseña, gbc_pfContraseña);
		
		JLabel lblContraseñaRepetir = new JLabel("¡Repítela!");
		GridBagConstraints gbc_lblContraseñaRepetir = new GridBagConstraints();
		gbc_lblContraseñaRepetir.anchor = GridBagConstraints.EAST;
		gbc_lblContraseñaRepetir.insets = new Insets(20, 0, 0, 5);
		gbc_lblContraseñaRepetir.gridx = 0;
		gbc_lblContraseñaRepetir.gridy = 2;
		pCajas.add(lblContraseñaRepetir, gbc_lblContraseñaRepetir);
		
		pfContraseñaRepetir = new JPasswordField();
		GridBagConstraints gbc_pfContraseñaRepetir = new GridBagConstraints();
		gbc_pfContraseñaRepetir.insets = new Insets(20, 0, 0, 25);
		gbc_pfContraseñaRepetir.fill = GridBagConstraints.HORIZONTAL;
		gbc_pfContraseñaRepetir.gridx = 1;
		gbc_pfContraseñaRepetir.gridy = 2;
		pCajas.add(pfContraseñaRepetir, gbc_pfContraseñaRepetir);
		
		JPanel pBoton = new JPanel();
		contentPane.add(pBoton, BorderLayout.SOUTH);
		pBoton.setLayout(new GridLayout(0, 4, 5, 10));
		
		JLabel label = new JLabel("");
		pBoton.add(label);
		
		JLabel label_1 = new JLabel("");
		pBoton.add(label_1);
		
		JButton bAtras = new JButton("Atras");
		bAtras.setAction(action);
		pBoton.add(bAtras);
		
		JButton bEnviar = new JButton("Enviar");
		bEnviar.setAction(action_1);
		pBoton.add(bEnviar);

	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Atras");
			putValue(SHORT_DESCRIPTION, "Quita esta ventana");
		}
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Enviar");
			putValue(SHORT_DESCRIPTION, "Intenta registrar el nuevo usuario");
		}
		public void actionPerformed(ActionEvent e) {
			String mail = tfMail.getText();
			String contra = new String(pfContraseña.getPassword());
			String contra2 = new String(pfContraseña.getPassword());
			
			
			if (contra != contra2) {
				DialogoLogin dialog = new DialogoLogin("¡Tus contraseñas no coinciden!");
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} else if ()
		}
	}
}
