package dialogos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.UIManager;

public class DavidBJDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DavidBJDialog frame = new DavidBJDialog();
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
	public DavidBJDialog() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 80);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{184, 184, 184, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton bDatosPersoais = new JButton("Datos persoais");
		GridBagConstraints gbc_bDatosPersoais = new GridBagConstraints();
		gbc_bDatosPersoais.fill = GridBagConstraints.HORIZONTAL;
		gbc_bDatosPersoais.insets = new Insets(0, 0, 0, 5);
		gbc_bDatosPersoais.gridx = 0;
		gbc_bDatosPersoais.gridy = 0;
		contentPane.add(bDatosPersoais, gbc_bDatosPersoais);
		
		JButton bDatosAcademicos = new JButton("Datos académicos");
		GridBagConstraints gbc_bDatosAcademicos = new GridBagConstraints();
		gbc_bDatosAcademicos.fill = GridBagConstraints.HORIZONTAL;
		gbc_bDatosAcademicos.insets = new Insets(0, 0, 0, 5);
		gbc_bDatosAcademicos.gridx = 1;
		gbc_bDatosAcademicos.gridy = 0;
		contentPane.add(bDatosAcademicos, gbc_bDatosAcademicos);
		
		JButton bSalir = new JButton("Saír");
		GridBagConstraints gbc_bSalir = new GridBagConstraints();
		gbc_bSalir.fill = GridBagConstraints.HORIZONTAL;
		gbc_bSalir.gridx = 2;
		gbc_bSalir.gridy = 0;
		contentPane.add(bSalir, gbc_bSalir);

	}

}
