package dialogos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
// añadidos:
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DatosPersoais extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DatosPersoais dialog = new DatosPersoais();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DatosPersoais() {
		setTitle("Datos Persoais");
		setBounds(100, 100, 315, 199);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel pDatos = new JPanel();
			getContentPane().add(pDatos, BorderLayout.CENTER);
			GridBagLayout gbl_pDatos = new GridBagLayout();
			gbl_pDatos.columnWidths = new int[]{0, 0, 0};
			gbl_pDatos.rowHeights = new int[]{0, 0, 0};
			gbl_pDatos.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_pDatos.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			pDatos.setLayout(gbl_pDatos);
			{
				JLabel lblNome = new JLabel("Nome");
				GridBagConstraints gbc_lblNome = new GridBagConstraints();
				gbc_lblNome.anchor = GridBagConstraints.WEST;
				gbc_lblNome.insets = new Insets(40, 10, 5, 5);
				gbc_lblNome.gridx = 0;
				gbc_lblNome.gridy = 0;
				pDatos.add(lblNome, gbc_lblNome);
			}
			{
				textField = new JTextField();
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.insets = new Insets(40, 0, 5, 10);
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.gridx = 1;
				gbc_textField.gridy = 0;
				pDatos.add(textField, gbc_textField);
				textField.setColumns(10);
			}
			{
				JLabel lblApellidos = new JLabel("Apellidos");
				GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
				gbc_lblApellidos.anchor = GridBagConstraints.WEST;
				gbc_lblApellidos.insets = new Insets(0, 10, 40, 5);
				gbc_lblApellidos.gridx = 0;
				gbc_lblApellidos.gridy = 1;
				pDatos.add(lblApellidos, gbc_lblApellidos);
			}
			{
				textField_1 = new JTextField();
				GridBagConstraints gbc_textField_1 = new GridBagConstraints();
				gbc_textField_1.insets = new Insets(0, 0, 40, 10);
				gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_1.gridx = 1;
				gbc_textField_1.gridy = 1;
				pDatos.add(textField_1, gbc_textField_1);
				textField_1.setColumns(10);
			}
		}
		{
			JPanel pBoton = new JPanel();
			getContentPane().add(pBoton, BorderLayout.SOUTH);
			{
				JButton bAceptar = new JButton("Aceptar");
				pBoton.add(bAceptar);

				// === LISTENER DO BOTÓN ACEPTAR ===
				bAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Validación de campos
						if (textField.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(
									DatosPersoais.this,
									"O nome non pode estar baleiro",
									"Erro",
									JOptionPane.ERROR_MESSAGE);
							textField.requestFocus();
							return;
						}
						if (textField_1.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(
									DatosPersoais.this,
									"Os apelidos non poden estar baleiros",
									"Erro",
									JOptionPane.ERROR_MESSAGE);
							textField_1.requestFocus();
							return;
						}
						// Se todo está ben, pechamos o diálogo
						dispose();
					}
				});
			}
		}
	}

}
